package pl.training.payments.ports;

public interface PaymentsService {

    boolean pay(long amount, String currency);

}
