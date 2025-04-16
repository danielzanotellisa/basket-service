package dev.jav.ecommerce.basketservice.service;

import dev.jav.ecommerce.basketservice.client.PlatziProductResponse;
import dev.jav.ecommerce.basketservice.client.PlatziStoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    public List<PlatziProductResponse> findAll() {

        return platziStoreClient.getAllProducts();
    }

    public PlatziProductResponse findById(Long id){
        return platziStoreClient.getProductById(id);
    }


}
