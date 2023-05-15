package com.condelar.cursomc.services;

import com.condelar.cursomc.domain.Cliente;
import com.condelar.cursomc.domain.Cliente;
import com.condelar.cursomc.domain.enums.TipoCliente;
import com.condelar.cursomc.dto.ClienteDTO;
import com.condelar.cursomc.repositories.ClienteRepository;
import com.condelar.cursomc.services.exeption.DataIntegrityException;
import com.condelar.cursomc.services.exeption.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repo;

    public Cliente find(Integer id) {
        Optional<Cliente> op = repo.findById(id);
        return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }


    public Cliente update(Cliente obj) {
        Cliente ob = find(obj.getId());
        updateData(ob, obj);
        return repo.save(ob);
    }


    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir a categoria, possui produtos vinculados!");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO dto) {
        return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
    }


    private void updateData(Cliente obNew, Cliente obOld) {
        obNew.setNome(obOld.getNome());
        obNew.setEmail(obOld.getEmail());
    }
}
