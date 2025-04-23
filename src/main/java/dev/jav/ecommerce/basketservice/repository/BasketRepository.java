package dev.jav.ecommerce.basketservice.repository;

import dev.jav.ecommerce.basketservice.model.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends MongoRepository<Basket, String> {
}
