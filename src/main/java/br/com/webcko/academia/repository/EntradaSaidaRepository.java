package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.EntradaSaida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaSaidaRepository extends JpaRepository<EntradaSaida, Long> {
}
