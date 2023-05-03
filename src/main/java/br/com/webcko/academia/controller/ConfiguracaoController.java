package br.com.webcko.academia.controller;

import br.com.webcko.academia.entity.Configuracao;
import br.com.webcko.academia.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;

@Controller
@RequestMapping(value = "/api/configuracao")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @GetMapping
    public ResponseEntity<?> findConfiguracaoById (){
        return ResponseEntity.ok(this.configuracaoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Configuracao configuracao){
        try{
            this.configuracaoRepository.save(configuracao);
            return ResponseEntity.ok().body("Configuacao salva com sucesso");
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar (@RequestParam("id") final Long id, @RequestBody final Configuracao configuracao){
        try{
            final Configuracao configuracaoBanco = this.configuracaoRepository.findById(id).orElse(null);

            if(configuracaoBanco == null || configuracaoBanco.getId().equals(configuracao.getId())){
                throw new RuntimeException("Nao foi possivel identificar o registro");
            }

            this.configuracaoRepository.save(configuracao);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        }catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Error "  + e.getCause().getCause().getMessage());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }



    }

}
