package br.com.webcko.academia.controller;

import br.com.webcko.academia.entity.Exercicio;
import br.com.webcko.academia.entity.GrupoMuscular;
import br.com.webcko.academia.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/exercicio")
public class ExercicioController {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){

        Exercicio exercicio = this.exercicioRepository.findById(id).orElse(null);

        return exercicio == null
                ? ResponseEntity.badRequest().body("Nenhum registro encontrado")
                : ResponseEntity.ok(exercicio);

    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaExercicio(){
        return ResponseEntity.ok(this.exercicioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Exercicio exercicio){
        try{
            this.exercicioRepository.save(exercicio);
            return ResponseEntity.ok("Registro salvo com sucesso");
        }catch(Exception erro){
            return ResponseEntity.badRequest().body("Error" + erro.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar (@RequestParam("id") final Long id, @RequestBody final Exercicio exercicio){
        try{
            final Exercicio exercicioData = this.exercicioRepository.findById(id).orElse(null);

            if(exercicioData == null || !exercicioData.getId().equals(exercicio.getId())){
                throw new RuntimeException("Nao foi possivel identificar o registro informado");
            }

            this.exercicioRepository.save(exercicio);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        }catch(DataIntegrityViolationException erro){
            return ResponseEntity.internalServerError().body("Error" + erro.getCause().getCause().getMessage());
        }catch(RuntimeException erro){
            return ResponseEntity.badRequest().body("Error " + erro.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletar (@RequestParam("id") final Long id) {

        final Exercicio exercicioData = this.exercicioRepository.findById(id).orElse(null);

        if (exercicioData != null) {
            try {
                this.exercicioRepository.delete(exercicioData);
                return ResponseEntity.ok().body("Registro deletado com sucesso");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Nenhum registro encontrado");
        }
    }
}
