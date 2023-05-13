package com.condelar.cursomc.services;

import com.condelar.cursomc.domain.Pedido;
import com.condelar.cursomc.repositories.PedidoRepository;
import com.condelar.cursomc.services.exeption.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repo;

    public Pedido find(Integer id) {
        Optional<Pedido> op = repo.findById(id);
        return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}
