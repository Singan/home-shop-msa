package com.example.service.order.application.usecase;

import com.example.service.order.infrastructure.member.MemberClient;
import com.example.service.order.infrastructure.member.dto.MemberInfoDto;
import com.example.service.order.application.OrderServiceFactory;
import com.example.service.order.application.dto.request.OrderRequestDto;
import com.example.service.order.application.dto.response.OrderPlaceResponseDto;
import com.example.service.order.domain.Order;
import com.example.service.order.domain.OrderValidator;
import com.example.service.order.domain.enums.OrderStatus;
import com.example.service.order.domain.repository.OrderRepository;
import com.example.service.order.infrastructure.product.ProductClient;
import com.example.service.order.infrastructure.product.dto.ProductDetailDto;
import com.example.service.order.infrastructure.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderRequestUseCase {

    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final StockRepository stockRepository;
    private final MemberClient memberClient;

    public OrderPlaceResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        ProductDetailDto product = productClient.getProductDetail(orderRequestDto.productId());
        MemberInfoDto member = memberClient.getMemberProfile();
        if (!orderValidator.isValidOrder(product, orderRequestDto)) {
            throw new RuntimeException("주문이 유효하지 않습니다.");
        }

        return saveOrder(orderRequestDto, product, member);
    }

    @Transactional
    protected OrderPlaceResponseDto saveOrder(OrderRequestDto orderRequestDto, ProductDetailDto product, MemberInfoDto member) {
        Order order = Order.builder()
                .memberId(orderRequestDto.userId())
                .productId(orderRequestDto.productId())
                .totalPrice(product.price() * orderRequestDto.buyStock())
                .quantity(orderRequestDto.buyStock())
                .status(OrderStatus.PENDING)
                .build();

        order = orderRepository.saveOrder(order);
        stockRepository.decreaseStock(orderRequestDto.productId(), orderRequestDto.buyStock());
        return OrderServiceFactory.createOrderPlaceResponseDto(order, product, orderRequestDto.userId(), member);
    }
}

