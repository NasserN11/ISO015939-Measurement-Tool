package controller;

import GUI.DefinePanel;
import GUI.MainFrame;
import GUI.PlanPanel;
import GUI.ProfilePanel;
import data.ScenarioData;
import model.Mode;
import model.Scenario;
import model.UserProfile;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WizardController {

    // Private fields
    private MainFrame mainFrame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private int currentStep;    // 0=profile, 1=define, 2=plan, 3=collect, 4=analyse
    private UserProfile userProfile;
    private Scenario selectedScenario;
    private String selectedMode;
    private ProfilePanel profilePanel;
    private DefinePanel definePanel;
    private PlanPanel planPanel;

    ArrayList<Mode> allModes;

    // Constructor
    public WizardController(MainFrame mainFrame,CardLayout cardLayout,JPanel mainPanel) {
        this.mainFrame = mainFrame;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.currentStep = 0;
        allModes = ScenarioData.getAllModes();
    }


    // Methods
    public void nextStep() {
        // Validate current step before moving
        if (currentStep == 0 && profilePanel != null) {
            if (!profilePanel.dataIsValid()) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Please fill in all fields before continuing.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            profilePanel.saveToController(this);
        }

        if (currentStep == 1 && definePanel != null) {
            if (!definePanel.isDataValid()) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Please select a scenario before continuing",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            definePanel.saveToController(this);
        }

        if (currentStep < 4) {
            currentStep++;
        }

        showCurrentStep();
    }

    public void previousStep() {
        if (currentStep > 0) {
            currentStep--;
        }

        showCurrentStep();
    }

    public void saveProfile(String username, String school, String sessionName) {
        userProfile = new UserProfile(username, school, sessionName);
    }

    public void saveSelection(String scenario) {
        this.selectedScenario = findScenarioByName(scenario);
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
            case 2:
                if (planPanel != null && selectedScenario != null) {
                    planPanel.setScenario(selectedScenario);
                }
                cardLayout.show(mainPanel, "Plan"); break;
            case 3: cardLayout.show(mainPanel, "Collect"); break;
            case 4: cardLayout.show(mainPanel, "Analyse"); break;
        }
    }

    public void setProfilePanel(ProfilePanel profilePanel) {
        this.profilePanel = profilePanel;
    }

    public void setDefinePanel(DefinePanel definePanel) { this.definePanel = definePanel; }

    public void setPlanPanel(PlanPanel planPanel) { this.planPanel = planPanel; }


    // Helper Methods
    public Scenario findScenarioByName(String scenarioString) {

        Scenario scenario = null;

        // Loop through all modes
        for (Mode m : allModes) {
            // For each Mode, loop through its Scenarios
            for (Scenario s : m.getScenarios()) {
                // If Scenario name matches, return that Scenario
                if (scenarioString.equals(s.getName())) {
                    scenario = s;
                }
            }
        }

        return scenario;
    }
}
