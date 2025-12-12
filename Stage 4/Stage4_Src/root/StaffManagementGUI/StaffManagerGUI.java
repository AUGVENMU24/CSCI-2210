package StaffManagementGUI;

import javax.swing.*;
import java.awt.*;

public class StaffManagerGUI extends JFrame {

    private StaffManager staffManager;

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField phoneField;
    private JTextField rankField;

    private JTextArea outputArea;

    public StaffManagerGUI(StaffManager staffManager) {
        this.staffManager = staffManager;

        setTitle("Staff Manager");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Top buttons function input field 
        JPanel formPanel = new JPanel(new GridLayout(2, 5, 5, 5));

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        phoneField = new JTextField();
        rankField = new JTextField();

        formPanel.add(labeledField("ID", idField));
        formPanel.add(labeledField("Name", nameField));
        formPanel.add(labeledField("Age", ageField));
        formPanel.add(labeledField("Phone", phoneField));
        formPanel.add(labeledField("Rank (1–3)", rankField));

        add(formPanel, BorderLayout.NORTH);

        // Output
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Bottom Buttons 
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton removeButton = new JButton("Remove");
        JButton searchButton = new JButton("Search");
        JButton showAllButton = new JButton("Staff List");
        JButton checkInButton = new JButton("Check In");
        JButton showCheckedInButton = new JButton("Checked-In");
        
        Dimension buttonSize = new Dimension(130, 40);

         addButton.setPreferredSize(buttonSize);
         updateButton.setPreferredSize(buttonSize);
         removeButton.setPreferredSize(buttonSize);
         searchButton.setPreferredSize(buttonSize);
         showAllButton.setPreferredSize(buttonSize);
         checkInButton.setPreferredSize(buttonSize);
         showCheckedInButton.setPreferredSize(buttonSize);
      

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(showAllButton);
        buttonPanel.add(checkInButton);
        buttonPanel.add(showCheckedInButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Bottom button functions 

        // Add staff
        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String phone = phoneField.getText().trim();
                int id = Integer.parseInt(idField.getText().trim());
                int rank = Integer.parseInt(rankField.getText().trim());

                Staff s = new Staff(name, age, phone, id, rank);
                staffManager.addStaff(s);

                outputArea.append("Staff member added: " + staffToString(s) + "\n");
            } catch (NumberFormatException ex) {
                showNumberError();
            }
        });

        // Update staff
        updateButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String phone = phoneField.getText().trim();
                int rank = Integer.parseInt(rankField.getText().trim());

                staffManager.updateStaff(id, name, age, phone, rank);
                outputArea.append("Staff member updated: " + id + "\n");
            } catch (NumberFormatException ex) {
                showNumberError();
            }
        });

        // Remove staff
        removeButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                boolean removed = staffManager.removeStaff(id);
                if (removed) {
                    outputArea.append("Staff member removed: " + id + "\n");
                } else {
                    outputArea.append("Cannot find staff " + id + ".\n");
                }
            } catch (NumberFormatException ex) {
                showNumberError();
            }
        });

        // Search staff
        searchButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                Staff st = staffManager.searchStaff(id);
                if (st != null) {
                    outputArea.append("Found staff: " + staffToString(st) + "\n");

                    // Fill fields from found staff search
                    nameField.setText(st.getName());
                    ageField.setText(String.valueOf(st.getAge()));
                    phoneField.setText(st.getPhoneContact());
                    rankField.setText(String.valueOf(st.getStaffRank()));
                } else {
                    outputArea.append("Cannot locate Staff ID# " + id + ".\n");
                }
            } catch (NumberFormatException ex) {
                showNumberError();
            }
        });

        // Show all staff
        showAllButton.addActionListener(e -> {
            outputArea.append("\n Staff List \n");
            if (staffManager.getStaffList().isEmpty()) {
                outputArea.append("No staff \n");
            } else {
                for (Staff s : staffManager.getStaffList()) {
                    outputArea.append(staffToString(s) + "\n");
                }
            }
        });

        // Check in staff
        checkInButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                staffManager.checkInStaff(id); // prints to console
                outputArea.append("Attempted check-in for ID# " + id + "Unable to check-in\n");
            } catch (NumberFormatException ex) {
                showNumberError();
            }
        });

        // Show checked-in staff (console-based in your manager)
        showCheckedInButton.addActionListener(e -> {
            outputArea.append("\n Checked-in Staff \n");
            staffManager.showCheckedInStaff(); // prints formatted list to console
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

    // helper: format a Staff as one line
    private String staffToString(Staff s) {
        return "ID: " + s.getStaffId()
                + " | Name: " + s.getName()
                + " | Age: " + s.getAge()
                + " | Phone: " + s.getPhoneContact()
                + " | Rank: " + s.getStaffRankName();
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
}
