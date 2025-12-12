package MemberManagementGUI.MemberMenuGUI;
import MemberManagementGUI.MemberManagement.MemberManager;
import MemberManagementGUI.MemberManagement.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class EditMemberGUI {

    private MemberManager memberManager;
    private Member member;

    public EditMemberGUI(MemberManager memberManager, Member member) {
        this.memberManager = memberManager;
        this.member = member;
        buildUI();
    }

    private void buildUI() {
        JFrame frame = new JFrame("Edit Member");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel info = new JLabel("Select field to update:");
        String[] options = {"Name", "Age", "Pin", "Email", "Phone", "Membership Type"};
        JComboBox<String> choices = new JComboBox<>(options);

        JButton doneButton = new JButton("Done");

        panel.add(info);
        panel.add(choices);
        panel.add(doneButton);


        choices.addActionListener(e -> {
            String selected = (String) choices.getSelectedItem();

            switch (selected) {
                case "Name":
                    String newName = JOptionPane.showInputDialog(frame,"Enter new name:");
                    if (newName != null && !newName.trim().isEmpty()) {
                        memberManager.changeMembersInfo(member, newName, null, null, null, null);
                        JOptionPane.showMessageDialog(frame, "Name updated!");
                    }
                    break;

                case "Age":
                    String ageStr = JOptionPane.showInputDialog(frame, "Enter new age:");
                    try {
                        int age = Integer.parseInt(ageStr);
                        memberManager.changeMembersInfo(member, null, null, null, age, null);
                        JOptionPane.showMessageDialog(frame, "Age updated!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid age.");
                    }
                    break;

                case "Email":
                    String email = JOptionPane.showInputDialog(frame, "Enter new email:");
                    if (email != null && !email.isEmpty()) {
                        memberManager.changeMembersInfo(member, null, null, email, null, null);
                        JOptionPane.showMessageDialog(frame, "Email updated!");
                    }
                    break;

                case "Phone":
                    String phone = JOptionPane.showInputDialog(frame, "Enter new phone:");
                    if (phone != null && !phone.isEmpty()) {
                        memberManager.changeMembersInfo(member, null, phone, null, null, null);
                        JOptionPane.showMessageDialog(frame, "Phone updated!");
                    }
                    break;

                case "Membership Type":
                    String[] levels = {"Basic", "Premium", "Gold"};
                    String choice = (String) JOptionPane.showInputDialog(frame, "Select membership type:", "Membership Type", JOptionPane.PLAIN_MESSAGE, null, levels, levels[0]);
                    if (choice != null) {
                        int type = switch (choice) {
                            case "Basic" -> 1;
                            case "Premium" -> 2;
                            case "Gold" -> 3;
                            default -> 1;
                        };
                        memberManager.changeMembersInfo(member, null, null, null, null, type);
                        JOptionPane.showMessageDialog(frame, "Membership updated!");
                    }
                    break;

                case "Pin":
                    String oldPin = JOptionPane.showInputDialog(frame, "Enter old pin:");
                    String currentPin = String.valueOf(member.getCheckInPin());
                    if (oldPin == null || oldPin.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please Enter a valid number");
                        return;
                    }
                    if (!oldPin.equals(currentPin)) {
                        JOptionPane.showMessageDialog(frame, "Pin doesn't match!");

                    } else {
                        String newPin = JOptionPane.showInputDialog(frame, "Enter new pin:");
                        memberManager.changeMembersPin(Integer.parseInt(newPin));
                        JOptionPane.showMessageDialog(frame, "Pin updated!");
                    }
                    break;
            }
        });

        doneButton.addActionListener(e -> {
            frame.dispose();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}

