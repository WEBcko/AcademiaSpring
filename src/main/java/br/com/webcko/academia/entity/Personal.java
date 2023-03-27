package br.com.webcko.academia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "personais", schema = "public")
public class Personal extends AbstractEntity{

    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "personal_cliente",
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {
                            "personal_id",
                            "cliente_id"
                    }
            ),
            joinColumns = @JoinColumn(
                    name = "personal_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "cliente_id"
            )
    )
    private List<Cliente> alunos;


}
