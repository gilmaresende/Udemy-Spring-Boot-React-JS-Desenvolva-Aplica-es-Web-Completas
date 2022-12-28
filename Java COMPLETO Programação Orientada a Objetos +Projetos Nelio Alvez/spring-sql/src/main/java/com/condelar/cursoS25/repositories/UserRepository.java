package com.condelar.cursoS25.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.condelar.cursoS25.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
