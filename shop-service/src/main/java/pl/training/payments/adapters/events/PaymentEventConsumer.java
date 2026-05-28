package pl.training.payments.adapters.events;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Log
@Component
public class PaymentEventConsumer implements Consumer<PaymentUpdatedMessage> {

    @Override
    public void accept(PaymentUpdatedMessage paymentUpdatedMessage) {
        log.info("Received payment updated message: " + paymentUpdatedMessage);
    }

}
