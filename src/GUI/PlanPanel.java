package GUI;

import model.Dimension;
import model.Metric;
import model.Scenario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlanPanel extends JPanel {

    // Private fields
    private JTable table;
    private DefaultTableModel model;

    // Constructor
    public PlanPanel() {
        setLayout(new BorderLayout());

        // Create table columns
        String[] columns = {"Dimension", "Metric", "Coefficient", "Direction", "Range", "Unit"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setEnabled(false); // Read-only

        // Add scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }


    public void setScenario(Scenario scenario) {
        // Clear existing rows
        model.setRowCount(0);

        // Loop through dimensions and metrics to fill the table
        for (Dimension d : scenario.getDimensions()) {
            for (Metric m : d.getMetrics()) {
                model.addRow(new Object[]
                                {
                                        d.getName() + " (Coefficient: " + d.getWeight() + ")",
                                        m.getName(),
                                        m.getWeight(),
                                        m.getDirection().equals("Higher") ? "Higher is better" : "Lower is better",
                                        m.getMinValue() + " - " + m.getMaxValue(),
                                        m.getUnit()
                });
            }
        }
    }
}
