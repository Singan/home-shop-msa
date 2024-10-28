package com.example.service.application;

import com.example.service.api.request.OrderRequest;
import com.example.service.application.interfaces.OrderService;
import com.example.service.application.usecase.OrderRequestUseCase;
import com.example.service.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRequestUseCase orderRequestUseCase;
    @Override
    public Long orderRequest(OrderRequest orderRequest) {
        return 0L;
    }
}
