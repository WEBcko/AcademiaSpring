package br.com.webcko.academia.controller;

import br.com.webcko.academia.entity.Cliente;
import br.com.webcko.academia.entity.GrupoMuscular;
import br.com.webcko.academia.repository.GrupoMuscularRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "api/grupo")
public class GrupoMuscularController {

    private GrupoMuscularRepository grupoMuscularRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){

        GrupoMuscular grupo = this.grupoMuscularRepository.findById(id).orElse(null);

        return grupo == null
                ? ResponseEntity.badRequest().body("Nenhum registro encontrado")
                : ResponseEntity.ok(grupo);

    }

    @GetMapping("/lista")
    public ResponseEntity<?> lista(){
        return ResponseEntity.ok(this.grupoMuscularRepository.findAll());
    }

    @GetMapping("/ativos")
    public ResponseEntity<?> buscarAtivos (){
        try{
            return ResponseEntity.ok(this.grupoMuscularRepository.findByAtivos());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final GrupoMuscular grupo){
        try{
            this.grupoMuscularRepository.save(grupo);
            return ResponseEntity.ok("Registro salvo com sucesso");
        }catch(Exception error){
            return ResponseEntity.badRequest().body("Error" + error.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar (@RequestParam("id") final Long id, @RequestBody final GrupoMuscular grupo){
        try{
            final GrupoMuscular grupoBanco = this.grupoMuscularRepository.findById(id).orElse(null);

            if(grupoBanco == null || !grupoBanco.getId().equals(grupo.getId())){
                throw new RuntimeException("Nao foi possivel identificar o registro informado");
            }

            this.grupoMuscularRepository.save(grupo);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        }catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletar (@RequestParam("id") final Long id) {

        final GrupoMuscular grupoBanco = this.grupoMuscularRepository.findById(id).orElse(null);

        if (grupoBanco != null) {
            try {
                this.grupoMuscularRepository.delete(grupoBanco);
                return ResponseEntity.ok().body("Registro deletado com sucesso");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Nenhum registro encontrado");
        }
    }



}
