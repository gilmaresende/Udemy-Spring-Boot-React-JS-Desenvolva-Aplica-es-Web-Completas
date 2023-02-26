package com.condelar.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.condelar.helpdesk.domain.Pessoa;
import com.condelar.helpdesk.domain.Cliente;
import com.condelar.helpdesk.dto.ClienteDTO;
import com.condelar.helpdesk.repositorys.PessoaRepository;
import com.condelar.helpdesk.repositorys.ClienteRepository;
import com.condelar.helpdesk.service.exception.DataIntegrityViolationException;
import com.condelar.helpdesk.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		newObj.setSenha(encoder.encode(newObj.getSenha()));
		newObj = repository.save(newObj);
		return newObj;
	}

	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastro no sistema!");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastro no sistema!");
		}

	}

	public Cliente update(Integer id, @Valid ClienteDTO dtoNew) {
		dtoNew.setId(id);
		Cliente ob = findById(id);
		validaPorCpfEEmail(dtoNew);
		ob = new Cliente(dtoNew);
		//ob.setSenha(encoder.encode(ob.getSenha()));
		return repository.save(ob);
	}

	public void delete(Integer id) {
		Cliente ob = findById(id);
		if (ob.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado");

		}
		repository.delete(ob);
	}
}
