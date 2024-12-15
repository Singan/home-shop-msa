package com.example.service.order.application.usecase;

import com.example.service.order.application.OrderServiceFactory;
import com.example.service.order.application.dto.request.OrderRequestDto;
import com.example.service.order.application.dto.response.OrderPlaceResponseDto;
import com.example.service.order.domain.Order;
import com.example.service.order.domain.OrderValidator;
import com.example.service.order.domain.enums.OrderStatus;
import com.example.service.order.domain.repository.OrderRepository;
import com.example.service.order.exception.OrderInvalidParameter;
import com.example.service.order.exception.OrderNotFoundException;
import com.example.service.order.exception.OrderStockOutOfException;
import com.example.service.order.exception.OrderUnAuthorizedException;
import com.example.service.order.infrastructure.member.dto.MemberInfoDto;
import com.example.service.order.infrastructure.product.dto.ProductDetailDto;
import com.example.service.order.infrastructure.repository.MemberRepository;
import com.example.service.order.infrastructure.repository.ProductRepository;
import com.example.service.order.infrastructure.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

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
        CompletableFuture<ProductDetailDto> productFuture = getProductDetails(orderRequestDto.productId());

        // 사용자 정보 조회
        CompletableFuture<MemberInfoDto> memberFuture = getMemberProfile();

        //비동기 결과 검증
        productFuture.thenAccept(product -> validateOrder(product, orderRequestDto));

        return productFuture.thenCombine(memberFuture, (product, member) -> {
            if (stockRepository.decreaseStock(orderRequestDto.productId(), orderRequestDto.buyStock()) < 0) {
                throw new OrderStockOutOfException();
            }
            // 주문 저장
            return saveOrder(orderRequestDto, product, member);
        }).join();
    }

    private CompletableFuture<ProductDetailDto> getProductDetails(Long productId) {
        try {
            return productRepository.getProductDetail(productId);
        } catch (Exception e) {
            log.info("Error Message : {} , productId = {} , exception = {}", e.getMessage(), productId, e.getClass().toString());
            throw new OrderInvalidParameter();  // 상품 조회 실패시 예외
        }
    }

    private CompletableFuture<MemberInfoDto> getMemberProfile() {
        try {
            return memberRepository.getMemberProfile();
        } catch (Exception e) {
            log.info("Error Message : {} ,  exception = {}", e.getMessage(), e.getClass().toString());
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

