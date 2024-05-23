package pl.training.payments.adapters.payments;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient("PAYMENTS-SERVICE")
@FeignClient(value = "PAYMENTS-SERVICE", url = "${api.payments}")
public interface PaymentsApi {

    @PostMapping("payments")
    PaymentDto process(@RequestBody PaymentRequestDto paymentRequestDto);

}
