package pl.training.payments.ports;

import pl.training.payments.domain.PaymentStatusUpdatedEvent;

public interface PaymentEventEmitter {

    void emit(PaymentStatusUpdatedEvent event);

}
