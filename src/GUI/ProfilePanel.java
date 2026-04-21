package GUI;

import controller.WizardController;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {

    // Private fields
    private JTextField usernameField = new JTextField(20);
    private JTextField schoolField = new JTextField(20);
    private JTextField sessionField = new JTextField(20);

    // Constructor
    public ProfilePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Row 0: Name Label
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);

        // Row 0: Name Field
        gbc.gridx = 1;
        add(usernameField, gbc);


        // Row 1: School Label
        gbc.gridy = 1; gbc.gridx = 0;
        add(new JLabel("School:"), gbc);

        // Row 1: School Field
        gbc.gridx = 1;
        add(schoolField, gbc);


        // Row 2: Session Label
        gbc.gridy = 2; gbc.gridx = 0;
        add(new JLabel("Session:"), gbc);

        // Row 2: Session Field
        gbc.gridx = 1;
        add(sessionField, gbc);
    }

    // Methods
    public boolean dataIsValid() {
        // Get what user typed
        String username = usernameField.getText().trim();
        String school = schoolField.getText().trim();
        String session = sessionField.getText().trim();

        // Check if each field has some text
        boolean usernameValid = username.length() > 0;
        boolean schoolValid = school.length() > 0;
        boolean sessionValid = session.length() > 0;

        // return true only if all 3 are true
        return usernameValid && schoolValid && sessionValid;
    }

    public void saveToController(WizardController controller) {

        // Get what user typed
        String username = usernameField.getText().trim();
        String school = schoolField.getText().trim();
        String session = sessionField.getText().trim();

        // Save to controller
        controller.saveProfile(username, school, session);
    }
}
