package com.condelar.cursomc.services;

import com.condelar.cursomc.domain.Cidade;
import com.condelar.cursomc.repositories.CidadeRepository;
import com.condelar.cursomc.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public List<Cidade> findAll(Integer estadoId) {
        return repository.findCidades(estadoId);

    }
}
