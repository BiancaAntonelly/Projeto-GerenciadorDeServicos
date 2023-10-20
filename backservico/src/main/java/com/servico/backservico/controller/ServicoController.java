package com.servico.backservico.controller;

import com.servico.backservico.entity.Servico;
import com.servico.backservico.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servico")
public class ServicoController {

    private ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/todos")
    public List<Servico> buscarTodos(){
        return  servicoService.buscarTodos();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/pagamentoPendente")
    public List<Servico> buscarServicosPagamentoPendente(){
        return servicoService.buscarServicosPagamentoPendente();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/cancelados")
    public List<Servico> buscarServicosCancelados(){
        return servicoService.buscarServicosCancelados();
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/")
    public ResponseEntity<Servico> inserir(@RequestBody Servico servico) {
        Servico servicoBanco = servicoService.inserir(servico);
        return ResponseEntity.ok(servicoBanco);
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable("id") Long id){
        servicoService.cancelarServico(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin("http://localhost:3000")
    @PutMapping("/")
    public ResponseEntity<Servico> alterar(@RequestBody Servico servico) {
        Servico servicoAtualizado = servicoService.alterar(servico);
        return ResponseEntity.ok(servicoAtualizado);
    }

    @CrossOrigin("http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        servicoService.excluir((id));
        return ResponseEntity.ok().build();
    }
}
