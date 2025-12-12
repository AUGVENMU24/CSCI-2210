package MemberManagementGUI.MemberMenuGUI;

import MemberManagementGUI.MemberManagement.Member;
import MemberManagementGUI.MemberManagement.MemberAdderGUI;
import MemberManagementGUI.MemberManagement.MemberManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MemberMenuGUI {
    private MemberManager memberManager;
    private JTable memberTable;
    private DefaultTableModel tableModel;

    public MemberMenuGUI(MemberManager memberManager) {
        this.memberManager = memberManager;

        JFrame frame = new JFrame("Member Menu");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(false);

        // Main Panel
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top: Buttons grid
        JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        JButton addMemberButton = new JButton("Add Member");
        JButton removeMemberButton = new JButton("Remove Member");
        JButton editMemberButton = new JButton("Edit Member");
        JButton searchMemberButton = new JButton("Search Member");
        JButton displayMembersButton = new JButton("Display All Members");
        JButton checkInMemberButton = new JButton("Check-In Member");
        JButton showCheckedInMembersButton = new JButton("Show Checked-In Members");
        JButton refreshPage = new JButton("Refresh Page");
        JButton returnToMainMenuButton = new JButton("Return to Main Menu");

        buttonPanel.add(addMemberButton);
        buttonPanel.add(removeMemberButton);
        buttonPanel.add(editMemberButton);
        buttonPanel.add(searchMemberButton);
        buttonPanel.add(displayMembersButton);
        buttonPanel.add(checkInMemberButton);
        buttonPanel.add(showCheckedInMembersButton);
        buttonPanel.add(returnToMainMenuButton);
        buttonPanel.add(refreshPage);

        panel.add(buttonPanel, BorderLayout.NORTH);

        // Center: Table for displaying members
        String[] columns = {"ID", "Name", "Age", "Membership Type", "Phone", "Email"};
        tableModel = new DefaultTableModel(columns, 0);
        memberTable = new JTable(tableModel);
        memberTable.setFillsViewportHeight(true);
        JScrollPane tableScrollPane = new JScrollPane(memberTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Button Actions

        refreshPage.addActionListener(e -> {refreshPage();});

        // Add Member
        addMemberButton.addActionListener(e -> new MemberAdderGUI(memberManager));

        // Remove Member
        removeMemberButton.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Enter Member ID to remove:");
            if (idStr == null || idStr.trim().isEmpty()) return;

            try {
                int id = Integer.parseInt(idStr);
                Member mem = memberManager.searchMember(id);
                if (mem == null) {
                    JOptionPane.showMessageDialog(frame, "Member not found.");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to remove " + mem.getName() + "?",
                        "Confirm Removal",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    memberManager.removeMember(id);
                    JOptionPane.showMessageDialog(frame, "Member removed successfully.");
                    refreshTable(memberManager.getAllMembers());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID.");
            }
        });

        // Edit Member
        editMemberButton.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Enter Member ID:");
            if (idStr == null || idStr.trim().isEmpty()) return;

            try {
                int id = Integer.parseInt(idStr);
                Member mem = memberManager.searchMember(id);
                if (mem == null) {
                    JOptionPane.showMessageDialog(frame, "Member not found.");
                    return;
                }
                new EditMemberGUI(memberManager, mem);
                refreshTable(memberManager.getAllMembers());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID.");
            }
        });

        // Search Member
        searchMemberButton.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Enter Member ID:");
            if (idStr == null || idStr.trim().isEmpty()) return;
            try {
                int id = Integer.parseInt(idStr);
                Member mem = memberManager.searchMember(id);
                if (mem == null) {
                    JOptionPane.showMessageDialog(frame, "Member not found.");
                } else {
                    // show member in table
                    refreshTable(List.of(mem));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID.");
            }
        });

        // Display All Members
        displayMembersButton.addActionListener(e -> refreshTable(memberManager.getAllMembers()));

        // Check-In Member
        checkInMemberButton.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Enter Member ID:");
            if (idStr == null || idStr.trim().isEmpty()) return;
            try {
                int id = Integer.parseInt(idStr);
                Member mem = memberManager.searchMember(id);
                if (mem == null) {
                    JOptionPane.showMessageDialog(frame, "Member not found.");
                    return;
                }
                if(memberManager.getCheckedMembers().contains(mem)) {
                    JOptionPane.showMessageDialog(frame, "Member is already checked.");
                } else {
                    memberManager.checkInMember(mem);
                    JOptionPane.showMessageDialog(frame, "Member checked in successfully.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID.");
            }
        });

        // Show Checked-In Members
        showCheckedInMembersButton.addActionListener(e -> refreshTable(memberManager.getCheckedMembers()));

        // Return to Main Menu
        returnToMainMenuButton.addActionListener(e -> frame.dispose());

        frame.add(panel);
        frame.setVisible(true);
    }

    // Refresher Method to Repopulate the table
    private void refreshTable(List<Member> members) {
        tableModel.setRowCount(0); // clear existing rows
        for (Member m : members) {
            Object[] row = new Object[]{
                    m.getMemberId(),
                    m.getName(),
                    m.getAge(),
                    membershipToString(m.getMembershipType()),
                    m.getPhoneNum(),
                    m.getEmail(),
            };
            tableModel.addRow(row);
        }
    }

    // Convert membership type int to string
    private String membershipToString(int type) {
        return switch (type) {
            case 1 -> "Standard";
            case 2 -> "Premium";
            case 3 -> "Gold";
            default -> "Unknown";
        };
    }

    public void refreshPage() {
        tableModel.setRowCount(0);
    }
}
