package com.proyecto.franquicia.domain.model;

public record Product(
    Long id,
    String name,
    Integer stock,
    Long branchId
) {}