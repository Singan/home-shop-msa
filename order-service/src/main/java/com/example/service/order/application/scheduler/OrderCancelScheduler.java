package com.example.service.order.application.scheduler;

import com.example.service.order.application.usecase.OrderCancelUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class OrderCancelScheduler {

    private final OrderCancelUseCase orderCancelUseCase;

    @Scheduled(cron = "0 * * * * ?")  // 매분마다 실행
    public void updateOrderStatus() {
        LocalDateTime now = LocalDateTime.now();
        orderCancelUseCase.execute(now);
    }

}
