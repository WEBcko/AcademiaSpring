package br.com.webcko.academia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "treinos", schema = "public")
public class Treino extends AbstractEntity {

    @Getter @Setter
    @Column(name = "codigo_ordem", nullable = false, length = 10)
    private String codigoOrdem;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "treinos_exercicios",
            joinColumns = @JoinColumn(name = "treino_id"),
            inverseJoinColumns = @JoinColumn(name = "exercicio_id"))
    private List<Cliente> clientes;

}
