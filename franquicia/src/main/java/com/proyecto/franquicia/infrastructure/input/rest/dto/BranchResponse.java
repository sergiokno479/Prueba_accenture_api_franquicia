package com.proyecto.franquicia.infrastructure.input.rest.dto;

public record BranchResponse(
    Long id,
    String name,
    Long franchiseId
) {}