package br.com.webcko.academia.controller;


import br.com.webcko.academia.entity.TreinoExercicio;
import br.com.webcko.academia.repository.TreinoExercicioRepository;
import br.com.webcko.academia.service.TreinoExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/treinoexercicios")
public class ControllerTreinoExercicio {

    @Autowired
    private TreinoExercicioRepository treinoExercicioRepository;

    @Autowired
    private TreinoExercicioService treinoExercicioService;

    @GetMapping("{id}")
    public ResponseEntity<?> findByid (@PathVariable("id") final Long id){
        try{
            return ResponseEntity.ok(this.treinoExercicioRepository.findById(id));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(this.treinoExercicioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(final TreinoExercicio treinoExercicio){
        try{
            this.treinoExercicioService.cadastrar(treinoExercicio);
            return ResponseEntity.ok("Cadastrado");
        }catch(RuntimeException e){
            return  ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }


    @PutMapping





}
