package club.cheapok.constraint.validator;

import club.cheapok.constraint.TransactionItemSpecificationConstraint;
import club.cheapok.model.TransactionItemSpecification;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TransactionItemSpecificationConstraintValidator implements ConstraintValidator<TransactionItemSpecificationConstraint, TransactionItemSpecification> {

    private long millis;

    public void initialize(TransactionItemSpecificationConstraint constraint) {
        this.millis = constraint.seconds() * 1000;
    }

    public boolean isValid(TransactionItemSpecification specification, ConstraintValidatorContext context) {
        long timestamp = specification.getTimestamp();
        long currentTimeMillis = System.currentTimeMillis();
        // assuming no transactions should come in the future
        return timestamp < currentTimeMillis &&
                currentTimeMillis - timestamp < millis;
    }
}
