package com.proyecto.franquicia.infrastructure.input.rest.dto;

public record ProductRequest(
    String name,
    Integer stock,
    Long branchId
) {}