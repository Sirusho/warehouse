package org.example.mapper;

import org.example.dto.WarehouseDto;
import org.example.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper implements EntityToDtoMapper<Warehouse, WarehouseDto>{

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public WarehouseDto convert(Warehouse entity) {
        return WarehouseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .materialDtos(materialMapper.convert(entity.getMaterials()))
                .build();
    }
}
