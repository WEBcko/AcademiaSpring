package br.com.webcko.academia.controller;


import br.com.webcko.academia.entity.Treino;
import br.com.webcko.academia.repository.TreinoRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TreinoController {

    @Autowired
    private TreinoRepository treinoRepository;

    @RequestMapping(value ="/treinos", method = RequestMethod.GET)
    public List<Treino> Get(){
        return treinoRepository.findAll();
    }

    @RequestMapping(value = "/treinos/{id}", method = RequestMethod.GET)
    public ResponseEntity<Treino>  GetById(@PathVariable(value = "id") Long id){
        Optional<Treino> treino = treinoRepository.findById(id);
        if(treino.isPresent()){
            return new ResponseEntity<Treino>(treino.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/treinos", method = RequestMethod.POST)
    public Treino Post(@Valid @RequestBody Treino treino) {
        return treinoRepository.save(treino);
    }

    @RequestMapping(value = "/treinos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Treino> Put(@PathVariable(value = "id") Long id, @Valid @RequestBody Treino newTreino){
        Optional<Treino> oldTreino = treinoRepository.findById(id);
        if(oldTreino.isPresent()){
            Treino treino = oldTreino.get();
            treino.setCodigoOrdem(newTreino.getCodigoOrdem());
            return new ResponseEntity<Treino>(newTreino, HttpStatus.OK);
        }else{
            return new ResponseEntity<Treino>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Long id){
        Optional<Treino> treino = treinoRepository.findById(id);
        if(treino.isPresent()){
            treinoRepository.delete(treino.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
