package com.example.service.order.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_ORDER_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 주문 번호입니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다."),
    PAYMENT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "결제 처리 실패."),
    OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "재고가 부족합니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    UNAUTHORIZED_ORDER_ACCESS(HttpStatus.UNAUTHORIZED, "주문에 접근할 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    // 생성자
    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
    public int getStatusCode() {
        return httpStatus.value();
    }

}
