package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@ToString
public class MaterialTypeDto implements CommonDto{

    @NotNull
    private String name;
    private String description;
    private String icon;
    @Min(1)
    private int maxCapacity;
}
