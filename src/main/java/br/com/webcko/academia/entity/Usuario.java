package br.com.webcko.academia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Usuario extends AbstractEntity {

        @Getter @Setter
        @Column(name = "nome", nullable = false, length = 100)
        private String nome;

        @Getter @Setter
        @Column(name = "telefone", nullable = false, unique = true, length = 30)
        private String telefone;

        @Getter @Setter
        @Column(name = "cpf", nullable = false, unique = true, length = 16)
        private String cpf;

        @Getter @Setter
        @Column(name = "email", nullable = false, length = 100, unique = true)
        private String email;

        @Getter @Setter
        @Column(name = "senha", nullable = false, length = 100)
        private String senha;


}
