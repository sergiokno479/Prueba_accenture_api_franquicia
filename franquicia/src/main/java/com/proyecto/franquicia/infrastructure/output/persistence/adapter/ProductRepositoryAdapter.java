package com.proyecto.franquicia.infrastructure.output.persistence.adapter;

import com.proyecto.franquicia.domain.model.Product;
import com.proyecto.franquicia.domain.port.out.ProductRepositoryPort;
import com.proyecto.franquicia.infrastructure.output.persistence.entity.ProductEntity;
import com.proyecto.franquicia.infrastructure.output.persistence.repository.ProductRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductRepository repository;

    public ProductRepositoryAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Product> save(Product product) {
        ProductEntity entity = ProductEntity.builder()
                .id(product.id())
                .name(product.name())
                .stock(product.stock())
                .branchId(product.branchId())
                .build();

        return repository.save(entity).map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Product> updateStock(Long id, Integer newStock) {
        return repository.findById(id)
                .flatMap(entity -> {
                    entity.setStock(newStock);
                    return repository.save(entity);
                })
                .map(this::toDomain);
    }

    @Override
    public Mono<Product> updateName(Long id, String newName) {
        return repository.findById(id)
                .flatMap(entity -> {
                    entity.setName(newName);
                    return repository.save(entity);
                })
                .map(this::toDomain);
    }

    @Override
    public Flux<Product> findMaxStockProductsByFranchiseId(Long franchiseId) {
        return repository.findMaxStockProductsByFranchiseId(franchiseId)
                .map(this::toDomain);
    }

    private Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getStock(),
                entity.getBranchId()
        );
    }
}