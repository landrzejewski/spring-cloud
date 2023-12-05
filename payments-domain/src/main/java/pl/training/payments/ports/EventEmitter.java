package pl.training.payments.ports;

import pl.training.payments.domain.PaymentUpdatedEvent;

public interface EventEmitter {

    void emit(PaymentUpdatedEvent event);

}
