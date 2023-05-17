package com.devsuperior.dllist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dllist.dto.GameListDTO;
import com.devsuperior.dllist.dto.GameMinDTO;
import com.devsuperior.dllist.services.GameListService;
import com.devsuperior.dllist.services.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

	@Autowired
	private GameListService gameListService;

	@Autowired
	private GameService gameService;

	@GetMapping
	public List<GameListDTO> findAll() {
		return gameListService.findAll();
	}

	@GetMapping(value = "/{id}/games")
	public List<GameMinDTO> findByList(@PathVariable("id") Long id) {
		return gameService.findByList(id);
	}
}
