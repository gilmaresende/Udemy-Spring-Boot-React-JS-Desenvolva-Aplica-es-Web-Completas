package com.condelar.helpdesk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.condelar.helpdesk.domain.Pessoa;
import com.condelar.helpdesk.repositorys.PessoaRepository;
import com.condelar.helpdesk.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PessoaRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Pessoa> op = repository.findByEmail(email);
		if (op.isPresent()) {
			Pessoa p = op.get();
			return new UserSS(p.getId(), p.getEmail(), p.getSenha(), p.getPerfis());
		}
		throw new UsernameNotFoundException(email);
	}

}
