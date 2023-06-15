package br.com.webcko.academia.service;

import br.com.webcko.academia.entity.EntradaSaida;
import br.com.webcko.academia.repository.ClienteRepository;
import br.com.webcko.academia.repository.EntradaSaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EntradaSaidaRepository entradaSaidaRepository;

    private String telefone = "\\+\\d{3}\\(\\d{3}\\)\\d{5}-\\d{4}";

    private final String  cpf = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Cliente cliente){

        Assert.isTrue(cliente.getNome() != null, "Erro, campo nome vazio");
        Assert.isTrue(cliente.getTelefone() != null, "Erro, campo telefone vazio");
        Assert.isTrue(cliente.getCpf() != null, "Erro, campo cpf vazio");
        Assert.isTrue(cliente.getEmail() != null, "Erro, campo email vazio");

        Assert.isTrue(cliente.getPeso() != null, "Erro, campo Peso vazio");
        Assert.isTrue(cliente.getAltura() != null, "Erro, campo Altura vazio");
        Assert.isTrue(cliente.getCep() != null, "Erro, campo Cep vazio");
        Assert.isTrue(cliente.getNumeroCasa() >= 0, "Erro, campo NumeroCasa vazio");
        Assert.isTrue(cliente.getDataNascimento() != null, "Erro, campo DataNascimento vazio");

        Assert.isTrue(cliente.getCpf().matches(cpf), "Erro na mascara do cpf");
        Assert.isTrue(this.clienteRepository.findCpf(cliente.getCpf()).isEmpty(), "CPF já existente.");

        Assert.isTrue(cliente.getTelefone().matches(telefone), "Erro no tamanho do telefone");
        Assert.isTrue(this.clienteRepository.findTelefone(cliente.getTelefone()).isEmpty(), "Telefone já existente.");

        String cep = "^\\d{5}\\-\\d{3}";
        Assert.isTrue(cliente.getCep().matches(cep), "Erro na mascara do cep");

        this.clienteRepository.save(cliente);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final Cliente cliente){

        final Cliente clienteBanco = this.clienteRepository.findById(id).orElse(null);

        Assert.isTrue(clienteBanco != null || !clienteBanco.getId().equals(cliente.getId()), "nao foi possivel identificar o cliente");

        Assert.isTrue(cliente.getNome() != null, "Erro, campo nome vazio");
        Assert.isTrue(cliente.getTelefone() != null, "Erro, campo telefone vazio");
        Assert.isTrue(cliente.getCpf() != null, "Erro, campo cpf vazio");
        Assert.isTrue(cliente.getEmail() != null, "Erro, campo email vazio");

        Assert.isTrue(cliente.getPeso() != null, "Erro, campo Peso vazio");
        Assert.isTrue(cliente.getAltura() != null, "Erro, campo Altura vazio");
        Assert.isTrue(cliente.getCep() != null, "Erro, campo Cep vazio");
        Assert.isTrue(cliente.getNumeroCasa() >= 0, "Erro, campo NumeroCasa vazio");
        Assert.isTrue(cliente.getDataNascimento() != null, "Erro, campo DataNascimento vazio");

        Assert.isTrue(clienteBanco != null || !clienteBanco.getId().equals(cliente.getId()), "Nao foi possivel identificar o registro.");

        Assert.isTrue(cliente.getCpf().matches(cpf), "Erro na mascara do cpf");
        Assert.isTrue(this.clienteRepository.findCpfId(cliente.getCpf(), cliente.getId())!= null, "CPF ja existente.");

        Assert.isTrue(cliente.getTelefone().matches(telefone), "Erro no tamanho do telefone");


        String cep = "^\\d{5}\\-\\d{3}";
        Assert.isTrue(cliente.getCep().matches(cep), "Erro na mascara do cep");

        this.clienteRepository.save(cliente);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id){

        final Cliente clienteBanco = this.clienteRepository.findById(id).orElse(null);

        Assert.isTrue(clienteBanco != null, "Registro nao encontrado");

        final List<EntradaSaida> entradaSaidas = this.entradaSaidaRepository.findEntradaSaidaByIdCliente(clienteBanco);

        if (entradaSaidas.isEmpty()){
            this.clienteRepository.delete(clienteBanco);
        }else {
            clienteBanco.setAtivo(false);
            this.clienteRepository.save(clienteBanco);
        }
    }
}
