package br.com.webcko.academia.controller;

import br.com.webcko.academia.DTOs.EntradaSaidaDTO;
import br.com.webcko.academia.entity.EntradaSaida;
import br.com.webcko.academia.entity.TreinoExercicio;
import br.com.webcko.academia.repository.EntradaSaidaRepository;
import br.com.webcko.academia.service.EntradaSaidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/entradasaida")
public class EntradaSaidaController {

    @Autowired
    private EntradaSaidaRepository entradaSaidaRepository;

    @Autowired
    private EntradaSaidaService entradaSaidaService;


    @GetMapping
    public ResponseEntity<?> findEntradaSaida(){
        return ResponseEntity.ok(this.entradaSaidaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByid (@PathVariable("id") final Long id){
        try{
            return ResponseEntity.ok(this.entradaSaidaRepository.findById(id));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @GetMapping("/role")
    public ResponseEntity<Page<EntradaSaida>> getAllRequest(Pageable pageable) {
        return ResponseEntity.ok(this.entradaSaidaService.listAll(pageable));

    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaEntradaSaida(){
        return ResponseEntity.ok(this.entradaSaidaRepository.findAll());
    }
    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final EntradaSaidaDTO entradaSaida){
        try{
            this.entradaSaidaService.cadastrar(entradaSaida);
            return ResponseEntity.ok("Registro salvo com sucesso.");
        }catch (Exception erro){
            return ResponseEntity.badRequest().body("Error" + erro.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editar (@PathVariable("id") final Long id, @RequestBody final EntradaSaida entradaSaida){
        try{

            this.entradaSaidaService.editar(id,entradaSaida);
            return ResponseEntity.ok("Registro atualizado com sucesso.");
        }catch (DataIntegrityViolationException erro){
            return ResponseEntity.internalServerError().body("Error" + erro.getCause().getCause().getMessage());
        }catch (RuntimeException erro){
            return ResponseEntity.badRequest().body("Error " + erro.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletar (@RequestParam("id") final Long id){
        try{
            this.entradaSaidaService.deletar(id);
            return ResponseEntity.ok("Registro deletado");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }
}
