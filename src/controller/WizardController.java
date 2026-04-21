package controller;

import GUI.MainFrame;
import model.Scenario;
import model.UserProfile;

import javax.swing.*;
import java.awt.*;

public class WizardController {

    // Private fields
    private MainFrame mainFrame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private int currentStep;    // 0=profile, 1=define, 2=plan, 3=collect, 4=analyse
    private UserProfile userProfile;
    private Scenario selectedScenario;
    private String selectedMode;

    // Constructor
    public WizardController(mainFrame, cardLayout, mainPanel) {
        this.mainFrame = mainFrame;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.currentStep = 0;
    }


    // Methods
    public void nextStep() {
        if (currentStep < 4) {
            currentStep++;
        }
    }

    public void previousStep() {
        if (currentStep > 0) {
            currentStep--;
        }
    }

    public void saveProfile(String username, String school, String sessionName) {
        userProfile = new UserProfile(username, school, sessionName);
    }

    public void saveSelection(String mode, Scenario scenario) {
        this.selectedMode = mode;
        this.selectedScenario = selectedScenario;
    }

    public Scenario getCurrentScenario() {
        return selectedScenario;
    }

    public UserProfile getUserProfile() { return userProfile; }

    public int getCurrentStep() { return currentStep; }

    public void showCurrentStep() {
        switch(currentStep) {
            case 0: cardLayout.show(mainPanel, "Profile"); break;
            case 1: cardLayout.show(mainPanel, "Define"); break;
            case 2: cardLayout.show(mainPanel, "Plan"); break;
            case 3: cardLayout.show(mainPanel, "Collect"); break;
            case 4: cardLayout.show(mainPanel, "Analyse"); break;
        }
    }


}
