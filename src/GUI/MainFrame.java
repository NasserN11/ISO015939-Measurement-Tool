package GUI;

import com.sun.tools.javac.Main;
import controller.WizardController;
import data.ScenarioData;
import model.Mode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {


    // Private fields
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private WizardController controller;
    private JPanel stepIndicator;

    public MainFrame() {
        // Set up the window
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("ISO 15939 Quality Measurement Tool");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create step indicator panel
        stepIndicator = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        stepIndicator.setBackground(new Color(240, 240, 240));
        add(stepIndicator, BorderLayout.NORTH);

        // Initialize steps
        updateStepIndicator(0);

        // Create CardLayout and the panel that holds all screens
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        controller = new WizardController(this, cardLayout, mainPanel);



        // Create panels
        ProfilePanel profilePanel = new ProfilePanel();
        controller.setProfilePanel(profilePanel);


        DefinePanel definePanel = new DefinePanel();
        controller.setDefinePanel(definePanel);


        PlanPanel planPanel = new PlanPanel();
        controller.setPlanPanel(planPanel);


        CollectPanel collectPanel = new CollectPanel();
        controller.setCollectPanel(collectPanel);


        AnalysePanel analysePanel = new AnalysePanel();
        controller.setAnalysePanel(analysePanel);



        // Add panels to mainPanel with proper names
        mainPanel.add(profilePanel, "Profile");
        mainPanel.add(definePanel, "Define");
        mainPanel.add(planPanel, "Plan");
        mainPanel.add(collectPanel, "Collect");
        mainPanel.add(analysePanel, "Analyse");


        // Create buttons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");

        nextButton.addActionListener(e -> controller.nextStep());
        backButton.addActionListener(e -> controller.previousStep());

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        // Add everything to the frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Show the first screen
        cardLayout.show(mainPanel, "Profile");

        // Make the window visible
        setVisible(true);
    }

    public void updateStepIndicator(int currentStep) {
        stepIndicator.removeAll();

        String[] steps = {"Profile", "Define", "Plan", "Collect", "Analyse"};

        for (int i = 0; i < steps.length; i++) {
            JLabel stepLabel = new JLabel(steps[i]);

            if (i == currentStep) {
                stepLabel.setFont(new Font("Seoge UI", Font.BOLD, 14));
                stepLabel.setForeground(new Color(60, 144, 255));
            }

            else if (i < currentStep) {
                stepLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                stepLabel.setForeground(new Color(52, 204, 66));
                stepLabel.setText("● " + steps[i]);
            }

            stepIndicator.add(stepLabel);


            // Add arrow between steps
            if (i < steps.length - 1) {
                JLabel arrow = new JLabel(("→"));
                arrow.setForeground(Color.gray);
                stepIndicator.add(arrow);
            }

            stepIndicator.revalidate();
            stepIndicator.repaint();
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}