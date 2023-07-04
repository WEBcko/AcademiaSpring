package br.com.webcko.academia.DTOs;

import br.com.webcko.academia.entity.Usuario;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntradaSaidaDTO {

    private Usuario idUsuario;

    private Usuario idPersonal;

    private LocalDateTime horaEntrada;

    private LocalDateTime horaSaida;
}
