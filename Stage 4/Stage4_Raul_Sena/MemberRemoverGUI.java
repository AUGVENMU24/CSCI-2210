package MemberManagementGUI.MemberMenuGUI;

import javax.swing.*;
import java.awt.*;

import MemberManagementGUI.MemberManagement.MemberManager;
import MemberManagementGUI.MemberManagement.MemberSubMenuManager;

public class MemberRemoverGUI {

    public MemberRemoverGUI() {

        JFrame frame = new JFrame("Remove Member");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,180); // smaller window
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title label
        JLabel title = new JLabel("Remove Member");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input label
        JLabel label = new JLabel("Enter Member ID:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input field (short, fixed width)
        JTextField idField = new JTextField(10);
        idField.setMaximumSize(new Dimension(150, 30));
        idField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button
        JButton removeButton = new JButton("Remove");
        removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(label);
        panel.add(idField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(removeButton);

        // Remove button logic
        removeButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                MemberSubMenuManager.removeMember(id);
                JOptionPane.showMessageDialog(frame, "Member removed.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "ID must be a number.");
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
