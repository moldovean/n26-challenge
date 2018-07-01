package club.cheapok.control.event;

import club.cheapok.model.TransactionItem;

public abstract class TransactionItemEvent {

    private final TransactionItem transactionItem;

    TransactionItemEvent(final TransactionItem transactionItem) {
        this.transactionItem = transactionItem;
    }

    public TransactionItem getTransactionItem() {
        return transactionItem;
    }

}
