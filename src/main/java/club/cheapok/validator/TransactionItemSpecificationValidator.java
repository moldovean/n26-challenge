package club.cheapok.validator;

import club.cheapok.model.TransactionItemSpecification;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class TransactionItemSpecificationValidator {

    public boolean isValid(final TransactionItemSpecification specification) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        return validator.validate(specification).size() == 0;
    }

}
