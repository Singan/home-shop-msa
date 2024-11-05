package com.example.service.order.application.usecase;

import com.example.service.order.application.OrderServiceFactory;
import com.example.service.order.application.dto.request.OrderRequestDto;
import com.example.service.order.application.dto.response.OrderPlaceResponseDto;
import com.example.service.order.domain.Order;
import com.example.service.order.domain.OrderValidator;
import com.example.service.order.domain.enums.OrderStatus;
import com.example.service.order.domain.repository.OrderRepository;
import com.example.service.order.infrastructure.client.product.ProductClient;
import com.example.service.order.infrastructure.client.product.response.ProductDetailResponse;
import com.example.service.order.infrastructure.repository.ProductStockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderRequestUseCase {

    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final ProductStockRepository productStockRepository;

    public OrderPlaceResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        ProductDetailResponse product = productClient.getProductDetail(orderRequestDto.productId());

        if (!orderValidator.isValidOrder(product, orderRequestDto)) {
            throw new RuntimeException("주문이 유효하지 않습니다.");
        }

        return saveOrder(orderRequestDto, product);
    }

    @Transactional
    protected OrderPlaceResponseDto saveOrder(OrderRequestDto orderRequestDto, ProductDetailResponse product) {
        Order order = Order.builder()
                .memberId(orderRequestDto.userId())
                .productId(orderRequestDto.productId())
                .totalPrice(product.price() * orderRequestDto.buyStock())
                .quantity(orderRequestDto.buyStock())
                .status(OrderStatus.PENDING)
                .build();

        order = orderRepository.saveOrder(order);
        productStockRepository.decreaseStock(orderRequestDto.productId(), orderRequestDto.buyStock());
        return OrderServiceFactory.createOrderPlaceResponseDto(order, product, orderRequestDto.userId());
    }
}

