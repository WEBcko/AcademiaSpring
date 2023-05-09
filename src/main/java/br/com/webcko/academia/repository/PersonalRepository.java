package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.Cliente;
import br.com.webcko.academia.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {

    @Query("from Personal where ativo = true")
    public List<Personal> findByAtivo ();

    @Query("from Personal where admin = true")
    public List<Personal> findByAdmin ();

//    @Query("from Personal where id =:id")
//    public List<Personal> findPersonalById(@Param("id") Long id);
}
