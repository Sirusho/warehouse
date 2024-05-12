package org.example.validation;

import org.example.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class WarehouseDoesNotExistValidator implements ConstraintValidator<WarehouseDoesNotExist, String> {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintContext) {
        return warehouseRepository.findByName(name).isEmpty();
    }
}