package com.condelar.helpdesk.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condelar.helpdesk.domain.Chamado;
import com.condelar.helpdesk.domain.Cliente;
import com.condelar.helpdesk.domain.Tecnico;
import com.condelar.helpdesk.domain.enuns.Perfil;
import com.condelar.helpdesk.domain.enuns.Prioridade;
import com.condelar.helpdesk.domain.enuns.Status;
import com.condelar.helpdesk.repositorys.ChamadoRepository;
import com.condelar.helpdesk.repositorys.ClienteRepository;
import com.condelar.helpdesk.repositorys.TecnicoRepository;

@Service
public class DBService {
	

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void instanciaDB() {
		
		Tecnico t1 = new Tecnico(1, "Joao Souza", "123456"," joao.sousa@mail.com", "s1235879");
		t1.addPerfis(Perfil.ADMIN);
		Tecnico t2 = new Tecnico(1, "Maria Souza", "7412589"," maria.sousa@mail.com", "df7591");
		
		
		
		
		Cliente c1 = new Cliente(1, "Tadeu Borges", "785196", "tadeu.borges@mail.com", "4532gf97");
		Cliente c2 = new Cliente(2, "Maria Julia", "785196", "maria.julia@mail.com", "fds72456");
		
		
		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Primeiro Chamado", "Primeira Instacia", t2, c2);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(ch1));
		
	}

}
