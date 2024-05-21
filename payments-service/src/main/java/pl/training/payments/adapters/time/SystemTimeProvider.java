package pl.training.payments.adapters.time;

import org.springframework.stereotype.Service;
import pl.training.payments.ports.TimeProvider;

import java.time.Instant;

@Service
public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
