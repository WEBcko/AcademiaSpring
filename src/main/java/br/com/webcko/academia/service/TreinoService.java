package br.com.webcko.academia.service;

import br.com.webcko.academia.entity.Treino;
import br.com.webcko.academia.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Transactional
    public void cadastrar(final Treino treino){

    }
}
