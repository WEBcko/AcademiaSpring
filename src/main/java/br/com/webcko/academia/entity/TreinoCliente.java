package br.com.webcko.academia.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

@Entity
public class TreinoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;

    @Getter
    @Setter
    //@ManyToOne
    //@JoinColumn(name = "treino_id")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "treinos_clientes",
        joinColumns = @JoinColumn(name = "treino_id"),
            inverseJoinColumns = @JoinColumn(name = "treino_cliente_id")
    )
    private Treino treino;

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
