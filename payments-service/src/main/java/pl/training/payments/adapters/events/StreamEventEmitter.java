package pl.training.payments.adapters.events;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import pl.training.payments.domain.PaymentStatusUpdatedEvent;
import pl.training.payments.ports.PaymentEventEmitter;

@Component
@RequiredArgsConstructor
public class StreamEventEmitter implements PaymentEventEmitter {

    private static final String BING_NAME = "payment-updates-out-0";

    private final StreamBridge streamBridge;

    @Override
    public void emit(PaymentStatusUpdatedEvent event) {
        var message = new PaymentUpdatedMessage(event.getPaymentId(), event.getNewStatus(), "STATUS_UPDATED");
        streamBridge.send(BING_NAME, message);
    }

}
