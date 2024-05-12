package org.example.mapper;


import org.example.dto.CommonDto;
import org.example.model.CommonEntity;

public interface EntityToDtoMapper<E extends CommonEntity, D extends CommonDto> {

    D convert(E entity);
}
