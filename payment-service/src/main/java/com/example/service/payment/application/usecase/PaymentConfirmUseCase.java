package com.example.service.payment.application.usecase;

import com.example.service.order.OrderClient;
import com.example.service.order.dto.OrderInfoDto;
import com.example.service.payment.application.PaymentServiceFactory;
import com.example.service.payment.application.dto.message.StockDecreaseDto;
import com.example.service.payment.application.dto.request.PaymentConfirmRequestDto;
import com.example.service.payment.application.dto.response.PaymentConfirmResponseDto;
import com.example.service.payment.domain.Payment;
import com.example.service.payment.domain.enums.PaymentStatus;
import com.example.service.payment.domain.repository.PGRepository;
import com.example.service.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentConfirmUseCase {
    private final PGRepository pgRepository;
    private final PaymentRepository paymentRepository;
    private final KafkaTemplate<String, StockDecreaseDto> kafkaTemplate;

    private final OrderClient orderClient;


    private PaymentStatus paymentGateSendConfirm() {
        PaymentStatus paymentStatus;
        try {
            if (!pgRepository.paymentGatewayToSend(null)) {
                throw new IllegalStateException("결제 실패");
            }

            paymentStatus = PaymentStatus.success;
        } catch (Exception e) {
            paymentStatus = PaymentStatus.fail;
        }
        return paymentStatus;
    }

    @Transactional
    public PaymentConfirmResponseDto paymentConfirm(PaymentConfirmRequestDto paymentConfirmRequestDto) {


        OrderInfoDto orderInfoDto = orderClient.getOrderInfo(paymentConfirmRequestDto.orderId()); // 오더 조회

        PaymentStatus paymentStatus = paymentGateSendConfirm(); // PG 사 요청 성공 여부

        if (paymentStatus.equals(PaymentStatus.success)) { // 성공 했다면 재고 차감을 위해 메시지를 발행한다.
            kafkaTemplate.send("product-stock-topics",
                    new StockDecreaseDto(orderInfoDto.getProductId(), orderInfoDto.getQuantity())
            );
        }

        Payment payment = Payment.builder()
                .orderId(paymentConfirmRequestDto.orderId())
                .status(paymentStatus)
                .price(paymentConfirmRequestDto.price())
                .build();

        payment = paymentRepository.save(payment);

        return PaymentServiceFactory.createPaymentConfirmResponseDto(payment);
    }

}
