package com.devsuperior.dllist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dllist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long>{

}
