package com.proyecto.franquicia.infrastructure.input.rest.dto;

public record BranchRequest(
    String name,
    Long franchiseId
) {}