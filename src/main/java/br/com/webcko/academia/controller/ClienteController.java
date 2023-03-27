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
    public ResponseEntity<Cliente> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Cliente newCliente)
    {
        Optional<Cliente> oldCliente = _clienteRepository.findById(id);
        if(oldCliente.isPresent()){
            Cliente cliente = oldCliente.get();
            cliente.setPeso(newCliente.getPeso());
            _clienteRepository.save(cliente);
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
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
