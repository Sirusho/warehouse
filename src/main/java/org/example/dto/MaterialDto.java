package org.example.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class MaterialDto implements CommonDto{

    @NotNull
    private MaterialTypeDto materialTypeDto;

    @Min(1)
    private int quantity;

}
