package pl.training.payments.adapters.time.remote;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Primary
@Component
@RequiredArgsConstructor
public class FeignTimeProvider {

    private final TimeServiceApi timeServiceApi;

    public Optional<TimestampDto> getTime() {
        return Optional.ofNullable(timeServiceApi.getTime());
    }

}
