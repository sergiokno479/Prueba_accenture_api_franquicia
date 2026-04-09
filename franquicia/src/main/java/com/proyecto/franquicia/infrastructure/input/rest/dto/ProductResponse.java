package com.proyecto.franquicia.infrastructure.input.rest.dto;

public record ProductResponse(
    Long id,
    String name,
    Integer stock,
    Long branchId
) {}