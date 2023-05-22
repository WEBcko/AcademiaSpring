package br.com.webcko.academia.service;


import br.com.webcko.academia.entity.EntradaSaida;
import br.com.webcko.academia.repository.EntradaSaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class EntradaSaidaService {

    @Autowired
    private EntradaSaidaRepository entradaSaidaRepository;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final EntradaSaida entradaSaida){

        Assert.isTrue(entradaSaida.getCliente() != null, "Erro. campo vazio.");
        Assert.isTrue(entradaSaida.getPersonal() != null, "Erro, campo vazio.");
        Assert.isTrue(entradaSaida.getHoraEntrada() != null, "Erro, campo vazio.");
        Assert.isTrue(entradaSaida.getHoraSaida() != null, "Erro, campo vazio.");

        this.entradaSaidaRepository.save(entradaSaida);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final EntradaSaida entradaSaida, final Long id){
        final EntradaSaida entradaSaidaBanco = this.entradaSaidaRepository.findById(id).orElse(null);

        Assert.isTrue(entradaSaidaBanco != null || entradaSaidaBanco.getId().equals(entradaSaida.getId()), "Erro, registro nao encontrado.");

        Assert.isTrue(entradaSaida.getId().equals(id), "Erro, id da URL diferente do body");

        Assert.isTrue(entradaSaida.getCliente() != null, "Erro, campo vazio");
        Assert.isTrue(entradaSaida.getPersonal() != null, "Erro, campo vazio");
        Assert.isTrue(entradaSaida.getHoraEntrada() != null, "Erro, campo vazio.");
        Assert.isTrue(entradaSaida.getHoraSaida() != null, "Erro, campo vazio");

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
