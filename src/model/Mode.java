package model;

import java.util.ArrayList;

public class Mode {

    // Private fields
    private String name;
    ArrayList<Scenario> scenarios;

    // Constructor
    public Mode(String name) {
        this.name = name;
        scenarios = new ArrayList<>();
    }

    // Getters
    public String getName() { return name; }
    public ArrayList<Scenario> getScenarios() { return this.scenarios; }

    // Methods
    public void addScenario(Scenario s) { scenarios.add(s); }
}
