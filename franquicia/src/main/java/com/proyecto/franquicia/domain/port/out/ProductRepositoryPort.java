package com.proyecto.franquicia.domain.port.out;

import com.proyecto.franquicia.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepositoryPort {
    Mono<Product> save(Product product);
    Mono<Void> deleteById(Long id);
    Mono<Product> updateStock(Long id, Integer newStock);
    Mono<Product> updateName(Long id, String newName);
    Flux<Product> findMaxStockProductsByFranchiseId(Long franchiseId);
}