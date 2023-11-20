package com.webservices.project.resources;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.webservices.project.entities.User;
import com.webservices.project.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    List<User> userList = service.findAll();
    return ResponseEntity.ok().body(userList);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    User user = service.findById(id);
    return ResponseEntity.ok().body(user);
  }

  @PostMapping
  public ResponseEntity<User> create(@RequestBody User userObj) {
    User user = service.insert(userObj);
    ServletUriComponentsBuilder servletUri = ServletUriComponentsBuilder.fromCurrentRequest();
    URI uri = servletUri.path("/{id}").buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(uri).body(user);
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "{id}")
  public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
    User updatedUser = service.update(id, user);
    return ResponseEntity.ok().body(updatedUser);
  }
}
