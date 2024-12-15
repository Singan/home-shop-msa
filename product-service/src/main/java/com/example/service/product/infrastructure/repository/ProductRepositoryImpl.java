package com.example.service.product.infrastructure.repository;

import com.example.service.product.application.dto.cache.ProductDetailCacheDto;
import com.example.service.product.domain.Product;
import com.example.service.product.domain.repository.ProductRepository;
import com.example.service.product.infrastructure.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductRedisRepository productRedisRepository;
    private final ProductStockRedisRepository productStockRedisRepository;

    @Override
    public Long productSave(Product product) {
        return productJpaRepository.save(ProductEntity.fromProduct(product)).getId();
    }

    @Override
    public Slice<Product> findAllProducts(Long cursor, Pageable pageable) {
        return productJpaRepository.findByIdGreaterThan(cursor, pageable).map(ProductEntity::toProduct);
    }

    @Override
    public Optional<Product> findOne(Long id) {
        Optional<ProductDetailCacheDto> cachedProduct = productRedisRepository.findProduct(id);
        Optional<Integer> cachedStock = productStockRedisRepository.findStock(id);

        // 둘 중 하나라도 캐시가 없으면 DB에서 조회
        if (cachedProduct.isEmpty() || cachedStock.isEmpty()) {
            return productJpaRepository.findById(id)
                    .map(productEntity -> {
                        // 캐시가 없다면 저장
                        Product product = productEntity.toProduct();
                        if (cachedProduct.isEmpty()) {
                            productRedisRepository.save(product);
                        }
                        if (cachedStock.isEmpty()) {
                            productStockRedisRepository.saveStock(product);
                        }
                        return product;
                    });
        }

        return createProductFromCache(cachedProduct.get(), cachedStock.get());
    }
    private Optional<Product> createProductFromCache(ProductDetailCacheDto cachedProduct, Integer stock) {
        return Optional.of(Product.builder()
                .id(cachedProduct.getId())
                .price(cachedProduct.getPrice())
                .name(cachedProduct.getName())
                .description(cachedProduct.getDescription())
                .openDateTime(LocalDateTime.parse(cachedProduct.getOpenDateTime()))
                .stock(stock)
                .build());
    }

    @Override
    public void findByWarmingProductList(LocalDateTime localDateTime) {
        List<Product> productList =
                productJpaRepository.findByWarmingProductList(localDateTime)
                        .stream().map(ProductEntity::toProduct).toList();
        productRedisRepository.productStockWarmingUp(productList);
    }
}
