package br.com.webcko.academia.service;

import br.com.webcko.academia.entity.TreinoExercicio;
import br.com.webcko.academia.repository.TreinoExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class TreinoExercicioService {

    @Autowired
    private TreinoExercicioRepository treinoExercicioRepository;


    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final TreinoExercicio treinoExercicio){
        Assert.isTrue(treinoExercicio.getDificuldade().isBlank(), "Campo vazio");
        Assert.isTrue( treinoExercicio.getPeso().isNaN() || treinoExercicio.getPeso() != null, "Error dado invalido ou vazio");
        Assert.isTrue(treinoExercicio.getRepeticoes() != null, "Error campo vazio");

        this.treinoExercicioRepository.save(treinoExercicio);
    }


}
