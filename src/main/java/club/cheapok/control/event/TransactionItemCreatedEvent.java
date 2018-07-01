package club.cheapok.control.event;

import club.cheapok.model.TransactionItem;

public class TransactionItemCreatedEvent extends TransactionItemEvent {
    public TransactionItemCreatedEvent(final TransactionItem transactionItem) {
        super(transactionItem);
    }
}
