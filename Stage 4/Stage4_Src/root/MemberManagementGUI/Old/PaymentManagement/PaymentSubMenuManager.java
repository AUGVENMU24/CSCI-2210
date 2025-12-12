package MemberManagementGUI.Old.PaymentManagement;
/*
 *
 * Stage 3
 * 
 */

import MemberManagementGUI.MemberManagement.Member;
import MemberManagementGUI.MemberManagement.MemberManager;

import java.util.Scanner;

public class PaymentSubMenuManager {

    private PaymentManager paymentManager;
    private MemberManager memberManager;
    private Scanner input;


    /*
     * Sets up the payment submenu manager
     * @param pay Old.PaymentManagement.PaymentManager used for payment actions
     */
    public PaymentSubMenuManager(PaymentManager pay) {
        this.paymentManager = pay;
        memberManager = new MemberManager(); 
        input = new Scanner(System.in);
    }


    /*
     * Adds payment info to a member
     */
    public void addPayment() {
        System.out.print("Enter MemberManagement.Member ID: ");
        int memberId = Integer.parseInt(input.nextLine());

        Member mem = memberManager.searchMember(memberId);
        if (mem == null) {
            System.out.println("MemberManagement.Member not found.");
            return;
        }

        System.out.println("Adding payment for " + mem.getName());

        System.out.print("Enter card number (last 4 digits): ");
        String card = input.nextLine();

        System.out.print("Enter expiration date (MM/YY): ");
        String exp = input.nextLine();

        System.out.print("Enter CVV: ");
        String cvv = input.nextLine();

        Payment payment = new Payment(mem, card, exp, cvv);
        System.out.println("Old.PaymentManagement.Payment added for " + mem.getName());
        System.out.println("Card ending in: " + payment.getCardEnding() + " was added");
    }




    /*
     * Searches for payment information using a member ID
     */
    public void searchPayment() {
        System.out.print("Enter MemberManagement.Member ID: ");
        int memberId = Integer.parseInt(input.nextLine());

        Payment payment = paymentManager.findPaymentByMemberId(memberId);

        if (payment != null) 
        {
            System.out.println("\nOld.PaymentManagement.Payment Info for " + payment.getMember().getName());
            System.out.println("Card Ending: " + payment.getCardEnding());
            System.out.println("Exp Date: " + payment.getExpDate());
            System.out.println("CVV: " + payment.getCvv());

        } else {

            System.out.println("No payment info found.");
        }
    }


    /*
     * Updates payment information for a member
     */
    public void updatePayment() {
        System.out.print("Enter MemberManagement.Member ID to update payment: ");
        int memberId = Integer.parseInt(input.nextLine());

        Payment payment = paymentManager.findPaymentByMemberId(memberId);

        if (payment == null) 
        {
            System.out.println("Old.PaymentManagement.Payment not found.");
            return;
        }

        System.out.println("Updating payment for " + payment.getMember().getName());

        System.out.print("Enter new card number (last 4 digits): ");
        String newCard = input.nextLine();

        System.out.print("Enter new expiration date (MM/YY): ");
        String newExp = input.nextLine();

        System.out.print("Enter new CVV: ");
        String newCvv = input.nextLine();

        paymentManager.updatePayment(payment, newCard, newExp, newCvv);

        System.out.println("Old.PaymentManagement.Payment updated successfully.");
    }



    /*
     * Shows all recorded payments
     */
    public void showAllPayments() {
        paymentManager.displayPayments();
    }


    /*
     * Pays the monthly balance for a member
     */
    public void payBalance() {
        System.out.print("Enter MemberManagement.Member ID: ");
        int id = Integer.parseInt(input.nextLine());

        Payment p = paymentManager.findPaymentByMemberId(id);

        if (p == null) 
        {
            System.out.println("MemberManagement.Member not Found");
            return;
        }

        Member m = p.getMember();

        System.out.print("Enter month to pay for (e.g., January or 02/2025): ");
        String month = input.nextLine();

        paymentManager.payBalance(m, month);
    }
}
