package MemberManagementGUI.MemberMenuGUI;

import MemberManagementGUI.MemberManagement.MemberManager;
import MemberManagementGUI.MemberManagement.Member;
import javax.swing.*;
import java.awt.*;

public class ShowCheckedIn {

    private MemberManager memberManager;

    public ShowCheckedIn(MemberManager memberManager) {
        this.memberManager = memberManager;
        buildUI();
    }

    private void buildUI() {
        JFrame frame = new JFrame("Checked-In Members");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.add(scrollPane);


        String text = "";

        for (Member m : memberManager.getCheckedMembers()) {
            text += "ID: " + m.getMemberId() + "\n";
            text += "Name: " + m.getName() + "\n";
            text += "Email: " + m.getEmail() + "\n";
            text += "Phone: " + m.getPhoneNum() + "\n";
            text += "Membership: " + m.getMembershipType() + "\n";
            text += "-------------------------------------\n";
        }

        if (text.isEmpty()) {
            text = "No Members To Display.";
        }

        displayArea.setText(text);

        frame.setVisible(true);
    }
}
