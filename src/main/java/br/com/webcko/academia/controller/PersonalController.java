package br.com.webcko.academia.controller;

import br.com.webcko.academia.entity.Cliente;
import br.com.webcko.academia.entity.Exercicio;
import br.com.webcko.academia.entity.Personal;
import br.com.webcko.academia.repository.PersonalRepository;
import br.com.webcko.academia.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "api/personal")
public class PersonalController {

    @Autowired
    private PersonalRepository personalRepository;

    @Autowired
    private PersonalService personalService;

    @GetMapping
    public ResponseEntity<?> findByIdParam (@PathVariable("id") final Long id){

        final Personal personal = this.personalRepository.findById(id).orElse(null);

        return personal == null
                ? ResponseEntity.badRequest().body("Nenhum problema")
                : ResponseEntity.ok(personal);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompletaTreinos(){
        return ResponseEntity.ok(this.personalRepository.findAll());
    }
  
    @GetMapping("/ativo")
    public ResponseEntity<?> buscarAtivo(){
        try{
            return ResponseEntity.ok(this.personalRepository.findByAtivo());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    @GetMapping("/admin")
    public ResponseEntity<?> buscaAdmin(){
        try {
            return ResponseEntity.ok(this.personalRepository.findByAdmin());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

   //admin

    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Personal personal){
        try {
            this.personalService.cadastrar(personal);
            return ResponseEntity.ok("Salvo com sucesso");
        }catch (Exception error){
            return ResponseEntity.badRequest().body("Error" + error.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar (@RequestParam("id") final Long id, @RequestBody final Personal personal){
        try{

            this.personalService.editar(id,personal);
            return ResponseEntity.ok("Atualizado com Sucesso");
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletar (@RequestParam("id") final long id){

        try {
            this.personalService.deletar(id);
            return ResponseEntity.ok().body("Registro deletado com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
    }
}


