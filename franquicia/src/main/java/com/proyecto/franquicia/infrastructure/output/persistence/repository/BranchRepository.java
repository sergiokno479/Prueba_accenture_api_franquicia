package com.proyecto.franquicia.infrastructure.output.persistence.repository;

import com.proyecto.franquicia.infrastructure.output.persistence.entity.BranchEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BranchRepository extends ReactiveCrudRepository<BranchEntity, Long> {
}