package com.proyecto.franquicia.infrastructure.config;

import com.proyecto.franquicia.application.usecase.BranchUseCase;
import com.proyecto.franquicia.application.usecase.FranchiseUseCase;
import com.proyecto.franquicia.application.usecase.ProductUseCase;
import com.proyecto.franquicia.domain.port.out.BranchRepositoryPort;
import com.proyecto.franquicia.domain.port.out.FranchiseRepositoryPort;
import com.proyecto.franquicia.domain.port.out.ProductRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public FranchiseUseCase franchiseUseCase(FranchiseRepositoryPort port) {
        return new FranchiseUseCase(port);
    }

    @Bean
    public BranchUseCase branchUseCase(BranchRepositoryPort port) {
        return new BranchUseCase(port);
    }

    @Bean
    public ProductUseCase productUseCase(ProductRepositoryPort port) {
        return new ProductUseCase(port);
    }
}