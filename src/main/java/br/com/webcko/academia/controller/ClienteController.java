package br.com.webcko.academia.controller;


import br.com.webcko.academia.repository.ClienteRepository;
import br.com.webcko.academia.entity.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository _clienteRepository;

    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public List<Cliente> Get(){
        return _clienteRepository.findAll();
    }

    @RequestMapping (value = "/cliente/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> GetById(@PathVariable(value = "id") Long id){
        Optional<Cliente> cliente = _clienteRepository.findById(id);
        if(cliente.isPresent()){
            return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/cliente", method = RequestMethod.POST)
    public Cliente Post(@Valid @RequestBody Cliente cliente){
        return _clienteRepository.save(cliente);
    }

    @RequestMapping(value = "/cliente/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<?> Put(@PathVariable(value = "id") Long id, @Valid @RequestBody Cliente newCliente)
    {
        Optional<Cliente> oldCliente = _clienteRepository.findById(id);
        System.out.println(oldCliente.get());
        if(oldCliente.isPresent()){
            Cliente cliente = oldCliente.get();
            cliente.setNome(newCliente.getNome());
            cliente.setTelefone(newCliente.getTelefone());
            cliente.setCpf(newCliente.getCpf());
            cliente.setEmail(newCliente.getEmail());
            cliente.setPeso(newCliente.getPeso());
            cliente.setAltura(newCliente.getAltura());
            cliente.setCep(newCliente.getCep());
            cliente.setNumeroCasa(newCliente.getNumeroCasa());
            cliente.setDataNascimento(newCliente.getDataNascimento());
            _clienteRepository.save(cliente);
            return new ResponseEntity<Cliente>(newCliente, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> Delete(@PathVariable(value = "id")Long id){
        Optional<Cliente> cliente = _clienteRepository.findById(id);
        if(cliente.isPresent()){
            _clienteRepository.delete(cliente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
