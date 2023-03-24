package br.com.webcko.academia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exercicios", schema = "public")
public class Exercicio extends AbstractEntity{

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "grupo_muscular", nullable = false)
    private GrupoMuscular grupoMuscular;

    @Getter @Setter
    @Column(name = "nome", nullable = false)
    private String nome;

}
