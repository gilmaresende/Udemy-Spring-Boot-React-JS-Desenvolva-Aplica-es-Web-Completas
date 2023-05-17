package com.devsuperior.dllist.dto;

import org.springframework.beans.BeanUtils;

import com.devsuperior.dllist.entities.GameList;

public class GameListDTO {

	private Long id;
	private String name;

	public GameListDTO() {
	}

	public GameListDTO(GameList ob) {
		BeanUtils.copyProperties(ob, this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
