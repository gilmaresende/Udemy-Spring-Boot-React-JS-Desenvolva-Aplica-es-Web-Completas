package com.condelar.helpdesk.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condelar.helpdesk.domain.Chamado;
import com.condelar.helpdesk.domain.Cliente;
import com.condelar.helpdesk.domain.Tecnico;
import com.condelar.helpdesk.domain.enuns.Prioridade;
import com.condelar.helpdesk.domain.enuns.Status;
import com.condelar.helpdesk.dto.ChamadoDTO;
import com.condelar.helpdesk.repositorys.ChamadoRepository;
import com.condelar.helpdesk.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public Chamado findById(Integer id) {
		Optional<Chamado> ob = repository.findById(id);
		return ob.orElseThrow(() -> new ObjectNotFoundException("Chamado Não encontrado! Id:" + id));
	}

	public List<Chamado> findAll() {
		return repository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO dto) {
		return repository.save(newChamado(dto));
	}

	public Chamado update(Integer id, @Valid ChamadoDTO dto) {
		dto.setId(id);
		Chamado ob = findById(id);
		ob = newChamado(dto);
		return repository.save(ob);
	}

	private Chamado newChamado(ChamadoDTO dto) {
		Tecnico tecnico = tecnicoService.findById(dto.getTecnico());
		Cliente cliente = clienteService.findById(dto.getCliente());

		Chamado ob = new Chamado();
		if (dto.getId() != null) {
			ob.setId(dto.getId());
		}
		

		if (dto.getStatus().equals(2)) {
			ob.setDataFechamento(LocalDate.now());
		}

		ob.setTecnico(tecnico);
		ob.setCliente(cliente);
		ob.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
		ob.setStatus(Status.toEnum(dto.getStatus()));
		ob.setTitulo(dto.getTitulo());
		ob.setObservacoes(dto.getObservacoes());
		return ob;
	}

}
