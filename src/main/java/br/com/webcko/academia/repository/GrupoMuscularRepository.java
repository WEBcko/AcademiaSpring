package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.Cliente;
import br.com.webcko.academia.entity.Exercicio;
import br.com.webcko.academia.entity.GrupoMuscular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GrupoMuscularRepository extends JpaRepository<GrupoMuscular, Long> {

    @Query("from Cliente where ativo = true")
    public List<Cliente> findByAtivos ();



}
