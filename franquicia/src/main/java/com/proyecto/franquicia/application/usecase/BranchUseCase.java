package com.proyecto.franquicia.application.usecase;

import com.proyecto.franquicia.domain.model.Branch;
import com.proyecto.franquicia.domain.port.out.BranchRepositoryPort;
import reactor.core.publisher.Mono;

public class BranchUseCase {

    private final BranchRepositoryPort branchRepositoryPort;

    public BranchUseCase(BranchRepositoryPort branchRepositoryPort) {
        this.branchRepositoryPort = branchRepositoryPort;
    }

    public Mono<Branch> addBranch(Branch branch) {
        return branchRepositoryPort.save(branch);
    }

    public Mono<Branch> updateBranchName(Long id, String newName) {
        return branchRepositoryPort.updateName(id, newName);
    }
}