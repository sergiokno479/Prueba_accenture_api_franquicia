package com.proyecto.franquicia.domain.port.out;

import com.proyecto.franquicia.domain.model.Branch;
import reactor.core.publisher.Mono;

public interface BranchRepositoryPort {
    Mono<Branch> save(Branch branch);
    Mono<Branch> updateName(Long id, String newName);
}