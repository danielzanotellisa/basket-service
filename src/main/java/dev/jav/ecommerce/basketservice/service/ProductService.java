package dev.jav.ecommerce.basketservice.service;

import dev.jav.ecommerce.basketservice.client.PlatziProductResponse;
import dev.jav.ecommerce.basketservice.client.PlatziStoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    @Cacheable(value = "products")
    public List<PlatziProductResponse> findAll() {

        return platziStoreClient.getAllProducts();
    }

    @Cacheable(value = "product", key="#productId")
    public PlatziProductResponse findById(Long productId){
        return platziStoreClient.getProductById(productId);
    }


}
