package dev.jav.ecommerce.basketservice.model;

public enum PaymentMethod {

    PIX("pix"),
    DEBIT_CARD("debit_card"),
    CREDIT_CARD("credit_card");

    private final String paymentMethod;

    PaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
