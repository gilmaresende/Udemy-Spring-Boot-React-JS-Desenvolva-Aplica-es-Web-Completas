package com.devsuperior.dllist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dllist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE tb_belonging SET position = :newPosition WHERE list_id = :listId AND game_id = :gameId")
	void updateBelongingPosition(Integer newPosition, Long listId, Long gameId);

}
