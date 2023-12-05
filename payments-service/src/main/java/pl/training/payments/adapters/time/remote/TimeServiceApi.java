package pl.training.payments.adapters.time.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "timeProvider", url = "${api.time}")
public interface TimeServiceApi {

    @GetMapping
    TimestampDto getTime();


}
