package dev.jav.ecommerce.basketservice.model;

public enum Status {

    OPEN("open"),
    CLOSED("closed");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
