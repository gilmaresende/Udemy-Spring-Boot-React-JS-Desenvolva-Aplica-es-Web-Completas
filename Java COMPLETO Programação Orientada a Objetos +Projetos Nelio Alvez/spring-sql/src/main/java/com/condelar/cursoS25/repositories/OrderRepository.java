package com.condelar.cursoS25.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.condelar.cursoS25.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
