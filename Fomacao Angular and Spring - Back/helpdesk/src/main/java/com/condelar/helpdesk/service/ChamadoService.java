package com.condelar.helpdesk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condelar.helpdesk.domain.Chamado;
import com.condelar.helpdesk.repositorys.ChamadoRepository;
import com.condelar.helpdesk.service.exception.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;

	public Chamado findById(Integer id) {
		Optional<Chamado> ob = repository.findById(id);
		return ob.orElseThrow(() -> new ObjectNotFoundException("Chamado Não encontrado! Id:" + id));
	}

}
