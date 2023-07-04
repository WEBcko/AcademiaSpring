package br.com.webcko.academia.service;
import br.com.webcko.academia.DTOs.EntradaSaidaDTO;
import br.com.webcko.academia.entity.EntradaSaida;
import br.com.webcko.academia.entity.TreinoExercicio;
import br.com.webcko.academia.entity.Usuario;
import br.com.webcko.academia.repository.EntradaSaidaRepository;
import br.com.webcko.academia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class EntradaSaidaService {

    @Autowired
    private EntradaSaidaRepository entradaSaidaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<EntradaSaida> listAll(Pageable pageable) {
        return this.entradaSaidaRepository.findAll(pageable);
    }


    @Transactional(rollbackFor =  Exception.class)
    public void cadastrar(final EntradaSaidaDTO entradaSaidaDto){

        final Usuario usuario = this.usuarioRepository.findById(entradaSaidaDto.getIdUsuario().getId()).orElse(null);

        Assert.isTrue(usuario !=null, "Usuario Nao encontrado");

        Assert.isTrue(entradaSaidaDto.getHoraEntrada() != null, "Erro Campo HoraEntrada vazio");
        Assert.isTrue(entradaSaidaDto.getHoraSaida() != null, "Erro Campo HoraSaida vazio");

        EntradaSaida entradaSaida = new EntradaSaida();

        entradaSaida.setCliente(entradaSaidaDto.getIdUsuario());
        entradaSaida.setPersonal(entradaSaidaDto.getIdPersonal());
        entradaSaida.setHoraEntrada(entradaSaidaDto.getHoraEntrada());
        entradaSaida.setHoraSaida(entradaSaidaDto.getHoraSaida());

        this.entradaSaidaRepository.save(entradaSaida);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final EntradaSaida entradaSaida){

        final EntradaSaida entradaSaidaBanco = this.entradaSaidaRepository.findById(id).orElse(null);

        Assert.isTrue(entradaSaidaBanco != null  || entradaSaidaBanco.getId().equals(entradaSaida.getId()),"Erro, registro nao encontrado");

        Assert.isTrue(entradaSaidaBanco.getId().equals(id), "erro da url e diferente do body");

        Assert.isTrue(entradaSaida.getCliente() != null, " erro no cliente campo vazio");
        Assert.isTrue(entradaSaida.getPersonal() != null, " erro no personal campo vazio");
        Assert.isTrue(entradaSaida.getHoraEntrada() != null, " erro no horaEntrada campo vazio");
        Assert.isTrue(entradaSaida.getHoraSaida() != null, " erro no horaSaida campo vazio");


        this.entradaSaidaRepository.save(entradaSaida);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id){
        final EntradaSaida entradaSaidaBanco = this.entradaSaidaRepository.findById(id).orElse(null);

        Assert.isTrue(entradaSaidaBanco != null, "Registro nao encontrado.");

        entradaSaidaBanco.setAtivo(false);
        this.entradaSaidaRepository.save(entradaSaidaBanco);

    }
}
