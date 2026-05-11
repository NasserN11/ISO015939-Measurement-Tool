package GUI;

import model.Scenario;

import javax.swing.*;
import java.awt.*;

public class AnalysePanel extends JPanel {

    // Private fields
    Scenario scenario;


    // Constructor
    public AnalysePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // Add title
        JLabel title = new JLabel("ANALYSIS RESULTS");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(title);
        add(Box.createRigidArea(new Dimension(0, 20)));
    }

    public void setScenario(Scenario s) {
        this.scenario = s;
        refresh(); // Build the UI when scenario is set
    }

    private JPanel createDimensionPanel(String name, int coefficient, double score) {

        int percent = (int) ((score / 5.0) * 100);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        // Top row: name and coefficient
        JLabel nameLabel = new JLabel(name + " (Coeff: " + coefficient + ")");
        JLabel scoreLabel = new JLabel(String.format("%.1f / 5", score));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(nameLabel, BorderLayout.WEST);
        topPanel.add(scoreLabel, BorderLayout.EAST);


        // Progress bar
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setValue(percent);
        bar.setStringPainted(true);
        bar.setString(String.format("%.1f / 5", score));

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bar, BorderLayout.CENTER);

        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        return panel;
    }

    private JPanel createGapAnalysisPanel() {
        model.Dimension weakest = findWeakestDimension();
        double score = weakest.calculateDimensionScore();
        double gap = 5.0 - score;
        String label = getQualityLabel(score);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("GAP ANALYSIS"));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        panel.add(new JLabel("Weakest Dimension: " + weakest.getName()));
        panel.add(new JLabel(String.format("Score: %.1f / 5", score)));
        panel.add(new JLabel(String.format("Gap: %.1f to reach perfect score", gap)));
        panel.add(new JLabel("Level: " + label));
        panel.add(Box.createRigidArea(new Dimension(0,5)));
        panel.add(new JLabel("- This characteristic require the most improvement."));

        return panel;
    }

    private void refresh() {

        // Remove everything except the title
        Component[] components = getComponents();
        for (int i = 1; i < components.length; i++) {
            remove(components[i]);
        }

        if (scenario == null) return;

        // Add progress bars for each dimension
        for (model.Dimension d : scenario.getDimensions()) {
            add(createDimensionPanel(d.getName(), d.getWeight(), d.calculateDimensionScore()));
            add(Box.createRigidArea(new Dimension(0, 15)));
        }

        // Add gap analysis
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(createGapAnalysisPanel());

        revalidate();
        repaint();
    }

    private model.Dimension findWeakestDimension() {
        model.Dimension weakest = null;
        double lowestScore = Double.MAX_VALUE;

        for (model.Dimension d : scenario.getDimensions()) {
            double score = d.calculateDimensionScore();
            if (score < lowestScore) {
                lowestScore = score;
                weakest = d;
            }
        }
        return weakest;
    }

    private String getQualityLabel(double score) {
        if (score >= 4.5) return "Excellent Quality";
        if (score >= 3.5) return "Good Quality";
        if (score >= 2.5) return "Needs Improvement";
        return "Poor Quality";
    }
}
