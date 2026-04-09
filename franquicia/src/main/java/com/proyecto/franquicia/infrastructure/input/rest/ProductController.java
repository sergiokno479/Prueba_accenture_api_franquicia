package com.proyecto.franquicia.infrastructure.input.rest;

import com.proyecto.franquicia.application.usecase.ProductUseCase;
import com.proyecto.franquicia.domain.model.Product;
import com.proyecto.franquicia.infrastructure.input.rest.dto.ProductRequest;
import com.proyecto.franquicia.infrastructure.input.rest.dto.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        Product productToCreate = new Product(null, request.name(), request.stock(), request.branchId());
        
        return productUseCase.addProduct(productToCreate).map(this::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> removeProduct(@PathVariable Long id) {
        return productUseCase.removeProduct(id);
    }

    @PatchMapping("/{id}/stock")
    public Mono<ProductResponse> updateProductStock(@PathVariable Long id, @RequestBody ProductRequest request) {
        return productUseCase.updateProductStock(id, request.stock()).map(this::toResponse);
    }

    @PatchMapping("/{id}/name")
    public Mono<ProductResponse> updateProductName( @PathVariable Long id, @RequestBody ProductRequest request) {
        return productUseCase.updateProductName(id, request.name()).map(this::toResponse);
    }

    
    @GetMapping("/max-stock/franchise/{franchiseId}")
    public Flux<ProductResponse> getProductsWithMaxStockPerBranch(@PathVariable Long franchiseId) {
        return productUseCase.getProductsWithMaxStockPerBranch(franchiseId).map(this::toResponse);
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(product.id(), product.name(), product.stock(), product.branchId());
    }
}