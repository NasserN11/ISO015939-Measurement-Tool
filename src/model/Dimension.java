package model;

import java.util.ArrayList;

public class Dimension {

    // Private fields
    private String name;
    private String isoCode;
    private int weight;

    private ArrayList<Metric> metrics;


    // Constructor
    public Dimension(String name, String isoCode, int weight) {
        this.name = name;
        this.isoCode = isoCode;
        this.weight = weight;

        this.metrics = new ArrayList<>();
    }

    // Getters
    public String getName() { return name; }
    public String getIsoCode() { return isoCode; }
    public int getWeight() { return weight; }
    public ArrayList<Metric> getMetrics() { return this.metrics; }

    // Methods
    public void addMetric(Metric m) { metrics.add(m); }

    public double calculateDimensionScore() {
        double dimensionScore;

        double totalScoreTimesWeight = 0;
        double totalWeight = 0;

        int size = metrics.size();
        for (int i = 0; i < size; i++) {

            Metric m = metrics.get(i);

            double metricScore = m.calculateScore();

            totalScoreTimesWeight += metricScore * m.getWeight();
            totalWeight += m.getWeight();
        }

        dimensionScore = totalScoreTimesWeight / totalWeight;
        dimensionScore = Math.round(dimensionScore * 2) / 2.0;
        return dimensionScore;
    }

    public String qualityLabel() {
        double score = calculateDimensionScore();

        if (score >= 4.5 && score <= 5.0)
            return "Excellent Quality";
        else if (score >= 3.5 && score < 4.4)
            return "Good Quality";
        else if (score >= 2.5 && score < 3.4)
            return "Needs Improvement";
        else
            return "Poor Quality";
    }

    public double qualityGap() {
        return 5.0 - calculateDimensionScore();
    }
}
