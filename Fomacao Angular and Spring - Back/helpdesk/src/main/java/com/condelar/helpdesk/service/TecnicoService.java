package com.condelar.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condelar.helpdesk.domain.Pessoa;
import com.condelar.helpdesk.domain.Tecnico;
import com.condelar.helpdesk.dto.TecnicoDTO;
import com.condelar.helpdesk.repositorys.PessoaRepository;
import com.condelar.helpdesk.repositorys.TecnicoRepository;
import com.condelar.helpdesk.service.exception.DataIntegrityViolationException;
import com.condelar.helpdesk.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	@Autowired
	PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		newObj = repository.save(newObj);
		return newObj;
	}

	private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastro no sistema!");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastro no sistema!");
		}

	}

	public Tecnico update(Integer id, @Valid TecnicoDTO dtoNew) {
		dtoNew.setId(id);
		Tecnico ob = findById(id);
		validaPorCpfEEmail(dtoNew);
		ob = new Tecnico(dtoNew);
		return repository.save(ob);
	}

	public void delete(Integer id) {
		Tecnico ob = findById(id);
		if (ob.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Tecnico possui ordens de serviço e não pode ser deletado");

		}
		repository.delete(ob);
	}
}
