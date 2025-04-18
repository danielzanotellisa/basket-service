package dev.jav.ecommerce.basketservice.service;

import dev.jav.ecommerce.basketservice.client.PlatziProductResponse;
import dev.jav.ecommerce.basketservice.client.PlatziStoreClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    @Cacheable(value = "products")
    public List<PlatziProductResponse> findAll() {
        log.info("Getting all products");
        return platziStoreClient.getAllProducts();
    }

    @Cacheable(value = "product", key="#productId")
    public PlatziProductResponse findById(Long productId){
        log.info("Getting product with ID: {}", productId);
        return platziStoreClient.getProductById(productId);
    }


}
