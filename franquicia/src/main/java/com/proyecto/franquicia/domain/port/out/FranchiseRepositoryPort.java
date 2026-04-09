package com.proyecto.franquicia.domain.port.out;

import com.proyecto.franquicia.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface FranchiseRepositoryPort {
    Mono<Franchise> save(Franchise franchise);
    Mono<Franchise> updateName(Long id, String newName); 
}