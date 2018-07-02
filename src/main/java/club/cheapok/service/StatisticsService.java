package club.cheapok.service;

import club.cheapok.model.BasicStatistics;
import club.cheapok.model.TransactionItem;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class StatisticsService {
    private final Map<UUID,TransactionItem> transactionItems = new ConcurrentHashMap<>();
    private BasicStatistics basicStatistics = BasicStatistics.EMPTY;

    public Map<UUID, TransactionItem> getTransactionItems() {
        return transactionItems;
    }

    public void insertTransactionItem(TransactionItem transactionItem) {
        transactionItems.put(transactionItem.getUuid(), transactionItem);
        // a bunch of ifs rather than
        calculateBasicStats();
    }

    public void removeTranscationItem(TransactionItem transactionItem) {
        if (transactionItems.remove(transactionItem.getUuid(), transactionItem)) {
            calculateBasicStats();
        }
    }

    @PostConstruct
    public void calculateBasicStats() {
        final DoubleSummaryStatistics doubleSummaryStatistics = transactionItems.values()
                .parallelStream()
                .mapToDouble(TransactionItem::getAmount)
                .summaryStatistics();

        this.basicStatistics = new BasicStatistics(
                doubleSummaryStatistics.getSum(),
                doubleSummaryStatistics.getAverage(),
                doubleSummaryStatistics.getMax(),
                doubleSummaryStatistics.getMin(),
                doubleSummaryStatistics.getCount()
        );
    }

    public BasicStatistics getBasicStatistics() {
        return basicStatistics.getCount() == 0 ? BasicStatistics.EMPTY : basicStatistics;
    }
}
