package com.devsuperior.dllist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dllist.dto.GameListDTO;
import com.devsuperior.dllist.projections.GameMinProjection;
import com.devsuperior.dllist.repositories.GameListRepository;
import com.devsuperior.dllist.repositories.GameRepository;

@Service
public class GameListService {

	@Autowired
	GameListRepository gameListRepository;

	@Autowired
	GameRepository gameRepository;

	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		return gameListRepository.findAll().stream().map(m -> new GameListDTO(m)).toList();
	}

	@Transactional(readOnly = true)
	public GameListDTO findbyId(Long id) {
		return new GameListDTO(gameListRepository.findById(id).get());
	}

	public void move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		GameMinProjection ob = list.remove(sourceIndex);
		list.add(destinationIndex, ob);

		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex > destinationIndex ? sourceIndex : destinationIndex;

		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(i, listId, list.get(i).getId());
		}
	}

}
