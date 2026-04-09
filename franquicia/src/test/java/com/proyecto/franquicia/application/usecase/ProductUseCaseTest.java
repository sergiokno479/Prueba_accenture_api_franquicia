package com.proyecto.franquicia.application.usecase;

import com.proyecto.franquicia.domain.model.Product;
import com.proyecto.franquicia.domain.port.out.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

// Le decimos a JUnit que usaremos Mockito
@ExtendWith(MockitoExtension.class)
class ProductUseCaseTest {

    @Mock // base de datos falsa
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks // Caso de Uso
    private ProductUseCase productUseCase;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product(1L, "Café Latte", 20, 1L);
    }

    @Test
    void updateProductStock_withValidStock_shouldReturnUpdatedProduct() {
        // Arrange 
        Long productId = 1L;
        Integer newStock = 50;
        Product updatedProduct = new Product(1L, "Café Latte", 50, 1L);
        
        Mockito.when(productRepositoryPort.updateStock(productId, newStock)).thenReturn(Mono.just(updatedProduct));

        // Act 
        Mono<Product> result = productUseCase.updateProductStock(productId, newStock);

        // Assert 
        StepVerifier.create(result).expectNextMatches(product -> product.stock() == 50) .verifyComplete();
    }

    @Test
    void updateProductStock_withNegativeStock_shouldReturnError() {
        // Arrange
        Long productId = 1L;
        Integer negativeStock = -10;

        // Act
        Mono<Product> result = productUseCase.updateProductStock(productId, negativeStock);

        // Assert
        StepVerifier.create(result).expectErrorMessage("El stock no puede ser negativo") .verify();
        
        Mockito.verify(productRepositoryPort, Mockito.never()).updateStock(Mockito.anyLong(), Mockito.anyInt());
    }
}