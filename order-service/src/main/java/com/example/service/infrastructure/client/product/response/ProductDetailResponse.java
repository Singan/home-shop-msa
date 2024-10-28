package com.example.service.infrastructure.client.product.response;

public record ProductDetailResponse(Long id,

                                    Integer stock,

                                    String name,


                                    Integer price,

                                    String openDateTime,
                                    String leftTime,
                                    String description) {
}