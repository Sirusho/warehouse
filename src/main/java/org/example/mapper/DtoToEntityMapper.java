package org.example.mapper;

import org.example.dto.CommonDto;
import org.example.model.CommonEntity;

import java.util.function.Supplier;

public interface DtoToEntityMapper<D extends CommonDto, E extends CommonEntity> {

    E merge(D dto, E entity);

    default E merge(D dto) {
        if (dto == null) {
            return null;
        }

        return merge(dto, entitySupplier().get());
    }

    Supplier<E> entitySupplier();
}
