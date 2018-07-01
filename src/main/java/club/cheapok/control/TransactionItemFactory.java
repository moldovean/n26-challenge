package club.cheapok.control;

import club.cheapok.control.event.TransactionItemCreatedEvent;
import club.cheapok.control.event.TransactionItemRemoveEvent;
import club.cheapok.model.TransactionItem;
import club.cheapok.model.TransactionItemSpecification;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Stateless
public class TransactionItemFactory {

    @Inject
    private Event<TransactionItemCreatedEvent> transactionItemCreatedEvent;
    @Inject
    private Event<TransactionItemRemoveEvent> transactionItemRemoveEvent;


    public TransactionItemFactory() {
    }

    public TransactionItem createTransactionItem(final TransactionItemSpecification specification) {
        final TransactionItem transactionItem = new TransactionItem(specification.getAmount(), specification.getTimestamp());
        // persist ... or use services for transactionItem
        transactionItemCreatedEvent.fire(new TransactionItemCreatedEvent(transactionItem));
        transactionItemRemoveEvent.fire(new TransactionItemRemoveEvent(transactionItem));

        // transactionItemCreatedEvent.fireAsync(new TransactionItemCreatedEvent(transactionItem)); // use this for JavaEE 8
        return transactionItem;
    }


}
