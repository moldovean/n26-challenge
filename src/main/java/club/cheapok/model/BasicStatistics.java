package club.cheapok.model;

public class BasicStatistics {

    public final static BasicStatistics EMPTY = new BasicStatistics(0, 0, 0, 0, 0);

    private double sum;
    private double avg;
    private double max;
    private double min;
    private long count;

    public BasicStatistics() {
    }

    public BasicStatistics(final double sum, final double avg, final double max, final double min, final long count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    public void setCount(final long count) {
        this.count = count;
    }

    public double getMax() {
        return max;
    }

    public void setMax(final double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(final double min) {
        this.min = min;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(final double avg) {
        this.avg = avg;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(final double sum) {
        this.sum = sum;
    }
}
