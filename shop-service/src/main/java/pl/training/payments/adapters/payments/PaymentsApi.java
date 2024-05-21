package pl.training.payments.adapters.payments;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("PAYMENTS-SERVICE")
@RequestMapping("payments")
public interface PaymentsApi {

    @PostMapping
    PaymentDto process(@RequestBody PaymentRequestDto paymentRequestDto);

}
