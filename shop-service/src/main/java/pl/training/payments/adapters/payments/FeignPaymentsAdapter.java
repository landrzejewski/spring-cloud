package pl.training.payments.adapters.payments;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import pl.training.payments.ports.PaymentsService;

import java.util.Optional;

@Primary
@Log
@Component
@RequiredArgsConstructor
public class FeignPaymentsAdapter implements PaymentsService {

    private final PaymentsApi paymentsApi;

    @Retry(fallbackMethod = "payFallback", name = "payments")
    @Override
    public boolean pay(long amount, String currency) {
        var paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setValue("%d %s".formatted(amount, currency));
       // try {
            var paymentsDto = paymentsApi.pay(paymentRequestDto);
            return Optional.ofNullable(paymentsDto)
                    .map(payment -> payment.status.equals("STARTED"))
                    .orElse(false);
        //} catch (RestClientException restClientException) {
        //    log.info("Payment failed: " + restClientException.getMessage());
        //}
        //return false;
    }

    public boolean payFallback(long amount, String currency, RuntimeException runtimeException) {
        log.info("Fallback....");
        return false;
    }

}
