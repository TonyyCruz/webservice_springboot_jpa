package com.webservices.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webservices.project.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
