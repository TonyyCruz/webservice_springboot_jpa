package com.webservices.project.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.webservices.project.entities.User;
import com.webservices.project.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    User user1 = new User(null, "User 1", "user1@email.com", "999999999", "123456");
    User user2 = new User(null, "User 2", "user2@email.com", "888888888", "abcdef");
    userRepository.saveAll(Arrays.asList(user1, user2));
  }
}
