import java.util.*;

import static java.lang.Math.sqrt;

/**
 * This class computes the outliers of a list of doubles
 * by selecting those within 2*sigma range from the mean.
 * Sigma is the standard deviation.
 * User: renero
 */
public class Outlier {

    ArrayList<Double> data;
    Double mean;

    /*
     * Constructor: an array of doubles is passed.
     */
    public Outlier(ArrayList<Double> input) {
        data = new ArrayList<Double>(input);
        this.mean = null;
    }

    public Double computeMean() {
        if (this.mean != null) {
            return this.mean;
        }

        Double sum = 0.0;
        for (Double x : data) {
            sum = sum + x;
        }
        this.mean = sum / data.size();
        System.out.printf("Mean: %f\n", this.mean);
        return this.mean;
    }

    public Double variance() {
        Double sigma = 0.0;
        Double mean = this.computeMean();

        for (Double x : data) {
            sigma = sigma + ((mean.doubleValue() - x.doubleValue())*(mean.doubleValue() - x.doubleValue()));
        }
        System.out.printf("Sigma: %f\n", sigma);
        Double inv = 1.0 / ((double)data.size() - 1.0);
        Double variance = inv * sigma;
        System.out.printf("Variance: %f\n", variance);
        return variance;
    }

    public Double stdDev() {
        Double stdDev = sqrt(this.variance());
        System.out.printf("Standard deviation: %f\n", stdDev);
        return stdDev;
    }

    public List<Double> getOutliers() {
        ArrayList<Double> outliers = new ArrayList<Double>();
        double stdDev = this.stdDev();

        for (Double x : data) {
            double distance = Math.abs(x - this.mean);
            if (distance > (2.0 * stdDev)) {
                outliers.add(x);
            }
        }

        return outliers;
    }

    public static void main (String []args)
    {
        System.out.printf("Hola\n");
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(945.45);
        list.add(102.02);
        list.add(328.0);
        list.add(14400.0);
        list.add(1500.1);
        list.add(63.63);
        Outlier base = new Outlier(list);
        List<Double> outliers = base.getOutliers();
        System.out.printf("%d outliers\n", outliers.size());
        if (outliers.size() != 0) {
            for (Double x : outliers) {
                System.out.printf("  -> %f\n", x);
            }
        }
    }
}
