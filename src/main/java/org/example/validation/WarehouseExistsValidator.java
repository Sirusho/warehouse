package org.example.validation;

import org.example.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class WarehouseExistsValidator implements ConstraintValidator<WarehouseExists, UUID> {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public boolean isValid(UUID id, ConstraintValidatorContext constraintContext) {
        return warehouseRepository.findById(id).isPresent();
    }
}