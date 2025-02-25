package pl.training.payments.adapters.time;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.training.payments.ports.TimeProvider;

import java.time.Instant;

@Primary
@Service
public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
