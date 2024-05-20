package pl.training.payments.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pl.training.payments.ports.ShopService;

@Log
@RequiredArgsConstructor
public class OrderProcessor implements ShopService {

    private static final String DEFAULT_CURRENCY = "PLN";

    @Override
    public void place(Order order) {
        var totalValue = order.getTotalValue();
        log.info("New order with total value: %d %s".formatted(totalValue, DEFAULT_CURRENCY));
    }

}
