package com.proyecto.franquicia.infrastructure.input.rest;

import com.proyecto.franquicia.application.usecase.FranchiseUseCase;
import com.proyecto.franquicia.domain.model.Franchise;
import com.proyecto.franquicia.infrastructure.input.rest.dto.FranchiseRequest;
import com.proyecto.franquicia.infrastructure.input.rest.dto.FranchiseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final FranchiseUseCase franchiseUseCase;

    public FranchiseController(FranchiseUseCase franchiseUseCase) {
        this.franchiseUseCase = franchiseUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FranchiseResponse> addFranchise(@RequestBody FranchiseRequest request) {
        // Mapeo manual de DTO a Dominio
        Franchise franchiseToCreate = new Franchise(null, request.name());
        
        return franchiseUseCase.addFranchise(franchiseToCreate).map(created -> new FranchiseResponse(created.id(), created.name()));
    }

    @PatchMapping("/{id}/name")
    public Mono<FranchiseResponse> updateFranchiseName(@PathVariable Long id, @RequestBody FranchiseRequest request) {
        
        return franchiseUseCase.updateFranchiseName(id, request.name()).map(updated -> new FranchiseResponse(updated.id(), updated.name()));
    }
}