package club.cheapok.boundary;

import club.cheapok.control.TransactionItemFactory;
import club.cheapok.model.BasicStatistics;
import club.cheapok.model.TransactionItem;
import club.cheapok.model.TransactionItemSpecification;
import club.cheapok.service.StatisticsService;
import club.cheapok.validator.TransactionItemSpecificationValidator;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/")
public class N26EndPoints {

    private final TransactionItemFactory transactionItemFactory;

    private final StatisticsService statisticsService;

    private final TransactionItemSpecificationValidator validator;

    @Inject
    public N26EndPoints(final TransactionItemFactory transactionItemFactory,
                        final StatisticsService statisticsService,
                        final TransactionItemSpecificationValidator validator) {
        this.transactionItemFactory = transactionItemFactory;
        this.statisticsService = statisticsService;
        this.validator = validator;
    }


    @GET
    @Path("statistics")
    public BasicStatistics getStatistics() {
        return statisticsService.getBasicStatistics();
    }

    @POST
    @Path("transactions")
    public Response postTransactionItem(TransactionItemSpecification transactionItemSpecification) {
        if (!validator.isValid(transactionItemSpecification))
            return Response.noContent().build();

        transactionItemFactory.createTransactionItem(transactionItemSpecification);
        return Response.created(URI.create("/transactions")).build();
    }

    @GET
    @Path("iaka")
    public List<TransactionItem> getTransactionItems() {
        return statisticsService.getTransactionItems();
    }

}
