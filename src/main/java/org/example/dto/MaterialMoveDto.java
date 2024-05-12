package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.validation.MaterialExists;
import org.example.validation.WarehouseExists;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class MaterialMoveDto {

    @WarehouseExists
    private UUID fromWarehouseId;
    @WarehouseExists
    private UUID toWarehouseId;
    @MaterialExists
    private UUID materialId;
}
