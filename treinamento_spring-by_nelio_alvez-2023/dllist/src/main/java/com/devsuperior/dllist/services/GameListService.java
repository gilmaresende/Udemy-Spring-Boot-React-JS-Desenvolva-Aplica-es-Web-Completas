package com.devsuperior.dllist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dllist.dto.GameListDTO;
import com.devsuperior.dllist.repositories.GameListRepository;

@Service
public class GameListService {

	@Autowired
	GameListRepository gameRepository;

	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		return gameRepository.findAll().stream().map(m -> new GameListDTO(m)).toList();
	}

	@Transactional(readOnly = true)
	public GameListDTO findbyId(Long id) {
		return new GameListDTO(gameRepository.findById(id).get());
	}

}
