package data;

import model.Dimension;
import model.Metric;
import model.Mode;
import model.Scenario;

import javax.swing.plaf.DimensionUIResource;
import java.util.ArrayList;

public class ScenarioData {

    // Methods
    public static ArrayList<Mode> getAllModes() {
        ArrayList<Mode> modes = new ArrayList<>();
        modes.add(createHealthMode());
        modes.add(createEducationMode());

        return modes;
    }

    // Create Health Mode
    private static Mode createHealthMode() {
        Mode healthMode = new Mode("Health");
        healthMode.addScenario(createHospitalScenario());
        healthMode.addScenario(createClinicScenario());

        return healthMode;
    }

    // Create Education Mode
    private static Mode createEducationMode() {
        Mode educationMode = new Mode("Education");
        educationMode.addScenario(createTeamAhphaScenario());
        educationMode.addScenario(createTeamBetaScenario());

        return educationMode;
    }

    private static Scenario createTeamAhphaScenario() {
        return null;
    }

    private static Scenario createTeamBetaScenario() {
        Scenario teamBeta = new Scenario("Team Beta");

        // Create Usability Dimension
        Dimension usability = new Dimension("Usability", 25);
        // Add metrics for Usability
        Metric susScore = new Metric("SUS score", 50, "higher", 0, 100, "points");
        Metric onboardingTime = new Metric("Onboarding time", 50, "lower", 0, 60, "min");

        usability.addMetric(susScore);
        usability.addMetric(onboardingTime);
        teamBeta.addDimension(usability);


        // Create Perf. Efficiency Dimension
        Dimension perfEfficiency = new Dimension("Perf. Efficiency", 20);
        // Add metrics for Perf. Efficiency
        Metric videoStartTime = new Metric("Video start time", 50, "lower", 0, 15, "sec");
        Metric concurrentExams = new Metric("Concurrent exams", 50, "higher", 0, 600, "users");

        perfEfficiency.addMetric(videoStartTime);
        perfEfficiency.addMetric(concurrentExams);
        teamBeta.addDimension(perfEfficiency);


        // Create Accessibility Dimension
        Dimension accessibility = new Dimension("Accessibility", 20);
        // Add metrics for Accessibility
        Metric WCAG_Compliance = new Metric("WCAG compliance", 50, "higher", 0, 100, "%");
        Metric screenReaderScore = new Metric("Screen reader score", 50, "higher", 0, 100, "%");

        accessibility.addMetric(WCAG_Compliance);
        accessibility.addMetric(screenReaderScore);
        teamBeta.addDimension(accessibility);


        // Create Reliability Dimension
        Dimension reliability = new Dimension("Reliability", 20);
        // Add metrics for Reliability
        Metric uptime = new Metric("Uptime", 50, "higher", 95, 100, "%");
        Metric mttr = new Metric("MTTR", 50, "lower", 0, 120, "min");

        reliability.addMetric(uptime);
        reliability.addMetric(mttr);
        teamBeta.addDimension(reliability);


        // Create Func. Suitability Dimension
        Dimension funcSuitability = new Dimension("Func. Suitability", 15);
        // Add metrics for Func. Suitability
        Metric featureCompletion = new Metric("Feature completion", 50, "higher", 0, 100, "%");
        Metric assignmentSubmitRate = new Metric("Assignment submit rate", 50, "higher", 0, 100, "%");

        funcSuitability.addMetric(featureCompletion);
        funcSuitability.addMetric(assignmentSubmitRate);
        teamBeta.addDimension(funcSuitability);


        return teamBeta;
    }

    private static Scenario createHospitalScenario() {
        // Scenario 1 for Health
        Scenario hospital = new Scenario("Hospital System");

        // Create Usability Dimension
        Dimension usability = new Dimension("Usability", 25);

        // Add metrics for Usability
        Metric susScore = new Metric("SUS score", 50, "higher", 0, 100, "points");

        Metric onboardingTime = new Metric("Onboarding time", 50, "lower", 0, 60, "min");

        usability.addMetric(susScore);
        usability.addMetric(onboardingTime);
        hospital.addDimension(usability);

        // Create Reliability Dimension
        Dimension reliability = new Dimension("Reliability", 20);

        // Add metrics for Reliability
        Metric uptime = new Metric("Uptime", 50, "higher", 95, 100, "%");

        Metric mttr = new Metric("MTTR", 50, "lower", 0, 120, "min");

        reliability.addMetric(uptime);
        reliability.addMetric(mttr);
        hospital.addDimension(reliability);

        return hospital;
    }

    private static Scenario createClinicScenario() {
        // Scenario 2 for Health
        Scenario clinic = new Scenario("Clinic System");

        Dimension usability = new Dimension("Usability", 30);
        usability.addMetric(new Metric("Task completion", 60, "higher", 0, 100, "%"));
        usability.addMetric(new Metric("Error rate", 40, "lower", 0, 20, "%"));
        clinic.addDimension(usability);

        Dimension performance = new Dimension("Performance", 20);
        performance.addMetric(new Metric("Response time", 100, "lower", 0, 5, "sec"));
        clinic.addDimension(performance);

        return clinic;
    }
}
