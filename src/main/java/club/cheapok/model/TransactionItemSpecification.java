package club.cheapok.model;

import club.cheapok.constraint.TransactionItemSpecificationConstraint;

@TransactionItemSpecificationConstraint
public class TransactionItemSpecification {
    private double amount;
    private long timestamp;

    public TransactionItemSpecification() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

}
