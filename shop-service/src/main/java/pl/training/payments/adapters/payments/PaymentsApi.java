package pl.training.payments.adapters.payments;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("PAYMENTS-SERVICE")
public interface PaymentsApi {

    @PostMapping("payments")
    PaymentDto pay(@RequestBody PaymentRequestDto paymentRequestDto);

}
