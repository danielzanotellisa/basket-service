package dev.jav.ecommerce.basketservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "basket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Basket {

    @Id
    private String id;
    private Long client;
    private BigDecimal totalPrice;
    private List<Product> products;
    private Status status;


    public void calculateTotalPrice() {

        this.totalPrice = products.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

}
