package club.cheapok.control.event;

import club.cheapok.model.TransactionItem;

public class TransactionItemRemoveEvent extends TransactionItemEvent {
    public TransactionItemRemoveEvent(final TransactionItem transactionItem) {
        super(transactionItem);
    }
}
