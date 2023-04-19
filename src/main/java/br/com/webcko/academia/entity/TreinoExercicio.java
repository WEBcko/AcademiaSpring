package br.com.webcko.academia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class TreinoExercicio extends AbstractEntity {

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "id_treino")
    private Treino id_treino;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "id_exercicio")
    private Exercicio id_exercicio;

    @Getter
    @Setter
    @Column(name = "dificuldade", nullable = false)
    private String dificuldade;

    @Getter
    @Setter
    @Column(name="peso", nullable = false)
    private Float peso;

    @Getter
    @Setter
    @Column(name = "repeticoes")
    private Integer repeticoes;

}
