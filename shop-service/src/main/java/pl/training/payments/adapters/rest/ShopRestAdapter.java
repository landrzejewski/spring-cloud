package pl.training.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.training.payments.ports.ShopService;

@RequestMapping("orders")
@RestController
@RequiredArgsConstructor
public class ShopRestAdapter {

    private final ShopService shopService;
    private final RestShopMapper mapper;

   @PostMapping
   public ResponseEntity<Void> process(@RequestBody OrderDto orderDto) {
        var order = mapper.toDomain(orderDto);
        shopService.place(order);
        return ResponseEntity.noContent()
                .build();
   }

}
