package br.com.webcko.academia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "clientes", schema = "public")
public class Cliente extends Usuario {

    @Getter @Setter
    @Column(name = "peso", nullable = false)
    private BigDecimal peso;

    @Getter @Setter
    @Column(name = "altura", nullable = false)
    private BigDecimal altura;

    @Getter @Setter
    @Column(name = "cep", nullable = false, length = 10)
    private int cep;

    @Getter @Setter
    @Column(name = "numero_casa", nullable = false, length = 10)
    private int numeroCasa;

    @Getter @Setter
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

}
