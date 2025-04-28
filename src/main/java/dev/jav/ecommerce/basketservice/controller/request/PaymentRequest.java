package dev.jav.ecommerce.basketservice.controller.request;

import dev.jav.ecommerce.basketservice.model.PaymentMethod;

public record PaymentRequest(PaymentMethod paymentMethod) {
}
