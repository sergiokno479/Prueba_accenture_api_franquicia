package com.proyecto.franquicia.infrastructure.output.persistence.repository;

import com.proyecto.franquicia.infrastructure.output.persistence.entity.ProductEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, Long> {
    
    // Consulta nativa usando Window Functions para obtener el producto con más stock por sucursal
    @Query("""
        SELECT * FROM (
            SELECT p.*, ROW_NUMBER() OVER(PARTITION BY p.branch_id ORDER BY p.stock DESC) as rn 
            FROM products p 
            JOIN branches b ON p.branch_id = b.id 
            WHERE b.franchise_id = :franchiseId
        ) sub 
        WHERE rn = 1
    """)
    Flux<ProductEntity> findMaxStockProductsByFranchiseId(Long franchiseId);
}