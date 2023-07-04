package br.com.webcko.academia.service;

import br.com.webcko.academia.entity.Usuario;
import br.com.webcko.academia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

        @Autowired
        private UsuarioRepository usuarioRepository;

        public boolean autenticar(String username, String senha) {
            // Buscar o usuário com base no nome de usuário fornecido
            Usuario usuario = usuarioRepository.findByNome(username);

            if (usuario != null && usuario.getSenha().equals(senha)) {
                // As credenciais são válidas
                return true;
            } else {
                // Credenciais inválidas
                return false;
            }
        }

    }

