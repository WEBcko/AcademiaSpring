package br.com.webcko.academia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios", schema = "public")
public class Usuario extends AbstractEntity {

        @Getter @Setter
        @Column(name = "nome", nullable = false, length = 100)
        private String nome;

        @Getter @Setter
        @Column(name = "telefone", nullable = false, length = 30)
        private String telefone;

        @Getter @Setter
        @Column(name = "cpf", nullable = false, unique = true, length = 16)
        private String cpf;

        @Getter @Setter
        @Column(name = "email", nullable = false, length = 100)
        private String email;

        @Getter @Setter
        @Column(name = "tipo_usuario", nullable = false)
        private TipoUsuario tipoUsuario;


}
