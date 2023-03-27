package br.com.webcko.academia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "perso_anais", schema = "public")
public class Personal extends AbstractEntity{

    @Getter @Setter
    @Column(name = "id_usuario", nullable = false, unique = true)
    private Long idUsuario;

    @Getter @Setter
    @Column(name = "alunos", nullable = false)
    private List<Cliente> alunos;




}
