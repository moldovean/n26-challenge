package club.cheapok.control.event.listener;

import club.cheapok.control.event.TransactionItemCreatedEvent;
import club.cheapok.control.event.TransactionItemRemoveEvent;
import club.cheapok.model.TransactionItem;
import club.cheapok.service.StatisticsService;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.util.concurrent.TimeUnit;


public class TransactionItemEventListener {

    @Resource
    private ManagedScheduledExecutorService managedScheduledExecutorService;

    @Inject
    private StatisticsService statisticsService;


    public void transactionItemCreated(@Observes @ObservesAsync final TransactionItemCreatedEvent transactionItemCreatedEvent) {
        statisticsService.insertTransactionItem(transactionItemCreatedEvent.getTransactionItem());
    }

    private void removeTransactionItem(@Observes final TransactionItemRemoveEvent transactionItemRemoveEvent) {
        final TransactionItem transactionItem = transactionItemRemoveEvent.getTransactionItem();
        managedScheduledExecutorService.schedule(
                () -> statisticsService.removeTranscationItem(transactionItem),
                transactionItem.getTimestamp() + 60000 - System.currentTimeMillis(),
                TimeUnit.MILLISECONDS);

    }
}
