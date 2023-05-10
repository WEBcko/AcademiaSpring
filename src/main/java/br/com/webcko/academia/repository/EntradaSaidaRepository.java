package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.Cliente;
import br.com.webcko.academia.entity.EntradaSaida;
import br.com.webcko.academia.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradaSaidaRepository extends JpaRepository<EntradaSaida, Long> {

    @Query("from EntradaSaida where cliente = :cliente")
    public List<EntradaSaida> findEntradaSaidaByCliente(@Param("cliente")Cliente cliente);

    @Query("from EntradaSaida where personal = :personal")
    public List<EntradaSaida> findEntradaSaidaByIdPersonal(@Param("personal") Personal personal);
}
