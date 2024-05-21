package pl.training.payments.adapters.events;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service("paymentEventsConsumer")
@Log
public class PaymentStreamEventConsumer implements Consumer<PaymentEventDto> {

    @Override
    public void accept(PaymentEventDto event) {
        log.info("Payment status updated (id: %s)".formatted(event.getPaymentId()));
    }

}
