package com.servico.backservico.repository;


import com.servico.backservico.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico,Long> {

    @Query("select s from Servico s where (s.valorPago is null or s.valorPago = 0) and s.status!='cancelado'")
    List<Servico> buscarServicosPagamentoPendente();

    @Query("select s from Servico s where s.status = 'cancelado'")
    List<Servico> buscarServicosCancelados();
}
