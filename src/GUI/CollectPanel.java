package GUI;

import model.Dimension;
import model.Metric;
import model.Scenario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CollectPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private Scenario currentScenario;

    public CollectPanel() {
        setLayout(new BorderLayout());

        String[] columns = {"Metric", "Direction", "Range", "Value", "Score (1-5)", "Coeff / Unit"};

        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;  // Only Value column editable
            }
        };

        // Immediate score update when user finishes editing
        model.addTableModelListener(e -> {
            if (currentScenario == null) return;
            if (e.getColumn() == 3) {
                int row = e.getFirstRow();
                String metricName = (String) model.getValueAt(row, 0);
                String valueStr = (String) model.getValueAt(row, 3);

                // Clear score if empty
                if (valueStr == null || valueStr.trim().isEmpty()) {
                    model.setValueAt("", row, 4);
                    return;
                }

                try {
                    double value = Double.parseDouble(valueStr);

                    for (Dimension d : currentScenario.getDimensions()) {
                        for (Metric m : d.getMetrics()) {
                            if (metricName.equals(m.getName())) {
                                if (value >= m.getMinValue() && value <= m.getMaxValue()) {
                                    m.setMeasuredValue(value);
                                    double score = m.calculateScore();
                                    model.setValueAt(score, row, 4);
                                } else {
                                    model.setValueAt("", row, 4);
                                }
                                break;
                            }
                        }
                    }
                } catch (NumberFormatException ex) {
                    model.setValueAt("", row, 4);
                }
            }
        });

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setScenario(Scenario scenario) {
        this.currentScenario = scenario;
        model.setRowCount(0);

        for (Dimension d : scenario.getDimensions()) {
            String dimensionName = d.getName() + " (Coeff: " + d.getWeight() + ")";
            for (Metric m : d.getMetrics()) {
                model.addRow(new Object[]{
                        m.getName(),
                        m.getDirection().equals("higher") ? "Higher ↑" : "Lower ↓",
                        (int) m.getMinValue() + "-" + (int) m.getMaxValue(),
                        "",  // Value - user fills
                        "",  // Score - calculated when valid
                        m.getWeight() + " / " + m.getUnit()
                });
            }
        }
    }

    public boolean isDataValid() {
        // Stop editing the current cell so the value is saved to the model
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }

        for (int row = 0; row < model.getRowCount(); row++) {
            String metricName = (String) model.getValueAt(row, 0);
            String valueStr = (String) model.getValueAt(row, 3);

            // Check empty
            if (valueStr == null || valueStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        metricName + " cannot be empty",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            double value;
            try {
                value = Double.parseDouble(valueStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        metricName + " must be a valid number",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Find metric and check range
            for (Dimension d : currentScenario.getDimensions()) {
                for (Metric m : d.getMetrics()) {
                    if (metricName.equals(m.getName())) {
                        if (value < m.getMinValue() || value > m.getMaxValue()) {
                            JOptionPane.showMessageDialog(this,
                                    metricName + " must be between " + m.getMinValue() + " and " + m.getMaxValue(),
                                    "Validation Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                        m.setMeasuredValue(value);
                        break;
                    }
                }
            }
        }
        return true;
    }
}