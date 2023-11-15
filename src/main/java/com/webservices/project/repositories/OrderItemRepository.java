package com.webservices.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webservices.project.entities.OrderItem;
import com.webservices.project.entities.pk.OrderItemPk;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> {
}
