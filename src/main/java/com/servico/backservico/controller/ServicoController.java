package com.servico.backservico.controller;

import com.servico.backservico.entity.Servico;
import com.servico.backservico.service.ServicoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/servico")
public class ServicoController {

    private ServicoService servicoService;
    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping("/")
    public List<Servico> buscarTodos(){
        return  servicoService.buscarTodos();
    }

    @GetMapping("/pagamentoPendente")
    public List<Servico> buscarServicosPagamentoPendente(){
        return servicoService.buscarServicosPagamentoPendente();
    }

    @GetMapping("/cancelados")
    public List<Servico> buscarServicosCancelados(){
        return servicoService.buscarServicosCancelados();
    }
    @PostMapping("/inserir")
    public ResponseEntity<Servico> inserir(@RequestBody Servico servico) {
        Servico servicoBanco = servicoService.inserir(servico);
        return ResponseEntity.ok(servicoBanco);
    }

    @PutMapping("/alterar")
    public ResponseEntity<Servico> alterar(@RequestBody Servico servico) {
        Servico servicoAtualizado = servicoService.alterar(servico);
        return ResponseEntity.ok(servicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathParam("id") Long id){
        servicoService.excluir((id));
        return ResponseEntity.ok().build();
    }
}
