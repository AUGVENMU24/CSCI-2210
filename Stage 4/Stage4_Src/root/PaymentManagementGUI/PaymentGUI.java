package PaymentManagementGUI;

import MemberManagementGUI.MemberManagement.MemberManager;
import MemberManagementGUI.MemberManagement.Member;
import PaymentManagementGUI.Payment;

import javax.swing.*;
import java.awt.*;

public class PaymentGUI extends JFrame {
    private MemberManager memberManager;

    private JTextField memberIdField;
    private JLabel nameLabel, typeLabel, balanceLabel;
    private JTextField payAmountField;
    private JButton loadButton, payButton;
    private JTextArea statusArea;

    public PaymentGUI(MemberManager memberManager) {
        this.memberManager = memberManager;
        setTitle("Payment Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        setupTopPanel();
        setupPaymentPanel();
        setupStatusPanel();

        setVisible(true);
    }

    private void setupTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout());
        memberIdField = new JTextField(5);
        loadButton = new JButton("Load Member Info");
        topPanel.add(new JLabel("Member ID:"));
        topPanel.add(memberIdField);
        topPanel.add(loadButton);

        add(topPanel, BorderLayout.NORTH);

        // Load button action
        loadButton.addActionListener(e -> loadMemberInfo());
    }

    private void setupPaymentPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        nameLabel = new JLabel("-");
        typeLabel = new JLabel("-");
        balanceLabel = new JLabel("-");

        infoPanel.add(new JLabel("Name:"));
        infoPanel.add(nameLabel);
        infoPanel.add(new JLabel("Membership Type:"));
        infoPanel.add(typeLabel);
        infoPanel.add(new JLabel("Balance:"));
        infoPanel.add(balanceLabel);

        add(infoPanel, BorderLayout.CENTER);

        JPanel payPanel = new JPanel(new FlowLayout());
        payAmountField = new JTextField(5);
        payButton = new JButton("Pay Balance");
        payPanel.add(new JLabel("Amount to Pay:"));
        payPanel.add(payAmountField);
        payPanel.add(payButton);

        add(payPanel, BorderLayout.SOUTH);

        // Pay button action
        payButton.addActionListener(e -> payMemberBalance());
    }

    private void setupStatusPanel() {
        statusArea = new JTextArea(3, 40);
        statusArea.setEditable(false);
        add(new JScrollPane(statusArea), BorderLayout.EAST);
    }

    private void loadMemberInfo() {
        String idText = memberIdField.getText().trim();
        if (idText.isEmpty()) return;

        try {
            int memberId = Integer.parseInt(idText);
            Member member = memberManager.searchMember(memberId);
            if (member == null) {
                statusArea.append("Member ID " + memberId + " not found.\n");
                nameLabel.setText("-");
                typeLabel.setText("-");
                balanceLabel.setText("-");
                return;
            }

            Payment payment = member.getPayment();
            nameLabel.setText(member.getName());
            typeLabel.setText(memberManager.membershipToString(member.getMembershipType()));
            balanceLabel.setText("$" + payment.getBalance());

            statusArea.append("Loaded member: " + member.getName() + "\n");
        } catch (NumberFormatException ex) {
            statusArea.append("Invalid member ID.\n");
        }
    }

    private void payMemberBalance() {
        String amountText = payAmountField.getText().trim();
        if (amountText.isEmpty()) return;

        try {
            int memberId = Integer.parseInt(memberIdField.getText().trim());
            Member member = memberManager.searchMember(memberId);
            if (member == null) {
                statusArea.append("Member ID " + memberId + " not found.\n");
                return;
            }

            Payment payment = member.getPayment();
            double amount = Double.parseDouble(amountText);

            if (amount <= 0 || amount > payment.getBalance()) {
                statusArea.append("Invalid payment amount.\n");
                return;
            }

            payment.pay(amount); // reduce balance
            balanceLabel.setText("$" + payment.getBalance());
            statusArea.append("Paid $" + amount + " for " + member.getName() + ". New balance: $" + payment.getBalance() + "\n");

        } catch (NumberFormatException ex) {
            statusArea.append("Invalid number format.\n");
        }
    }
}
