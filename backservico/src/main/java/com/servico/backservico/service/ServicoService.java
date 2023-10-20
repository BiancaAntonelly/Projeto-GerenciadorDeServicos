package com.servico.backservico.service;

import com.servico.backservico.entity.Servico;
import com.servico.backservico.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {
    private ServicoRepository servicoRepository;

    @Autowired
    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> buscarTodos(){
        return servicoRepository.findAll();
    }

    public List<Servico> buscarServicosPagamentoPendente(){
        return servicoRepository.buscarServicosPagamentoPendente();
    }
    public List<Servico> buscarServicosCancelados(){
        return servicoRepository.buscarServicosCancelados();
    }
    public Servico inserir(Servico servico){
        if(servico.getValorPago() == null || servico.getValorPago() == 0 || servico.getDataPagamento()==null){
            servico.setStatus("pendente");
        }else{
            servico.setStatus("realizado");
        }
        Servico servicoBanco = servicoRepository.save(servico);
        return servicoBanco;
    }

    public Servico alterar(Servico servico){
        if(servico.getValorPago() != null && servico.getValorPago() > 0 || servico.getDataPagamento()!=null){
            servico.setStatus("realizado");
        }
        return servicoRepository.saveAndFlush(servico);
    }

    public Servico cancelarServico(Long id) {
        Servico servico = servicoRepository.findById(id).get();
        servico.setStatus("cancelado");
        return servicoRepository.save(servico);
    }


    public void excluir(Long id){
        Servico servico = servicoRepository.findById(id).get();
        servicoRepository.delete(servico);
    }
}
