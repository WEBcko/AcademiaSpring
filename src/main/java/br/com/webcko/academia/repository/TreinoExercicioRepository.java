package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.Treino;
import br.com.webcko.academia.entity.TreinoExercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreinoExercicioRepository extends JpaRepository<TreinoExercicio, Long> {

    @Query("from TreinoExercicio where idTreino = :idTreino")
    public List<TreinoExercicio> findtreinoExercicio(@Param("idTreino")final Long id);


}
