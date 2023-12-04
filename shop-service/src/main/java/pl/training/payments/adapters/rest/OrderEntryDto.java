package pl.training.payments.adapters.rest;

import lombok.Data;

@Data
public class OrderEntryDto {

    private Long productId;
    private int quantity;
    private long price;

}
