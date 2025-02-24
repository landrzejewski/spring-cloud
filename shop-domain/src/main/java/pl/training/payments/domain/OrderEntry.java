package pl.training.payments.domain;

import lombok.Value;

@Value
public class OrderEntry {

    Long productId;
    int quantity;
    long price;

    public long getValue() {
        return quantity * price;
    }

}
