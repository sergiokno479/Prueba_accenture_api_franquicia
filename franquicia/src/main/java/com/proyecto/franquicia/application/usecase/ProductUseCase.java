package com.proyecto.franquicia.application.usecase;

import com.proyecto.franquicia.domain.model.Product;
import com.proyecto.franquicia.domain.port.out.ProductRepositoryPort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public Mono<Product> addProduct(Product product) { 
        return productRepositoryPort.save(product);
    } 

    public Mono<Void> removeProduct(Long id) {
        return productRepositoryPort.deleteById(id);
    }

    public Mono<Product> updateProductStock(Long id, Integer newStock) {
        if (newStock < 0) {
            return Mono.error(new IllegalArgumentException("El stock no puede ser negativo"));
        }
        return productRepositoryPort.updateStock(id, newStock);
    }

    public Mono<Product> updateProductName(Long id, String newName) {
        return productRepositoryPort.updateName(id, newName);
    }

    public Flux<Product> getProductsWithMaxStockPerBranch(Long franchiseId) {
        return productRepositoryPort.findMaxStockProductsByFranchiseId(franchiseId);
    }
}