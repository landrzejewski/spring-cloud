package pl.training.payments.adapters.events;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import pl.training.payments.domain.PaymentUpdatedEvent;
import pl.training.payments.ports.PaymentEventEmitter;

@Component
@RequiredArgsConstructor
public class StreamEventEmitter implements PaymentEventEmitter {

    private static final String PAYMENTS_BINDING_NAME = "payments-out-0";
    private static final String EVENT_TYPE = "STATUS-UPDATE";

    private final StreamBridge streamBridge;

    @Override
    public void emit(PaymentUpdatedEvent event) {
        var paymentEventDto = new PaymentEventDto(event.getPaymentId(), EVENT_TYPE, event.getNewStatus());
        streamBridge.send(PAYMENTS_BINDING_NAME, paymentEventDto);
    }

}
