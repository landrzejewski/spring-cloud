package pl.training.payments.adapters.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ServiceResolver {

    private final DiscoveryClient discoveryClient;
    private final Random random = new Random();

    public Optional<URI> get(String serviceId) {
        var instances = discoveryClient.getInstances(serviceId);
        if (instances.isEmpty()) {
            return Optional.empty();
        }
        var instanceIndex = random.nextInt(instances.size());
        return Optional.ofNullable(instances.get(instanceIndex))
                .map(ServiceInstance::getUri);
    }

}
