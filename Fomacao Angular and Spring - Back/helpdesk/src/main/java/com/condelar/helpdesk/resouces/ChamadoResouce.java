package com.condelar.helpdesk.resouces;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.condelar.helpdesk.domain.Chamado;
import com.condelar.helpdesk.dto.ChamadoDTO;
import com.condelar.helpdesk.service.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResouce {

	@Autowired
	private ChamadoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
		Chamado ob = service.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(ob));
	}

	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll() {
		List<Chamado> all = service.findAll();
		return ResponseEntity.ok().body(all.stream().map(m -> new ChamadoDTO(m)).collect(Collectors.toList()));
	}

}
