package com.webservices.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webservices.project.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
