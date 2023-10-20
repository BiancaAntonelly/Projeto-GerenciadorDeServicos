package com.servico.backservico.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "servico")
@Data
public class Servico {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome_cliente")
    private String nomeCliente;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicio")
    private Date dataInicio = new Date();
    @Column(name = "data_termino")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    @Column(name = "descricao_servico")
    private String descricaoServico;
    @Column(name = "valor_servico")
    private Double valorServico;
    @Column(name = "valor_pago")
    private Double valorPago;
    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    @Column(name = "status")
    private String status;

}
