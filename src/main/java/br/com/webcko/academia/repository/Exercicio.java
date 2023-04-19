package br.com.webcko.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Exercicio extends JpaRepository<Exercicio, Long> {
}
