package com.proyecto.franquicia.infrastructure.output.persistence.adapter;

import com.proyecto.franquicia.domain.model.Branch;
import com.proyecto.franquicia.domain.port.out.BranchRepositoryPort;
import com.proyecto.franquicia.infrastructure.output.persistence.entity.BranchEntity;
import com.proyecto.franquicia.infrastructure.output.persistence.repository.BranchRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class BranchRepositoryAdapter implements BranchRepositoryPort {

    private final BranchRepository repository;

    public BranchRepositoryAdapter(BranchRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Branch> save(Branch branch) {
        BranchEntity entity = BranchEntity.builder()
                .id(branch.id())
                .name(branch.name())
                .franchiseId(branch.franchiseId())
                .build();

        return repository.save(entity).map(this::toDomain);
    }

    @Override
    public Mono<Branch> updateName(Long id, String newName) {
        return repository.findById(id)
                .flatMap(entity -> {
                    entity.setName(newName);
                    return repository.save(entity);
                })
                .map(this::toDomain);
    }

    private Branch toDomain(BranchEntity entity) {
        return new Branch(entity.getId(), entity.getName(), entity.getFranchiseId());
    }
}