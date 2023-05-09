package com.condelar.cursomc.services;

import com.condelar.cursomc.domain.Categoria;
import com.condelar.cursomc.repositories.CategoriaRepository;
import com.condelar.cursomc.services.exeption.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repo;

    public Categoria buscar(Integer id) {
        Optional<Categoria> op = repo.findById(id);
        return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }
}
