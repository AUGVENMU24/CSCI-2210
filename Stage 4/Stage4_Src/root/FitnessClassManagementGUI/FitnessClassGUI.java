package FitnessClassManagementGUI;
import TrainerManagementGUI.TrainerManager;
import TrainerManagementGUI.Trainer;
import FitnessClassManagementGUI.FitnessClass;

import javax.swing.*;
import java.awt.*;

public class FitnessClassGUI extends JFrame {

    private FitnessClassManager classManager;
    private TrainerManager trainerManager;

    private JTextField classNameField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField descriptionField;
    private JTextField trainerIdField;

    private JTextArea outputArea;

    public FitnessClassGUI(FitnessClassManager classManager, TrainerManager trainerManager) {
        this.classManager = classManager;
        this.trainerManager = trainerManager;

        setTitle("Fitness Class Manager");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Button functions for input
        JPanel formPanel = new JPanel(new GridLayout(2, 5, 5, 5));

        classNameField = new JTextField();
        dateField = new JTextField();
        timeField = new JTextField();
        descriptionField = new JTextField();
        trainerIdField = new JTextField();

        formPanel.add(labeledField("Class Name", classNameField));
        formPanel.add(labeledField("Date", dateField));
        formPanel.add(labeledField("Time", timeField));
        formPanel.add(labeledField("Description", descriptionField));
        formPanel.add(labeledField("Trainer ID", trainerIdField));

        add(formPanel, BorderLayout.NORTH);

        // Output 
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Bottom Button functions 
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add Class");
        JButton removeButton = new JButton("Remove Class");
        JButton searchButton = new JButton("Search Class");
        JButton showAllButton = new JButton("Class Schedule");
        JButton trainerScheduleButton = new JButton("Trainer Schedule");
        JButton refreshButton = new JButton("Refresh Table");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(showAllButton);
        buttonPanel.add(trainerScheduleButton);
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // ===== BUTTON ACTIONS =====

        refreshButton.addActionListener(e -> {refreshTable();});

        // Add class
        addButton.addActionListener(e -> {
            String className = classNameField.getText().trim();
            String date = dateField.getText().trim();
            String time = timeField.getText().trim();
            String description = descriptionField.getText().trim();
            String trainerIdText = trainerIdField.getText().trim();

            if (className.isEmpty() || date.isEmpty() || time.isEmpty()
                    || description.isEmpty() || trainerIdText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Fill in Class Name, Date, Time, Description, and Trainer ID to add a class",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int trainerId = Integer.parseInt(trainerIdText);

                Trainer trainer = trainerManager.searchTrainer(trainerId);
                if (trainer == null) {
                    JOptionPane.showMessageDialog(this,
                            "Cannot find trainer " + trainerId + ".",
                            "Trainer Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check double booking
                if (classManager.isTrainerDoubleBooked(trainer, date, time)) {
                    JOptionPane.showMessageDialog(this,
                            "Trainer is already booked at that date and time.",
                            "Double Booked",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                FitnessClass fc = new FitnessClass(className, date, time, description, trainer);
                classManager.addClass(fc);

                outputArea.append("Class Added: " + fitnessClassToString(fc) + "\n");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid Trainer ID",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Remove class
        removeButton.addActionListener(e -> {
            String className = classNameField.getText().trim();
            if (className.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Enter class name to remove:",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean removed = classManager.removeClass(className);
            if (removed) {
                outputArea.append("Successfully removed class " + className + "\n");

            } else {
                outputArea.append("Unable to remove" + className + "\n");
            }
        });

        // Search class
        searchButton.addActionListener(e -> {
            String className = classNameField.getText().trim();
            if (className.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Enter the Class Name to search.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            FitnessClass fc = classManager.searchClass(className);
            if (fc != null) {

                // Clear output so display is clean
                outputArea.setText("");

                // Show formatted class details
                outputArea.append(fc.displayClassAsString());

                // Pre-fill fields with found class info
                classNameField.setText(fc.getClassName());
                dateField.setText(fc.getDate());
                timeField.setText(fc.getTime());
                descriptionField.setText(fc.getDescription());
                trainerIdField.setText(String.valueOf(fc.getTrainer().getStaffId()));

            } else {
                outputArea.append("Unable to find " + className + "\n");
            }
        });


        // Show all classes
        showAllButton.addActionListener(e -> {
            outputArea.append("\n========= Fitness Class Schedule =========\n");

            if (classManager.getClassList().isEmpty()) {
                outputArea.append("No fitness classes available.\n");
            } else {
                for (FitnessClass c : classManager.getClassList()) {
                    outputArea.append(c.displayClassAsString());
                }
            }
        });


        // Show trainer schedule
        trainerScheduleButton.addActionListener(e -> {
            String trainerIdText = trainerIdField.getText().trim();
            if (trainerIdText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Enter Trainer ID to see their schedule.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int trainerId = Integer.parseInt(trainerIdText);

                // Clear previous screen
                outputArea.setText("");

                // Get formatted schedule string
                String schedule = classManager.showTrainerSchedule(trainerId);

                // Display in GUI text area
                outputArea.append(schedule);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Trainer ID must be a number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


        setVisible(true);
    }

    // helper shows text field and titles
    private JPanel labeledField(String labelText, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout(2, 2));
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    // helper that shows the description of selected fitness class
    private String fitnessClassToString(FitnessClass c) {
        return "Class: " + c.getClassName()
                + " | Date: " + c.getDate()
                + " | Time: " + c.getTime()
                + " | Trainer: " + c.getTrainer().getName()
                + " | Class Description: " + c.getDescription();
    }

    private void refreshTable() {
        outputArea.setText("");
    }
}
