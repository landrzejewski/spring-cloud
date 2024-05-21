package pl.training.payments.ports;

import pl.training.payments.domain.PaymentUpdatedEvent;

public interface PaymentEventEmitter {

    void emit(PaymentUpdatedEvent event);

}
