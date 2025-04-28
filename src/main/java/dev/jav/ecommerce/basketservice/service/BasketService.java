package dev.jav.ecommerce.basketservice.service;

import dev.jav.ecommerce.basketservice.client.PlatziProductResponse;
import dev.jav.ecommerce.basketservice.controller.request.BasketRequest;
import dev.jav.ecommerce.basketservice.controller.request.PaymentRequest;
import dev.jav.ecommerce.basketservice.model.Basket;
import dev.jav.ecommerce.basketservice.model.Product;
import dev.jav.ecommerce.basketservice.model.Status;
import dev.jav.ecommerce.basketservice.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public Basket createBasket(BasketRequest basketRequest) {

        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN).ifPresent(
                basket -> {
                    throw new IllegalArgumentException("O cliente ja possui um carrinho em aberto");
                }
        );

        List<Product> productList = new ArrayList<>();
        basketRequest.products().forEach(productRequest -> {
            PlatziProductResponse platziProductResponse = productService.findById(productRequest.id());
            productList.add(
                    Product.builder()
                            .id(platziProductResponse.id())
                            .title(platziProductResponse.title())
                            .price(platziProductResponse.price())
                            .quantity(productRequest.quantity())
                            .build()
            );

        });

        Basket basket = Basket.builder()
                .client(basketRequest.clientId())
                .status(Status.OPEN)
                .products(productList)
                .build();
        basket.calculateTotalPrice();
        return basketRepository.save(basket);

    }

    public Optional<Basket> findBasket(String basketId) {
        return basketRepository.findById(basketId);
    }

    public Basket updateBasket(String basketId, BasketRequest basketRequest) {

        Optional<Basket> optionalBasket  = findBasket(basketId);

        List<Product> productList = new ArrayList<>();
        basketRequest.products().forEach(productRequest -> {
            PlatziProductResponse platziProductResponse = productService.findById(productRequest.id());
            productList.add(
                    Product.builder()
                            .id(platziProductResponse.id())
                            .title(platziProductResponse.title())
                            .price(platziProductResponse.price())
                            .quantity(productRequest.quantity())
                            .build()
            );

        });

        if(optionalBasket.isPresent()) {
            optionalBasket.get().setProducts(productList);
            optionalBasket.get().calculateTotalPrice();
            return basketRepository.save(optionalBasket.get());
        }

        else {
            throw new IllegalArgumentException("Carrinho não encontrado");
        }
    }

    public Basket payBasket (String basketId, PaymentRequest request) {

        Optional<Basket> optionalBasket = findBasket(basketId);

        if(optionalBasket.isPresent()) {
            optionalBasket.get().setPaymentMethod(request.paymentMethod());
            optionalBasket.get().setStatus(Status.CLOSED);
            return basketRepository.save(optionalBasket.get());
        }

        else {
            throw new IllegalArgumentException("Carrinho não encontrado");
        }

    }
}
