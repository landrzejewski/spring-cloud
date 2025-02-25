package pl.training.payments.adapters.time;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FeignTimeProvider {

    private final TimeServiceApi timeServiceApi;

    public Optional<TimestampDto> getTime() {
        return Optional.ofNullable(timeServiceApi.getTime());
    }

}
