package com.example.service.payment.infrastructure.order;

import com.example.service.payment.infrastructure.order.dto.OrderInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", url = "${domain.url}${service.url.order-service}")
public interface OrderClient {

    @GetMapping("/orders/{orderId}/payment-info")
    OrderInfoDto getOrderInfo(@PathVariable("orderId") Long orderId);
}
