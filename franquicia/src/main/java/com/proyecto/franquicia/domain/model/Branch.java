package com.proyecto.franquicia.domain.model;

public record Branch(
    Long id,
    String name,
    Long franchiseId
) {}