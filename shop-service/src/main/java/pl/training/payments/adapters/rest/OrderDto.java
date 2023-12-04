package pl.training.payments.adapters.rest;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private List<OrderEntryDto> entries;

}
