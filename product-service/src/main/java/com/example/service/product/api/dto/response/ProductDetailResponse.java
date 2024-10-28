package com.example.service.product.api.dto.response;

public record ProductDetailResponse(Long id,

                                    Integer stock,

                                    String name,


                                    Integer price,

                                    String openDateTime,
                                    String leftTime,
                                    String description) {
}
