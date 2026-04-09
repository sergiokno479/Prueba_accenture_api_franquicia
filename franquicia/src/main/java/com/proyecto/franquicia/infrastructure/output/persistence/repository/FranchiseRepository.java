package com.proyecto.franquicia.infrastructure.output.persistence.repository;

import com.proyecto.franquicia.infrastructure.output.persistence.entity.FranchiseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FranchiseRepository extends ReactiveCrudRepository<FranchiseEntity, Long> {
}