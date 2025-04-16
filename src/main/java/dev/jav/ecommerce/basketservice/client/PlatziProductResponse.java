package dev.jav.ecommerce.basketservice.client;

import java.math.BigDecimal;

public record PlatziProductResponse(Long id, String title, BigDecimal price) {
}
