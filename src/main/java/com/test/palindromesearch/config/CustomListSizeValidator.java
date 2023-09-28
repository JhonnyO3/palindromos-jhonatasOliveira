package com.test.palindromesearch.config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class CustomListSizeValidator implements ConstraintValidator<CustomListSize, List<?>> {

    private int min;
    private int max;

    @Override
    public void initialize(CustomListSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        int size = value.size();
        return size >= min && size <= max;
    }
}
