package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.Cliente;
import br.com.webcko.academia.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {

    @Query("from Personal where ativo = true")
    public List<Personal> findPersonalByAtivo ();
}
