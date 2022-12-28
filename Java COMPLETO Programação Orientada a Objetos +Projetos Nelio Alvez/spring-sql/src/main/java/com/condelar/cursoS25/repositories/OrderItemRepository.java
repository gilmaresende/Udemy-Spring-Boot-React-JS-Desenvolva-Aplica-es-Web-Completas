package com.condelar.cursoS25.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.condelar.cursoS25.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
