package GUI;

import com.sun.tools.javac.Main;
import controller.WizardController;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {


    // Private fields
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private WizardController controller;

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

        // Create CardLayout and the panel that holds all screens
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        controller = new WizardController(this, cardLayout, mainPanel);

        // Create panels
        ProfilePanel profilePanel = new ProfilePanel();
        JPanel definePanel = new JPanel();
        definePanel.add(new JLabel("Step 2"));
        JPanel planPanel = new JPanel();
        planPanel.add(new JLabel("Step 3"));
        JPanel collectPanel = new JPanel();
        collectPanel.add(new JLabel("Step 4"));
        JPanel analysePanel = new JPanel();
        analysePanel.add(new JLabel("Step 5"));

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

    public static void main(String[] args) {
        new MainFrame();
    }
}