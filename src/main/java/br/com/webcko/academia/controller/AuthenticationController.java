package br.com.webcko.academia.controller;

import br.com.webcko.academia.DTOs.LoginRequest;
import br.com.webcko.academia.entity.Usuario;
import br.com.webcko.academia.repository.UsuarioRepository;
import br.com.webcko.academia.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Buscar o usuário com base no nome de usuário fornecido
            Usuario usuario = usuarioRepository.findByNome(loginRequest.getUsername());

            if (authenticationService.autenticar(loginRequest.getUsername(),loginRequest.getSenha())) {
                // As credenciais são válidas, retornar resposta 200 (OK)
                return ResponseEntity.ok("Autenticação bem-sucedida!");
            } else {
                // Credenciais inválidas, retornar resposta 401 (Unauthorized)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
