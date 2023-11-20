package com.webservices.project.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webservices.project.entities.User;
import com.webservices.project.repositories.UserRepository;
import com.webservices.project.services.exceptions.ResourceNotFoundException;

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

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public User update(Long id, User user) {
    User userEntity = repository.getReferenceById(id);
    updateUser(userEntity, user);
    return repository.save(userEntity);
  }

  private void updateUser(User userEntity, User user) {
    userEntity.setName(user.getName());
    userEntity.setEmail(user.getEmail());
    userEntity.setPhone(user.getPhone());
  }
}
