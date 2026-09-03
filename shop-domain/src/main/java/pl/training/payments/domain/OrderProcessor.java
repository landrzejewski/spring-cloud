package pl.training.payments.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pl.training.payments.ports.PaymentService;
import pl.training.payments.ports.ShopService;

import java.util.Currency;

@Log
@RequiredArgsConstructor
public class OrderProcessor implements ShopService {

    private static final String DEFAULT_CURRENCY = "PLN";

    private final PaymentService paymentService;

    @Override
    public void place(Order order) {
        var totalValue = order.getTotalValue();
        var paymentResult = paymentService.pay(totalValue, Currency.getInstance(DEFAULT_CURRENCY));
        log.info("Payment result: " + (paymentResult ? "Success" : "Failure"));
        log.info("New order with total value: %d %s".formatted(totalValue, DEFAULT_CURRENCY));
    }

}
