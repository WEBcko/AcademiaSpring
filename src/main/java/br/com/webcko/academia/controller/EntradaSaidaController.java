package br.com.webcko.academia.controller;

import br.com.webcko.academia.entity.EntradaSaida;
import br.com.webcko.academia.repository.EntradaSaidaRepository;
import br.com.webcko.academia.service.EntradaSaidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final EntradaSaida entradaSaida){
        try{
            this.entradaSaidaService.cadastrar(entradaSaida);
            return ResponseEntity.ok("Registro salvo com sucesso.");
        }catch (Exception erro){
            return ResponseEntity.badRequest().body("Error" + erro.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<?> editar (@RequestParam("id") final Long id, @RequestBody final EntradaSaida entradaSaida){
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
            return ResponseEntity.ok("Registro deletado com sucesso.");
        } catch (DataIntegrityViolationException erro){
            return ResponseEntity.internalServerError().body("Error" + erro.getCause().getCause().getMessage());
        }
    }
}
