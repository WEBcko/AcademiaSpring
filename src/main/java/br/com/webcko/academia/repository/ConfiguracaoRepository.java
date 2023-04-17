package br.com.webcko.academia.repository;


import br.com.webcko.academia.entity.Configuracao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {
}
