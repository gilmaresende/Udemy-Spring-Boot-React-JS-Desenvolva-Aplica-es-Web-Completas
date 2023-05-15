package com.devsuperior.dllist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dllist.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

}
