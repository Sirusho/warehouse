package org.example.mapper;

import org.example.dto.MaterialDto;
import org.example.model.Material;
import org.example.model.MaterialType;
import org.example.repository.MaterialRepository;
import org.example.repository.MaterialTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class MaterialMapper implements DtoToEntityMapper<MaterialDto, Material>, EntityToDtoMapper<Material, MaterialDto> {

    @Autowired
    private MaterialTypeMapper materialTypeMapper;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MaterialTypeRepository materialTypeRepository;

    @Override
    public Material merge(MaterialDto dto, Material entity) {

        Optional<MaterialType> materialType = materialTypeRepository.findByName(dto.getMaterialTypeDto().getName());
        if(materialType.isPresent()){
            entity.setMaterialType(materialType.get());
        }else {
            entity.setMaterialType(entity.getMaterialType() == null ?
                    materialTypeMapper.merge(dto.getMaterialTypeDto()) : entity.getMaterialType());
        }
        entity.setQuantity(entity.getQuantity() == null ? dto.getQuantity() : entity.getQuantity() + dto.getQuantity());
        return entity;
    }

    @Override
    public Supplier<Material> entitySupplier() {
        return Material::new;
    }

    public Set<MaterialDto> convert(Set<Material> entities) {
        return entities.stream().map(this::convert).collect(Collectors.toSet());
    }

    @Override
    public MaterialDto convert(Material entity) {
        return MaterialDto.builder()
                .quantity(entity.getQuantity())
                .materialTypeDto(materialTypeMapper.convert(entity.getMaterialType()))
                .build();
    }
}
