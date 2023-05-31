package br.com.webcko.academia.service;

import br.com.webcko.academia.entity.EntradaSaida;
import br.com.webcko.academia.entity.Personal;
import br.com.webcko.academia.repository.EntradaSaidaRepository;
import br.com.webcko.academia.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    @Autowired
    private EntradaSaidaRepository entradaSaidaRepository;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Personal personal){

       // Assert.isTrue(personal.getAdmin() != null, "Erro, campo admin vazio");
        Assert.isTrue(personal.getNome() != null, "Erro, campo nome vazio");
        Assert.isTrue(personal.getTelefone() != null, "Erro, campo telefone vazio");
        Assert.isTrue(personal.getCpf() != null, "Erro, campo cpf vazio");
        Assert.isTrue(personal.getEmail() != null, "Erro, campo email vazio");

        //GET SENHA?

        String cpf = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
        Assert.isTrue(personal.getCpf().matches(cpf), "Erro na mascara do cpf");

        String telefone = "^\\d{11}$";
        Assert.isTrue(personal.getTelefone().matches(telefone), "Erro no tamanho do telefone");

        this.personalRepository.save(personal);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id,final Personal personal){

        final Personal personalBanco = this.personalRepository.findById(id).orElse(null);

        Assert.isTrue(personalBanco != null || !personalBanco.getId().equals(personal.getId()), "nao foi possivel identificar o personal");

        Assert.isTrue(personal.getAdmin() != null, "Erro, campo admin nao informado");
        Assert.isTrue(personal.getNome() != null, "Erro, campo nome nao informado");
        Assert.isTrue(personal.getTelefone() != null, "Erro, campo telefone nao informado");
        Assert.isTrue(personal.getCpf() != null, "Erro, campo cpf nao informado");
        Assert.isTrue(personal.getEmail() != null, "Erro, campo email nao informado");



        this.personalRepository.save(personal);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id){

        final Personal personalBanco = this.personalRepository.findById(id).orElse(null);

        Assert.isTrue(personalBanco != null, "Registro nao encontrado");

        final List<EntradaSaida> entradaSaidas = this.entradaSaidaRepository.findEntradaSaidaByIdPersonal(personalBanco);

        if (entradaSaidas.isEmpty()){
            this.personalRepository.delete(personalBanco);
        }else {
            personalBanco.setAtivo(false);
            this.personalRepository.save(personalBanco);
        }
    }
}
