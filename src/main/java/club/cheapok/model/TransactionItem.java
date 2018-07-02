package club.cheapok.model;

import java.util.UUID;

public class TransactionItem {
    private double amount;
    private long timestamp;
    private final UUID uuid;


    public TransactionItem() {
        this.uuid = UUID.randomUUID();
    }

    public TransactionItem(final double amount, final long timestamp) {
        this();
        this.amount = amount;
        this.timestamp = timestamp;
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

    public UUID getUuid() {
        return uuid;
    }

}
