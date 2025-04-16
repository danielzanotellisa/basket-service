package dev.jav.ecommerce.basketservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "PlatziStoreClient", url = "${basket.client.platzi}")
public interface PlatziStoreClient {

    @GetMapping("/products")
    public void getAllProducts();

    @GetMapping("/products/{id}")
    public void getProductById(Long id);
}
