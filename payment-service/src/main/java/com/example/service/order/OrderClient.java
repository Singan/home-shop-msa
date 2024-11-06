package com.example.service.order;

import com.example.service.order.dto.OrderInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "order-service", url = "${domain.url}${service.url.order-service}")
public interface OrderClient {

    @GetMapping("/{orderId}/payment-info")
    OrderInfoDto getOrderInfo(@PathVariable("orderId") Long orderId);
}
