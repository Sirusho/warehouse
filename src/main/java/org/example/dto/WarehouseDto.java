package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.validation.WarehouseDoesNotExist;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class WarehouseDto implements CommonDto {
    private UUID id;

    @WarehouseDoesNotExist
    private String name;

    private Set<@NotNull MaterialDto> materialDtos;
}
