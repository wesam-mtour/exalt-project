package com.exalt.sparepartsmanagement.phoneValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements
        ConstraintValidator<PhoneValidator, String> {

    // this regex represents the palestinian phone number
    private static final String PALESTINIAN_PHONE_NUMBER_REGEX = "^(\\+|(00))9705(9|6)[0-9]{7}$";

    @Override
    public void initialize(PhoneValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return phoneNumber != null && phoneNumber.trim().matches(PALESTINIAN_PHONE_NUMBER_REGEX);
    }
}
