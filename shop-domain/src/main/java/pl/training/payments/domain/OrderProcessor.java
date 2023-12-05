package pl.training.payments.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pl.training.payments.ports.PaymentsService;
import pl.training.payments.ports.ShopService;

@Log
@RequiredArgsConstructor
public class OrderProcessor implements ShopService {

    private static final String DEFAULT_CURRENCY = "PLN";

    private final PaymentsService paymentsService;

    @Override
    public void place(Order order) {
        var totalValue = order.getTotalValue();
        log.info("New order with total value: %d %s".formatted(totalValue, DEFAULT_CURRENCY));
        var paymentStatus = paymentsService.pay(totalValue, DEFAULT_CURRENCY);
        log.info("Order payment status: " + paymentStatus);
    }

}
