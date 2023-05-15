package com.devsuperior.dllist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dllist.dto.GameMinDTO;
import com.devsuperior.dllist.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	GameRepository gameRepository;

	public List<GameMinDTO> findAll() {
		return gameRepository.findAll().stream().map(m -> new GameMinDTO(m)).toList();
	}

}
