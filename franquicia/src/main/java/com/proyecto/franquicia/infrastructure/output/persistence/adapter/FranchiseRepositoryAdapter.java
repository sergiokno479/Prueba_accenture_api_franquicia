package com.proyecto.franquicia.infrastructure.output.persistence.adapter;

import com.proyecto.franquicia.domain.model.Franchise;
import com.proyecto.franquicia.domain.port.out.FranchiseRepositoryPort;
import com.proyecto.franquicia.infrastructure.output.persistence.entity.FranchiseEntity;
import com.proyecto.franquicia.infrastructure.output.persistence.repository.FranchiseRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class FranchiseRepositoryAdapter implements FranchiseRepositoryPort {

    private final FranchiseRepository repository; 

    public FranchiseRepositoryAdapter(FranchiseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Franchise> save(Franchise franchise) {
        FranchiseEntity entity = FranchiseEntity.builder()
                .id(franchise.id())
                .name(franchise.name())
                .build();

        return repository.save(entity).map(this::toDomain);
    }

    @Override
    public Mono<Franchise> updateName(Long id, String newName) {
        return repository.findById(id)
                .flatMap(entity -> {
                    entity.setName(newName);
                    return repository.save(entity);
                })
                .map(this::toDomain);
    }

    // Mapeo manual 
    private Franchise toDomain(FranchiseEntity entity) {
        return new Franchise(entity.getId(), entity.getName());
    }
}