import React, {useEffect, useState} from 'react';
import './Servico.css';
import axios from 'axios';

function Servico() {
    const [servico, setServico] = useState({
      valoeServico:"",
        nomeCliente: "",
        dataInicio:"",
        dataTermino:"",
        descricao:"",
        valorPago:"",
        dataPagamento:""
});
    const [servicos, setServicos] = useState([]);
    const [atualizar, setAtualizar] = useState();
    
    useEffect(()=> {
      buscarTodos();
    }, [atualizar])

    function handleChange(event){
        setServico({...servico,[event.target.name]:event.target.value});
    }

    function buscarTodos(){
      axios.get("http://localhost:8080/api/servico/todos").then(result =>{
        setServicos(result.data);
      })
    }

    function buscarPagamentoPendente(){
      axios.get("http://localhost:8080/api/servico/pagamentoPendente").then(result =>{
        setServicos(result.data);
      })
    }
    function buscarCancelados(){
      axios.get("http://localhost:8080/api/servico/cancelados").then(result =>{
        setServicos(result.data);
      })
    }

    function limpar(){
      setServico({
        valoeServico:"",
        nomeCliente: "",
        dataInicio:"",
        dataTermino:"",
        descricao:"",
        valorPago:"",
        dataPagamento:""
      });
    }
    function hanbleSubmit(event){
        event.preventDefault();
        if(servico.id){
          axios.put("http://localhost:8080/api/servico/", servico).then(result=>{
          setAtualizar(result);
        });
        }else{
        axios.post("http://localhost:8080/api/servico/", servico).then(result=>{
          setAtualizar(result);
        });
    }
    limpar();
  }
    function excluir(id){
      axios.delete("http://localhost:8080/api/servico/" + id).then(result=>{
        setAtualizar(result);
      });
    }

    function cancelar(id){
      axios.post("http://localhost:8080/api/servico/" + id).then(result=>{
        setAtualizar(result);
      });
    }

    return (
    <div className="container">
      <h1>Cadastro de Serviços</h1>
      <form onSubmit={hanbleSubmit}>
        <div className="col-6">
          <div>
            <label className="form-label">Nome do Cliente</label>
            <input onChange={handleChange} value={servico.nomeCliente || ''} name="nomeCliente" type="text"className="form-control"/>
          </div>
          <div>
            <label className="form-label">Data de Início</label>
            <input onChange={handleChange} value={servico.dataInicio || ''} name="dataInicio" type= "date"className="form-control"/>
          </div>
          <div>
            <label className="form-label">Data de Término</label>
            <input onChange={handleChange} value={servico.dataTermino || ''} name="dataTermino" type= "date"className="form-control"/>
          </div>
          <div>
            <label className="form-label">Descrição do Serviço</label>
            <input onChange={handleChange} value={servico.descricao || ''} name="descricao" type= "text"className="form-control"/>
          </div>
          <div>
            <label className="form-label">Valor do Serviço</label>
            <input onChange={handleChange} value={servico.valorServico || ''} name="valorServico" type= "text"className="form-control"/>
          </div>
          <div>
            <label className="form-label">Valor Pago</label>
            <input onChange={handleChange} value={servico.valorPago || ''} name="valorPago" type= "number"className="form-control"/>
          </div>
          <div>
            <label className="form-label">Data de Pagamento</label>
            <input onChange={handleChange} value={servico.dataPagamento || ''} name="dataPagamento" type= "date"className="form-control"/>
          </div>
          <br></br>
          <input type='submit' className="btn btn-success" value="Cadastrar"></input>
        </div>
      </form>
      <hr/>
      <hr/>

      <button onClick={buscarTodos} type="button" class="btn btn-primary">Listar Todos</button>
      <button onClick={buscarPagamentoPendente} type="button" class="btn btn-secondary">Serviços com Pagamento Pendente</button>
      <button onClick={buscarCancelados} type="button" class="btn btn-success">Serviços Cancelados</button>

      <table class="table">
  <thead>
    <tr>
      <th scope="col">Nome</th>
      <th scope="col">Descrição</th>
      <th scope="col">Valor</th>
      <th scope="col">Status</th>
      <th scope='col'>Opções</th>
    </tr>
  </thead>
    <tbody>
      {servicos.map(serv=>  (
        <tr key={serv.id}>
          <td>{serv.nomeCliente}</td>
          <td>{serv.descricao}</td>
          <td>{serv.valorServico}</td>
          <td>{serv.status}</td>
          <td>
            {serv.status!='cancelado' &&  (
            <button onClick={()=>setServico(serv)} className="btn btn-primary">Alterar</button>
            )}
            {serv.status!='cancelado' && (
            <button onClick={()=>excluir(serv.id)} className="btn btn-danger">Excluir</button>
            )}
            <button onClick={()=>cancelar(serv.id)} className="btn btn-warning">Cancelar</button>
          </td>
        </tr>
      ))}
      </tbody>
      </table>
    </div>
  );
}

export default Servico;
