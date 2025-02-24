package pl.training.payments.ports;

import java.util.Currency;

public interface PaymentService {

    boolean pay(long amount, Currency currency);

}
