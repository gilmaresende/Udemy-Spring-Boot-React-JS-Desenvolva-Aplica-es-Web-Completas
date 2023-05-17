package com.devsuperior.dllist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dllist.dto.GameDTO;
import com.devsuperior.dllist.dto.GameMinDTO;
import com.devsuperior.dllist.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	GameRepository gameRepository;

	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll() {
		return gameRepository.findAll().stream().map(m -> new GameMinDTO(m)).toList();
	}
	
	@Transactional(readOnly = true)
	public GameDTO findbyId(Long id) {
		return new GameDTO(gameRepository.findById(id).get());
	}

}
