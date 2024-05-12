package org.example.validation;

import org.example.repository.MaterialRepository;
import org.example.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class MaterialExistsValidator implements ConstraintValidator<MaterialExists, UUID> {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public boolean isValid(UUID id, ConstraintValidatorContext constraintContext) {
        return materialRepository.findById(id).isPresent();
    }
}