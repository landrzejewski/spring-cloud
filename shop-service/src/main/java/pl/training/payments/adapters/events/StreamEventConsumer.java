package pl.training.payments.adapters.events;

import lombok.extern.java.Log;

import java.util.function.Consumer;

@Log
//@Component("paymentEventsConsumer")
public class StreamEventConsumer implements Consumer<PaymentEventDto> {

    @Override
    public void accept(PaymentEventDto paymentEventDto) {
        log.info("New payment update: " + paymentEventDto.getPaymentId());
    }

}
