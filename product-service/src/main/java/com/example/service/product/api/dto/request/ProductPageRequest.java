package com.example.service.product.api.dto.request;

import org.springframework.web.bind.annotation.RequestParam;

public record ProductPageRequest(@RequestParam Long cursor ,
                                 @RequestParam(defaultValue = "10") int size) {
}
