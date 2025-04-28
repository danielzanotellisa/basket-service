package dev.jav.ecommerce.basketservice.controller;

import dev.jav.ecommerce.basketservice.controller.request.BasketRequest;
import dev.jav.ecommerce.basketservice.model.Basket;
import dev.jav.ecommerce.basketservice.service.BasketService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PostMapping()
    public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest basket) {

        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createBasket(basket));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Basket>> findBasket(@PathVariable String id) {

        Optional<Basket> optionalBasket = basketService.findBasket(id);
        if(optionalBasket.isPresent()) {
            return ResponseEntity.ok(optionalBasket);
        }

        else {
            throw new IllegalArgumentException("O carrinho não foi encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Basket> updateBasket(@PathVariable String id, @RequestBody BasketRequest request) {

        return ResponseEntity.ok(basketService.updateBasket(id,request));

    }
}
