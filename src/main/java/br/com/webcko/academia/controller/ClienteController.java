package br.com.webcko.academia.controller;


import br.com.webcko.academia.entity.Treino;
import br.com.webcko.academia.repository.ClienteRepository;
import br.com.webcko.academia.entity.Cliente;
import br.com.webcko.academia.repository.TreinoRepository;
import br.com.webcko.academia.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "api/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdParam (@PathVariable final Long id){
        final Cliente cliente = this.clienteRepository.findById(id).orElse(null);

        return cliente == null
                ? ResponseEntity.badRequest().body("Nenhum error encontrado.")
                : ResponseEntity.ok(cliente);


    }

    @GetMapping("/lista")
    public ResponseEntity<?> lista(){
        return ResponseEntity.ok(this.clienteRepository.findAll());
    }


    @GetMapping("/ativos")
    public ResponseEntity<?> buscarAtivos (){
        try{
            return ResponseEntity.ok(this.clienteRepository.findByAtivos());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Cliente cliente){
        try{
            this.clienteService.cadastrar(cliente);
            return ResponseEntity.ok("Registro salvo com sucesso");
        }catch(Exception error){
            return ResponseEntity.badRequest().body("Error" + error.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar (@RequestParam("id") final Long id, @RequestBody final Cliente cliente){
        try{

            this.clienteService.editar(id, cliente);

            return ResponseEntity.ok("Registro atualizado com sucesso");
        }catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletar (@RequestParam("id") final Long id){

            try {
                this.clienteService.deletar(id);
                return ResponseEntity.ok().body("Registro deletado com sucesso");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
            }
    }
}
