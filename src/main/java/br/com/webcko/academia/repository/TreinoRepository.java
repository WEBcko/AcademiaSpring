package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long> {
}
