package MemberManagementGUI.MemberManagement;

import javax.swing.*;
import java.awt.*;

public class MemberAdderGUI {
    private JFrame frame;
    private MemberManager memberManager;
    // User Info
    private String name, email, phone;
    private int membershipType, age;

    public MemberAdderGUI(MemberManager memberManager) {

        this.memberManager = memberManager;

        // Frame setup
        frame = new JFrame("Add New Member");
        frame.setSize(300, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Card layout container
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        //Panel A: Personal Info
        JPanel memberPanelA = new JPanel();
        memberPanelA.setLayout(new BoxLayout(memberPanelA, BoxLayout.Y_AXIS));
        memberPanelA.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField memberName = new JTextField(10);
        JTextField memberAge = new JTextField(3);
        JTextField memberEmail = new JTextField(25);
        JTextField memberPhone = new JTextField(25);
        JButton nextPanelA = new JButton("Next");

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("Name:"));
        row1.add(memberName);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Age:"));
        row2.add(memberAge);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row3.add(new JLabel("Email:"));
        row3.add(memberEmail);

        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row4.add(new JLabel("Phone:"));
        row4.add(memberPhone);

        JPanel row5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        row5.add(nextPanelA);

        memberPanelA.add(row1);
        memberPanelA.add(row2);
        memberPanelA.add(row3);
        memberPanelA.add(row4);
        memberPanelA.add(row5);

        //Panel B: Membership Info
        JPanel memberPanelB = new JPanel();
        memberPanelB.setLayout(new BoxLayout(memberPanelB, BoxLayout.Y_AXIS));
        memberPanelB.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField memberID = new JTextField(3);

        //MemberShip Type Radio Buttons
        JRadioButton standardBtn = new JRadioButton("Standard");
        JRadioButton premiumBtn = new JRadioButton("Premium");
        JRadioButton goldBtn = new JRadioButton("Gold");

        // Radio Button Grouping
        ButtonGroup membershipGroup = new ButtonGroup();
        membershipGroup.add(standardBtn);
        membershipGroup.add(premiumBtn);
        membershipGroup.add(goldBtn);

        //Default
        standardBtn.setSelected(true);
        JPanel membershipPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        membershipPanel.add(standardBtn);
        membershipPanel.add(premiumBtn);
        membershipPanel.add(goldBtn);



        JTextField memberPin = new JTextField(3);
        JButton nextB = new JButton("Next");
        JButton backA = new JButton("Back");

        JPanel row6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row6.add(new JLabel("Member ID:"));
        row6.add(memberID);

        JPanel row7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row7.add(new JLabel("Membership Type:"));
        row7.add(membershipPanel);

        JPanel row8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row8.add(new JLabel("PIN:"));
        row8.add(memberPin);

        JPanel row9 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        row9.add(backA);
        row9.add(nextB);

        memberPanelB.add(row6);
        memberPanelB.add(row7);
        memberPanelB.add(row8);
        memberPanelB.add(row9);

        //Panel C: Payment INfo
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));
        paymentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel payTitle = new JLabel("Enter Payment Information");
        payTitle.setFont(new Font("Arial", Font.BOLD, 16));
        payTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField cardLast4 = new JTextField(10);
        JTextField expDate = new JTextField(10);
        JTextField cvvField = new JTextField(10);
        JButton submitPayment = new JButton("Submit Payment");

        JPanel payRow1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        payRow1.add(new JLabel("Card Number (Last 4 Digits):"));
        payRow1.add(cardLast4);

        JPanel payRow2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        payRow2.add(new JLabel("Expiration Date (MM/YY):"));
        payRow2.add(expDate);

        JPanel payRow3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        payRow3.add(new JLabel("CVV (3 digits):"));
        payRow3.add(cvvField);

        JPanel payRow4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        payRow4.add(submitPayment);

        paymentPanel.add(payTitle);
        paymentPanel.add(payRow1);
        paymentPanel.add(payRow2);
        paymentPanel.add(payRow3);
        paymentPanel.add(payRow4);

        //Add Panels to the Layout
        cardPanel.add(memberPanelA, "panelA");
        cardPanel.add(memberPanelB, "panelB");
        cardPanel.add(paymentPanel, "panelC");

        frame.add(cardPanel);
        frame.setVisible(true);

        //Action Listeners
        // Move from Panel A → Panel B
        nextPanelA.addActionListener(e -> {
            try {
                name = memberName.getText().trim();
                email = memberEmail.getText().trim();
                phone = memberPhone.getText().trim();
                age = Integer.parseInt(memberAge.getText().trim());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Age must be a number.");
                return;
            }
            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name and Email are required, please try again.");
                return;
            }

            cardLayout.show(cardPanel, "panelB");
        });

        // Back to Panel A
        backA.addActionListener(e -> cardLayout.show(cardPanel, "panelA"));

        // Finish Panel B → Add Member → Go to Payment Panel
        nextB.addActionListener(e -> {
            String id = memberID.getText().trim();
            String pin =  memberPin.getText().trim();

            for (Member mem : memberManager.getAllMembers()) {
                if(mem.getMemberId() == Integer.parseInt(id)){
                    JOptionPane.showMessageDialog(frame, "Member ID is already in use.");
                    return;
                }
            }

            if(id.isEmpty() || pin.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all the fields.");
                return;
            }

            if (pin.length() != 4 || id.length() != 4) {
                JOptionPane.showMessageDialog(frame, "Members pin and id must be exactly 4 digits.");
                return;
            }

            int memType = 0;
            if (standardBtn.isSelected()) memType = 1;
            else if (premiumBtn.isSelected()) memType = 2;
            else if (goldBtn.isSelected()) memType = 3;


            int memId = Integer.parseInt(memberID.getText().trim());
            int memPin = Integer.parseInt(memberPin.getText().trim());
            

            // Add member
            memberManager.addMember(name, age, memId, memPin, phone, email, memType);

            JOptionPane.showMessageDialog(frame, "Member added successfully! Please Enter the Members Payment Information to Finsh the Process");

            // Move to payment info panel
            cardLayout.show(cardPanel, "panelC");
        });

        // Payment Submit
        submitPayment.addActionListener(e -> {
            String card = cardLast4.getText().trim();
            String exp = expDate.getText().trim();
            String cvv = cvvField.getText().trim();

            if (!card.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(frame, "Card last 4 must be exactly 4 digits.");
                return;
            }

            if (!exp.matches("\\d{2}/\\d{2}")) {
                JOptionPane.showMessageDialog(frame, "Expiration must be MM/YY.");
                return;
            }

            if (!cvv.matches("\\d{3}")) {
                JOptionPane.showMessageDialog(frame, "CVV must be 3 digits.");
                return;
            }

            JOptionPane.showMessageDialog(frame, "Payment information saved!");
            frame.dispose(); // Close the window
        });
    }
}
