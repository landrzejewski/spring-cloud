package pl.training.payments.adapters.events;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import pl.training.payments.domain.PaymentUpdatedEvent;
import pl.training.payments.ports.EventEmitter;

@Component
@RequiredArgsConstructor
public class StreamEventEmitter implements EventEmitter {

    private static final String PAYMENTS_BINDING_NAME = "payments-out-0";
    private static final String EVENT_TYPE = "UPDATE";

    private final StreamBridge streamBridge;
    private final EventPaymentMapper mapper;

    @Override
    public void emit(PaymentUpdatedEvent event) {
        var paymentEventDto = mapper.toDto(event);
        paymentEventDto.setType(EVENT_TYPE);
        streamBridge.send(PAYMENTS_BINDING_NAME, paymentEventDto);
    }

}
