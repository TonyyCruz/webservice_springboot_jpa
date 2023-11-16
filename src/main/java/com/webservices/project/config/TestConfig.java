package com.webservices.project.config;

import java.time.Instant;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.webservices.project.entities.Category;
import com.webservices.project.entities.Order;
import com.webservices.project.entities.OrderItem;
import com.webservices.project.entities.Payment;
import com.webservices.project.entities.Product;
import com.webservices.project.entities.User;
import com.webservices.project.entities.enums.OrderStatus;
import com.webservices.project.repositories.CategoryRepository;
import com.webservices.project.repositories.OrderItemRepository;
import com.webservices.project.repositories.OrderRepository;
import com.webservices.project.repositories.ProductRepository;
import com.webservices.project.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private OrderItemRepository orderItemRepository;

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
    Category category1 = new Category(null, "kitchen");
    Category category2 = new Category(null, "living room");
    categoryRepository.saveAll(Arrays.asList(category1, category2));
    Product product1 = new Product(null, "TV", "TV 50 inches", 1950.00, "http://img-url-tv.com");
    product1.getCategories().add(category2);
    Product product2 = new Product(null, "couch", "couch 2 places", 950.00, "http://img-couch.com");
    product2.getCategories().add(category2);
    Product product3 = new Product(null, "cooker", "cooker black", 830.90, "http://img-cooker.com");
    product3.getCategories().add(category1);
    productRepository.saveAll(Arrays.asList(product1, product2, product3));
    OrderItem orderItem1 = new OrderItem(order1, product1, 3, product1.getPrice());
    OrderItem orderItem2 = new OrderItem(order1, product3, 1, product3.getPrice());
    OrderItem orderItem3 = new OrderItem(order2, product3, 2, product3.getPrice());
    OrderItem orderItem4 = new OrderItem(order3, product2, 2, product2.getPrice());
    orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3, orderItem4));
    Payment payment1 = new Payment(null, Instant.parse("2019-07-21T04:55:38Z"), order2);
    order2.setPayment(payment1);
    orderRepository.save(order2);
  }
}
