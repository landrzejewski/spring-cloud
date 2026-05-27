package pl.training.payments.ports;

import pl.training.payments.domain.Order;

public interface ShopService {

    void place(Order order);

}
