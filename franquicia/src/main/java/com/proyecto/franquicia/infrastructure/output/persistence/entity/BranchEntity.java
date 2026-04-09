package com.proyecto.franquicia.infrastructure.output.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("branches")
public class BranchEntity {
    @Id
    private Long id;
    private String name;
    private Long franchiseId;
}