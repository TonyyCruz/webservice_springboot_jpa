package com.webservices.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webservices.project.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
