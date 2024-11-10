package com.example.service.order.application.usecase;

import com.example.service.order.exception.*;
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
import com.example.service.order.infrastructure.repository.MemberRepository;
import com.example.service.order.infrastructure.repository.ProductRepository;
import com.example.service.order.infrastructure.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderRequestUseCase {

    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final MemberRepository memberRepository;

    public OrderPlaceResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        // 상품 정보 조회
        ProductDetailDto product = getProductDetails(orderRequestDto.productId());

        // 사용자 정보 조회
        MemberInfoDto member = getMemberProfile();

        // 주문 유효성 검증
        validateOrder(product, orderRequestDto);

        // 캐싱된 재고 감소
        if (stockRepository.decreaseStock(orderRequestDto.productId(), orderRequestDto.buyStock()) < 0) {
            throw new OrderStockOutOfException();
        }
        return saveOrder(orderRequestDto, product, member);
    }

    private ProductDetailDto getProductDetails(Long productId) {
        try {
            return productRepository.getProductDetail(productId);
        } catch (Exception e) {
            log.info("Error Message : {} , productId = {} , exception = {}", e.getMessage(), productId,e.getClass().toString());
            throw new OrderInvalidParameter();  // 상품 조회 실패시 예외
        }
    }

    private MemberInfoDto getMemberProfile() {
        try {
            return memberRepository.getMemberProfile();
        } catch (Exception e) {
            log.info("Error Message : {} ,  exception = {}", e.getMessage(),e.getClass().toString());
            throw new OrderUnAuthorizedException();  // 사용자 정보 조회 실패시 예외
        }
    }

    private void validateOrder(ProductDetailDto product, OrderRequestDto orderRequestDto) {
        if (!orderValidator.isValidOrder(product, orderRequestDto)) {
            throw new OrderNotFoundException();  // 주문 유효성 검사 실패시 예외
        }
    }


    protected OrderPlaceResponseDto saveOrder(OrderRequestDto orderRequestDto, ProductDetailDto product, MemberInfoDto member) {
        Order order = Order.builder()
                .memberId(orderRequestDto.userId())
                .productId(orderRequestDto.productId())
                .totalPrice(product.price() * orderRequestDto.buyStock())
                .quantity(orderRequestDto.buyStock())
                .status(OrderStatus.PENDING)
                .build();
        order = orderRepository.saveOrder(order);
        return OrderServiceFactory.createOrderPlaceResponseDto(order, product, orderRequestDto.userId(), member);
    }
}

