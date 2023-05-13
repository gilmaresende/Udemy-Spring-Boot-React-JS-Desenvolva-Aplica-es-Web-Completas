package com.condelar.cursomc.services;

import com.condelar.cursomc.domain.Cliente;
import com.condelar.cursomc.repositories.ClienteRepository;
import com.condelar.cursomc.services.exeption.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repo;

    public Cliente find(Integer id) {
        Optional<Cliente> op = repo.findById(id);
        return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
}
