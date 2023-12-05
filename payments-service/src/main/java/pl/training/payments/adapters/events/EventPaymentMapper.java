package pl.training.payments.adapters.events;

import org.mapstruct.Mapper;
import pl.training.payments.domain.PaymentUpdatedEvent;

@Mapper(componentModel = "spring")
public interface EventPaymentMapper {

    PaymentEventDto toDto(PaymentUpdatedEvent event);

}
