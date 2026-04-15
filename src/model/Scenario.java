package model;

import java.util.ArrayList;

public class Scenario {

    // Private fields
    private String name;

    ArrayList<Dimension> dimensions;

    // Constructor
    public Scenario(String name) {

        this.name = name;
        dimensions = new ArrayList<>();
    }

    // Getters
    public String getName() { return name; }
    public ArrayList<Dimension> getDimensions() { return this.dimensions; }


    // Methods
    public void addDimension(Dimension d) { dimensions.add(d); }

    public double calculateOverallScore() {

        // dimensionScore x dimensionWeight
        double totalScoreTimesWeight = 0;
        double totalWeight = 0;

        int size = dimensions.size();
        for (int i = 0; i < size; i++) {

            Dimension d = dimensions.get(i);
            double dimensionScore = d.calculateDimensionScore();

            totalScoreTimesWeight += dimensionScore * d.getWeight();
            totalWeight += d.getWeight();
        }

        double overallScore = totalScoreTimesWeight / totalWeight;
        overallScore = Math.round(overallScore * 10) / 10.0;

        return overallScore;
    }

    public Dimension findWeakestDimension() {

        Dimension weakestDimension = dimensions.get(0);
        double weakestDimensionScore = weakestDimension.calculateDimensionScore();

        int size = dimensions.size();
        for (int i = 1; i < size; i++) {
            Dimension dimension = dimensions.get(i);
            double dimensionScore = dimension.calculateDimensionScore();

            if (dimensionScore < weakestDimensionScore) {
                weakestDimension = dimension;
            }
        }

        return weakestDimension;
    }

    public String overallLabel() {

        double score = calculateOverallScore();
        if (score >= 4.5) return "Excellent Quality";
        if (score >= 3.5) return "Good Quality";
        if (score >= 2.5) return "Needs Improvement";
        return "Poor Quality";
    }
}
