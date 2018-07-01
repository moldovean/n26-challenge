package club.cheapok.service;

import club.cheapok.model.BasicStatistics;
import club.cheapok.model.TransactionItem;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@ApplicationScoped
public class StatisticsService {
    private final List<TransactionItem> transactionItems = new ArrayList<>();
    private BasicStatistics basicStatistics = BasicStatistics.EMPTY;

    public List<TransactionItem> getTransactionItems() {
        return transactionItems;
    }

    public void insertTransactionItem(TransactionItem transactionItem) {
        transactionItems.add(transactionItem);
        // a bunch of ifs rather than
        calculateBasicStats();
    }

    public void removeTranscationItem(TransactionItem transactionItem) {
        if (transactionItems.remove(transactionItem)) {
            calculateBasicStats();
        }
    }

    @PostConstruct
    public void calculateBasicStats() {
        final DoubleSummaryStatistics doubleSummaryStatistics = transactionItems
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
