package pl.training.payments.adapters.events;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component("paymentEventsConsumer")
@Log
public class PaymentStreamEventConsumer implements Consumer<PaymentEventDto> {

    @Override
    public void accept(PaymentEventDto paymentEventDto) {
        log.info("Received Payment Event: " + paymentEventDto);
    }

}
