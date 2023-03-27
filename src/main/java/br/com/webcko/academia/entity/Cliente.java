package br.com.webcko.academia.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.lang.Long;
import java.time.LocalDate;

@Entity
@Table(name = "clientes", schema = "public")
public class Cliente extends Usuario {

//    @Getter @Setter
//    @OneToOne
//    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
//    private Usuario usuario;

    @Getter @Setter
    @Column(name = "peso", nullable = false)
    private BigDecimal peso;

    @Getter @Setter
    @Column(name = "altura", nullable = false)
    private BigDecimal altura;

    @Getter @Setter
    @Column(name = "cep", nullable = false, length = 10)
    private String cep;

    @Getter @Setter
    @Column(name = "numero_casa", nullable = false)
    private int numeroCasa;

    @Getter @Setter
    @Column(name = "data_nascimento", nullable = false, columnDefinition = "DATE")
    private LocalDate dataNascimento;

}
