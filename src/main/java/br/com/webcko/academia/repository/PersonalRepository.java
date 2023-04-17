package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
