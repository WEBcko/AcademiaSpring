package br.com.webcko.academia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "entradas_saidas", schema = "public")
public class EntradaSaida extends AbstractEntity {


    @Getter @Setter
    @Column(name = "cliente", nullable = false)
    private Cliente cliente;

    @Getter @Setter
    @Column(name = "personal", nullable = false)
    private Personal personal;

    @Getter @Setter
    @Column(name = "hora_entrada", nullable = false)
    private LocalDateTime horaEntrada;

    @Getter @Setter
    @Column(name = "hora_saida", nullable = false)
    private LocalDateTime horaSaida;


}
