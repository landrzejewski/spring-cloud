package pl.training.payments.adapters.time.remote;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestTemplateTimeProvider {

    private final RestTemplate restTemplate;

    @Value("${api.time}")
    @Setter
    private String timeApi;

    public Optional<TimestampDto> getTime() {
        return Optional.ofNullable(restTemplate.getForObject(timeApi, TimestampDto.class));
    }

}
