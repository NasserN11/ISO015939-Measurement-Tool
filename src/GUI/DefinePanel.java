package GUI;

import controller.WizardController;
import model.Mode;
import model.Scenario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DefinePanel extends JPanel {

    // Radio buttons for quality type
    private JRadioButton productQuality = new JRadioButton("Product Quality");
    private JRadioButton processQuality = new JRadioButton("Process Quality");
    private ButtonGroup qualityGroup = new ButtonGroup();

    // Radio buttons for Mode
    private JRadioButton healthMode = new JRadioButton("Health");
    private JRadioButton educationMode = new JRadioButton("Education");
    private ButtonGroup modeGroup = new ButtonGroup();


    // Scenario (updated based on Mode)
    private JComboBox<String> scenarioCombo = new JComboBox<>();

    public DefinePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Create components

        // Add radio buttons to groups
        qualityGroup.add(productQuality);
        qualityGroup.add(processQuality);

        modeGroup.add(healthMode);
        modeGroup.add(educationMode);


        // Row 0: Quality Type Label
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Quality Type:"), gbc);

        // Row 1: productQuality, processQuality radio buttons
        gbc.gridy = 1;
        add(productQuality, gbc);
        gbc.gridx = 1;
        add(processQuality, gbc);

        // Row 2: Mode Label
        gbc.gridy = 2; gbc.gridx = 0;
        add(new JLabel("Mode:"), gbc);

        // Row 3: healthMode, educationMode, radio buttons
        gbc.gridy = 3;
        add(healthMode, gbc);
        gbc.gridx = 1;
        add(educationMode, gbc);

        // Row 4: Scenario Label
        gbc.gridy = 4; gbc.gridx = 0;
        add(new JLabel("Scenario:"), gbc);

        // Row 5: Scenario ComboBox
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        scenarioCombo.setPreferredSize(new Dimension(200, 25));
        add(scenarioCombo, gbc);

        // Action listeners to update scenario when mode changes
        healthMode.addActionListener(e -> updateScenario());
        educationMode.addActionListener(e -> updateScenario());

        // Initialize with health mode selected by default
        healthMode.setSelected(true);
        updateScenario();

    }

    // Methods
    public void saveToController(WizardController controller) {
        String mode = getSelectedMode();
        String scenario = getSelectedScenario();

        controller.saveSelection(scenario);
    }
    private void updateScenario() {

        scenarioCombo.removeAllItems();
        if (healthMode.isSelected()) {
            scenarioCombo.addItem("Hospital System");
            scenarioCombo.addItem("Clinic System");
        }
        else if (educationMode.isSelected()) {
            scenarioCombo.addItem("Team Alpha");
            scenarioCombo.addItem("Team Beta");
        }
    }

    public boolean isDataValid() {
        return scenarioCombo.getSelectedIndex() != -1;
    }

    // Helper methods
    private String getSelectedMode() {
        if (healthMode.isSelected())
            return "Health";
        if (educationMode.isSelected())
            return "Education";
        return null;
    }

    private String getSelectedScenario() {
        String selectedScenario = (String) scenarioCombo.getSelectedItem();

        return selectedScenario;
    }
}
