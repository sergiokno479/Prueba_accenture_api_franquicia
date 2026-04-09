package com.proyecto.franquicia.infrastructure.input.rest;

import com.proyecto.franquicia.application.usecase.BranchUseCase;
import com.proyecto.franquicia.domain.model.Branch;
import com.proyecto.franquicia.infrastructure.input.rest.dto.BranchRequest;
import com.proyecto.franquicia.infrastructure.input.rest.dto.BranchResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchUseCase branchUseCase;

    public BranchController(BranchUseCase branchUseCase) {
        this.branchUseCase = branchUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BranchResponse> addBranch(@RequestBody BranchRequest request) {
        Branch branchToCreate = new Branch(null, request.name(), request.franchiseId());

        return branchUseCase.addBranch(branchToCreate).map(created -> new BranchResponse(created.id(), created.name(), created.franchiseId()));
    }

    @PatchMapping("/{id}/name")
    public Mono<BranchResponse> updateBranchName(@PathVariable Long id, @RequestBody BranchRequest request) {
        return branchUseCase.updateBranchName(id, request.name()).map(updated -> new BranchResponse(updated.id(), updated.name(), updated.franchiseId()));
    }
}