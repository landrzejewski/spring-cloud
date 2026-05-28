package pl.training.payments.adapters.payments;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.training.payments.ports.PaymentService;

import java.net.URI;
import java.util.Currency;
import java.util.Optional;

@Primary
@Log
@Component
@RequiredArgsConstructor
public class FeignPaymentService implements PaymentService {

    private final PaymentsApi paymentsApi;

    @Override
    public boolean pay(long amount, Currency currency) {
        var paymentRequestDto = new PaymentRequestDto("%d %s".formatted(amount, currency.getCurrencyCode()));
        try {
            var paymentDto = paymentsApi.process(paymentRequestDto);
            return Optional.ofNullable(paymentDto)
                    .map(PaymentDto::isStarted)
                    .orElse(false);
        } catch (FeignException e) {
            log.info("Payment request could not be sent to payment service " + e.getMessage());
        }
        return false;
    }

}
