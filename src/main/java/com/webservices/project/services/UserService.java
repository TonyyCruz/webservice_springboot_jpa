package com.webservices.project.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webservices.project.entities.User;
import com.webservices.project.repositories.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(Long id) {
    Optional<User> obj = repository.findById(id);
    if (obj.isEmpty())
      return null;
    return obj.get();
  }

  public User save(User user) {
    return repository.save(user);
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
