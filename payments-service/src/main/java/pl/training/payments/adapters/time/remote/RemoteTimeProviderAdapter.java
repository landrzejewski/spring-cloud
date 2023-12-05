package pl.training.payments.adapters.time.remote;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import pl.training.payments.ports.TimeProvider;

import java.time.Instant;

@Primary
@Component
@RequiredArgsConstructor
public class RemoteTimeProviderAdapter implements TimeProvider {

    private final FeignTimeProvider timeProvider;
    //private final RestTemplateTimeProvider timeProvider;
    private final RemoteTimeRestMapper mapper;

    @Override
    public Instant getTimestamp() {
        try {
            return timeProvider.getTime()
                    .map(mapper::toDomain)
                    .orElseThrow(ServiceUnavailableException::new);
        } catch (RestClientException restClientException) {
            throw new ServiceUnavailableException();
        }
    }

}
