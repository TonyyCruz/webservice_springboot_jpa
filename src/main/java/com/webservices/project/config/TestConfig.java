package com.webservices.project.config;

import java.time.Instant;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.webservices.project.entities.Order;
import com.webservices.project.entities.User;
import com.webservices.project.entities.enums.OrderStatus;
import com.webservices.project.repositories.OrderRepository;
import com.webservices.project.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private OrderRepository orderRepository;

  @Override
  public void run(String... args) throws Exception {
    User user1 = new User(null, "User 1", "user1@email.com", "999999999", "123456");
    User user2 = new User(null, "User 2", "user2@email.com", "888888888", "abcdef");
    userRepository.saveAll(Arrays.asList(user1, user2));
    Order order1 =
        new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.WAITING_PAYMENT, user1);
    Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.PAID, user2);
    Order order3 =
        new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.SHIPPED, user1);
    orderRepository.saveAll(Arrays.asList(order1, order2, order3));
  }
}
