package TrainerManagementGUI;
import TrainerManagementGUI.TrainerManager;

import javax.swing.*;
import java.awt.*;

public class TrainerGUI extends JFrame {

    private TrainerManager trainerManager;

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField phoneField;
    private JTextField rankField;
    private JTextField specialtyField;

    private JTextArea outputArea;

    public TrainerGUI(TrainerManager trainerManager) {
        this.trainerManager = trainerManager;

        setTitle("Trainer Manager");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Top Buttons functions for input 
        JPanel formPanel = new JPanel(new GridLayout(2, 6, 5, 5));

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        phoneField = new JTextField();
        rankField = new JTextField();
        specialtyField = new JTextField();

        formPanel.add(labeledField("ID", idField));
        formPanel.add(labeledField("Name", nameField));
        formPanel.add(labeledField("Age", ageField));
        formPanel.add(labeledField("Phone", phoneField));
        formPanel.add(labeledField("Rank (1–3)", rankField));
        formPanel.add(labeledField("Specialty", specialtyField));

        add(formPanel, BorderLayout.NORTH);

        // ===== CENTER: OUTPUT AREA =====
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // ===== BOTTOM: BUTTONS =====
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton removeButton = new JButton("Remove");
        JButton searchButton = new JButton("Search");
        JButton showAllButton = new JButton("Trainer List");
        JButton refreshButton = new JButton("Refresh");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(showAllButton);
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button functions

        // Refresh Table
        refreshButton.addActionListener(e->{refreshScreen();});

        // Add trainer
        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String phone = phoneField.getText().trim();
                int id = Integer.parseInt(idField.getText().trim());
                int rank = Integer.parseInt(rankField.getText().trim());
                String specialty = specialtyField.getText().trim();

                Trainer t = new Trainer(name, age, phone, id, rank, specialty);
                trainerManager.addTrainer(t);

                outputArea.append("Trainer Added: " + trainerToString(t) + "\n");
            } catch (NumberFormatException ex) {
                showNumberError();
            }
        });

        updateButton.addActionListener(e -> {

            // First validate ID — this is the ONLY required field
            if (idField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Enter the ID of the trainer you want to update.",
                        "Missing Trainer ID",
                        JOptionPane.WARNING_MESSAGE);
                return; // STOP here — do not attempt update
            }

            try {
                int id = Integer.parseInt(idField.getText().trim());

                // Get optional fields
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                String specialty = specialtyField.getText().trim();

                Integer age = ageField.getText().trim().isEmpty()
                        ? null
                        : Integer.parseInt(ageField.getText().trim());

                Integer rank = rankField.getText().trim().isEmpty()
                        ? null
                        : Integer.parseInt(rankField.getText().trim());

                // Convert empty strings to null
                if (name.isEmpty()) name = null;
                if (phone.isEmpty()) phone = null;
                if (specialty.isEmpty()) specialty = null;

                trainerManager.updateTrainer(id, name, age, phone, rank, specialty);
                Trainer t = trainerManager.searchTrainer(id);

                outputArea.append("Updated trainer " + trainerToString(t) + "\n");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "One of the numeric fields (ID, age, or rank) is invalid.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });



        // Remove trainer
        removeButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                boolean removed = trainerManager.removeTrainer(id);
                if (removed) {
                    outputArea.append("Removed trainer " + id + "\n");
                } else {
                    outputArea.append("Cannot find trainer " + id + ".\n");
                }
            } catch (NumberFormatException ex) {
                showNumberError();
            }
        });

        // Search trainer
        searchButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                Trainer t = trainerManager.searchTrainer(id);
                if (t != null) {
                    outputArea.append("Found trainer: " + trainerToString(t) + "\n");

                    // fill inputs
                    nameField.setText(t.getName());
                    ageField.setText(String.valueOf(t.getAge()));
                    phoneField.setText(t.getPhoneContact());
                    rankField.setText(String.valueOf(t.getStaffRank()));
                    specialtyField.setText(t.getSpecialty());
                } else {
                    outputArea.append("Cannot locate trainer " + id + " \n");
                }
            } catch (NumberFormatException ex) {
                showNumberError();
            }
        });

        // Trainer List
        showAllButton.addActionListener(e -> {
            outputArea.append("\n Trainer List \n");
            if (trainerManager.getAllTrainers().isEmpty()) {
                outputArea.append("No trainers\n");
            } else {
                for (Trainer t : trainerManager.getAllTrainers()) {
                    outputArea.append(trainerToString(t) + "\n");
                }
            }
        });
        setVisible(true);
    }

    // helper: label + textfield in a small panel
    private JPanel labeledField(String labelText, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout(2, 2));
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    // helper: format a Trainer as one line
    private String trainerToString(Trainer t) {
        return "ID: " + t.getStaffId()
                + " | Name: " + t.getName()
                + " | Age: " + t.getAge()
                + " | Phone: " + t.getPhoneContact()
                + " | Rank: " + t.getStaffRankName()
                + " | Specialty: " + t.getSpecialty();
    }

    // helper: error popup for bad numbers
    private void showNumberError() {
        JOptionPane.showMessageDialog(
                this,
                "Need Valid Age, Rank, or ID.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public void refreshScreen() {
        outputArea.setText("");
    }
}
