package br.com.webcko.academia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {

        @Id
        @Getter
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false)
        private Long id;

        @Getter @Setter
        @Column(name = "dt_cadastrado", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
        private LocalDateTime dataCadastrado;

        @Getter @Setter
        @Column(name = "dt_atualizado", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
        private LocalDateTime dataAtualizado;

        @Getter @Setter
        @Column(name = "ativo", nullable = false)
        private boolean ativo;
}
