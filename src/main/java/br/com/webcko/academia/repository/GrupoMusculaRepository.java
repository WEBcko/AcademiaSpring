package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.GrupoMuscular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoMusculaRepository extends JpaRepository<GrupoMuscular, Long> {
}