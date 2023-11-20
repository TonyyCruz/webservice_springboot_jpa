package com.webservices.project.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.webservices.project.entities.User;
import com.webservices.project.repositories.UserRepository;
import com.webservices.project.services.exceptions.DatabaseException;
import com.webservices.project.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(Long id) {
    Optional<User> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public User insert(User user) {
    return repository.save(user);
  }

  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }

  public User update(Long id, User user) {
    try {
      User userEntity = repository.getReferenceById(id);
      updateUser(userEntity, user);
      return repository.save(userEntity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  private void updateUser(User userEntity, User user) {
    userEntity.setName(user.getName());
    userEntity.setEmail(user.getEmail());
    userEntity.setPhone(user.getPhone());
  }
}
