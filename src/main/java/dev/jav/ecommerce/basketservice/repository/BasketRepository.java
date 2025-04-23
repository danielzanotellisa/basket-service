package dev.jav.ecommerce.basketservice.repository;

import dev.jav.ecommerce.basketservice.model.Basket;
import dev.jav.ecommerce.basketservice.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends MongoRepository<Basket, String> {

  Optional<Basket> findByClientAndStatus(Long client, Status status);
}
