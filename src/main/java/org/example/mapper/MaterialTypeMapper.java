package org.example.mapper;

import org.example.dto.MaterialTypeDto;
import org.example.model.MaterialType;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class MaterialTypeMapper implements DtoToEntityMapper<MaterialTypeDto, MaterialType>,
EntityToDtoMapper<MaterialType, MaterialTypeDto>{

    @Override
    public MaterialType merge(MaterialTypeDto dto, MaterialType entity) {
        entity.setDescription(dto.getDescription());
        entity.setIcon(dto.getIcon());
        entity.setMaxCapacity(dto.getMaxCapacity());
        entity.setName(dto.getName());
        return entity;
    }

    @Override
    public Supplier<MaterialType> entitySupplier() {
        return MaterialType::new;
    }

    @Override
    public MaterialTypeDto convert(MaterialType entity) {
        return MaterialTypeDto.builder()
                .description(entity.getDescription())
                .icon(entity.getIcon())
                .maxCapacity(entity.getMaxCapacity())
                .name(entity.getName())
                .build();
    }
}
