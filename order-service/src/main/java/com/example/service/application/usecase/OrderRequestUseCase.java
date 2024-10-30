package com.example.service.application.usecase;

import com.example.service.application.OrderServiceFactory;
import com.example.service.application.dto.request.OrderRequestDto;
import com.example.service.application.dto.response.OrderPlaceResponseDto;
import com.example.service.domain.Order;
import com.example.service.domain.OrderValidator;
import com.example.service.domain.enums.OrderStatus;
import com.example.service.domain.repository.OrderRepository;
import com.example.service.infrastructure.client.product.ProductClient;
import com.example.service.infrastructure.client.product.response.ProductDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderRequestUseCase {
    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderPlaceResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        ProductDetailResponse product = productClient.getProductDetail(orderRequestDto.productId());

        // 가격 정보는 DB에서 가져오므로 orderRequestDto에는 포함되지 않아도 됨
        if (!orderValidator.isValidOrder(product, orderRequestDto)) {
            throw new RuntimeException("주문이 유효하지 않습니다.");
        }

        Order order = Order.builder()
                .memberId(orderRequestDto.userId())
                .productId(orderRequestDto.productId())
                .totalPrice(product.price() * orderRequestDto.buyStock())
                .quantity(orderRequestDto.buyStock())
                .status(OrderStatus.PENDING)
                .build();

        order = orderRepository.placeOrder(order);




        return OrderServiceFactory.createOrderPlaceResponseDto(order , product, orderRequestDto.userId());
    }

}
