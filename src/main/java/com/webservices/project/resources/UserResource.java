package com.webservices.project.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.webservices.project.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

  @GetMapping
  public ResponseEntity<User> fingAll() {
    User user = new User(1L, "Tony", "tony@email.com", "999999999", "mypassword");
    return ResponseEntity.ok().body(user);
  }
}
