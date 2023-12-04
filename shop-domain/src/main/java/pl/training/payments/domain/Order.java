package pl.training.payments.domain;

import lombok.Value;

import java.util.List;

@Value
public class Order {

    Long id;
    List<OrderEntry> entries;

    public long getTotalValue() {
        return entries.stream()
                .map(OrderEntry::getValue)
                .reduce(0L, Long::sum);
    }

}
