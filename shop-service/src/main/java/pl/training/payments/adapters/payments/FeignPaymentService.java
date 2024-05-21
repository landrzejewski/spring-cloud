package pl.training.payments.adapters.payments;

import feign.FeignException.FeignClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.training.payments.ports.PaymentService;

import java.util.Currency;
import java.util.Optional;

@Primary
@Component
@Log
@RequiredArgsConstructor
public class FeignPaymentService implements PaymentService {

    private final PaymentsApi paymentsApi;

    @Override
    public boolean pay(long amount, Currency currency) {
        var paymentRequestDto = new PaymentRequestDto("%d %s".formatted(amount, currency));
        try {
            var paymentDto = paymentsApi.process(paymentRequestDto);
            return Optional.ofNullable(paymentDto)
                    .map(PaymentDto::isStarted)
                    .orElse(false);
        } catch (FeignClientException feignClientException) {
            log.info("Payment failed: " + feignClientException.getMessage());
        }
        return false;
    }

}
