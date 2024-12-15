package com.example.service.payment.application.usecase;

import com.example.service.payment.application.PaymentServiceFactory;
import com.example.service.payment.application.dto.request.PaymentConfirmRequestDto;
import com.example.service.payment.application.dto.response.PaymentConfirmResponseDto;
import com.example.service.payment.domain.Payment;
import com.example.service.payment.domain.enums.PaymentStatus;
import com.example.service.payment.domain.repository.PGRepository;
import com.example.service.payment.domain.repository.PaymentRepository;
import com.example.service.payment.infrastructure.order.OrderClient;
import com.example.service.payment.infrastructure.order.OrderKafkaProducer;
import com.example.service.payment.infrastructure.order.dto.OrderInfoDto;
import com.example.service.payment.infrastructure.product.ProductKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentConfirmUseCase {
    private final PGRepository pgRepository;
    private final PaymentRepository paymentRepository;
    private final ProductKafkaProducer productKafkaProducer;
    private final OrderKafkaProducer orderKafkaProducer;
    private final OrderClient orderClient;


    private PaymentStatus paymentGateSendConfirm() {
        try {
            return pgRepository.paymentGatewayToSend(null) ? PaymentStatus.success : PaymentStatus.fail;
        } catch (Exception e) {
            log.error("결제 실패");
            return PaymentStatus.fail;
        }
    }
    private void validateOrderTime(LocalDateTime orderTime) {
        if (LocalDateTime.now().isAfter(orderTime.plusMinutes(15))) {
            throw new IllegalArgumentException("결제 가능 시간이 초과되었습니다.");
        }
    }

    private void sendStockDecreaseMessage(OrderInfoDto orderInfoDto) {
        productKafkaProducer.sendStockDecreaseMessage(orderInfoDto.getProductId() , orderInfoDto.getQuantity());
    }

    @Transactional
    public PaymentConfirmResponseDto paymentConfirm(PaymentConfirmRequestDto paymentConfirmRequestDto) {

        OrderInfoDto orderInfoDto = orderClient.getOrderInfo(paymentConfirmRequestDto.orderId()); // 오더 조회

        validateOrderTime(orderInfoDto.getOrderTime());

        PaymentStatus paymentStatus = paymentGateSendConfirm(); // PG 사 요청 성공 여부

        if (paymentStatus.equals(PaymentStatus.success)) { // 성공 했다면 재고 차감을 위해 메시지를 발행한다.
            sendStockDecreaseMessage(orderInfoDto);
            orderKafkaProducer.sendOrder(orderInfoDto.getOrderId() , true); // Order 도 업데이트 해준다.
        }


        Payment payment = Payment.builder()
                .orderId(paymentConfirmRequestDto.orderId())
                .status(paymentStatus)
                .price(orderInfoDto.getOrderPrice())
                .build();

        payment = paymentRepository.save(payment);

        return PaymentServiceFactory.createPaymentConfirmResponseDto(payment);
    }

}
