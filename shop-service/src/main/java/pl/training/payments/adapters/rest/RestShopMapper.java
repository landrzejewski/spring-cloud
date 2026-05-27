package pl.training.payments.adapters.rest;

import org.mapstruct.Mapper;
import pl.training.payments.domain.Order;

@Mapper(componentModel = "spring")
public interface RestShopMapper {

    Order toDomain(OrderDto orderDto);

}
