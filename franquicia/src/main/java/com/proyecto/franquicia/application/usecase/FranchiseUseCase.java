package com.proyecto.franquicia.application.usecase;

import com.proyecto.franquicia.domain.model.Franchise;
import com.proyecto.franquicia.domain.port.out.FranchiseRepositoryPort;
import reactor.core.publisher.Mono;

public class FranchiseUseCase {
    
    private final FranchiseRepositoryPort franchiseRepositoryPort;

    public FranchiseUseCase(FranchiseRepositoryPort franchiseRepositoryPort) {
        this.franchiseRepositoryPort = franchiseRepositoryPort;
    }

    public Mono<Franchise> addFranchise(Franchise franchise) {
        return franchiseRepositoryPort.save(franchise);
    }

    public Mono<Franchise> updateFranchiseName(Long id, String newName) {
        return franchiseRepositoryPort.updateName(id, newName);
    }
}