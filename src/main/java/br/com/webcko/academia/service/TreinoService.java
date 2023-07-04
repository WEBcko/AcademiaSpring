package br.com.webcko.academia.service;

import br.com.webcko.academia.DTOs.TreinoDTO;
import br.com.webcko.academia.entity.Treino;
import br.com.webcko.academia.entity.Usuario;
import br.com.webcko.academia.repository.TreinoRepository;
import br.com.webcko.academia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final TreinoDTO treinoDto){

        Assert.isTrue(treinoDto.getCodigoOrdem() != null,"Erro, campo vazio.");

        final Usuario user = this.usuarioRepository.findById(treinoDto.getIdUsuario().getId()).orElse(null);

        System.out.println(user.getId());
        System.out.println(user.getNome());
        Assert.isTrue(user != null, "Usuario nao encontrado");

        Treino treino = new Treino();
        treino.setCodigoOrdem(treinoDto.getCodigoOrdem());
        treino.setIdUsuario(user);

        this.treinoRepository.save(treino);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final Treino treino){
        final Treino treinoBanco = this.treinoRepository.findById(id).orElse(null);

        Assert.isTrue(treinoBanco != null || treino.getId().equals(id), "Registro não encontrado");

        Assert.isTrue(treino.getId() != null, "ID já existente.");

        this.treinoRepository.save(treino);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id){
        final Treino treinoBanco = this.treinoRepository.findById(id).orElse(null);

        Assert.isTrue(treinoBanco != null, "Registro não encontrado");

        this.treinoRepository.delete(treinoBanco);
    }
}
