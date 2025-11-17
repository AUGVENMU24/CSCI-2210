
/*
 *
 * Stage 3
 * 
 */

import java.util.ArrayList;

public class PaymentManager {

    /*
     * Gets a list of all payments made
     */
    public ArrayList<Payment> getAllPayments() {
        return Payment.getPaymentList();
    }


    /*
     * Adds a new payment to the list
     * @param payment The payment to add
     */
    public void addPayment(Payment payment) {
        Payment.getPaymentList().add(payment);
    }



    /*
     * Finds a payment by member ID
     * @param memberId The ID of the member to look for
     * @return The matching Payment or null if not found
     */
    public Payment findPaymentByMemberId(int memberId) {
        for (Payment p : Payment.getPaymentList()) {
            if (p.getMember().getMemberId() == memberId) {
                return p;
            }
        }
        return null;
    }



    /*
     * Updates payment card information
     * @param payment The payment to update
     * @param newCard New card ending digits
     * @param newExp New expiration date
     * @param newCvv New CVV
     */

    public void updatePayment(Payment payment, String newCard, String newExp, String newCvv) {
        payment.setCardEnding(newCard);
        payment.setExpDate(newExp);
        payment.setCvv(newCvv);
    }


    /*
     * Displays all saved payments in chart format
     */
    public void displayPayments() {
        if (Payment.getPaymentList().isEmpty()) 
        {
            System.out.println("No payments to display.");
            return;
        }

        System.out.printf("%-10s %-20s %-15s %-15s %-10s\n",
        "MemberID", "Name", "Card Ending", "Exp Date", "CVV");
        System.out.println("------------------------------------------------------------------------------");

        for (Payment p : Payment.getPaymentList()) 
        {
            System.out.printf("%-10d %-20s %-15s %-15s %-10s\n",
                    p.getMember().getMemberId(),
                    p.getMember().getName(),
                    p.getCardEnding(),
                    p.getExpDate(),
                    p.getCvv());
        }
    }

    

    /*
     * Charges a member for their monthly balance
     * @param member The member being charged
     * @param month The month being paid for
     */
    public void payBalance(Member member, String month) {

        if (member == null) 
        {
            System.out.println("Member not found.");
            return;
        }

        // Determine monthly cost
        double amount = 0;

        switch (member.getMembershipType()) 
        {
            case 1: amount = 25.00; break;   // Basic
            case 2: amount = 45.00; break;   // Premium
            case 3: amount = 60.00; break;   // Gold
            default: amount = 0; break;
        }

        // Look for existing payment info
        Payment pay = findPaymentByMemberId(member.getMemberId());

        // Process payment
        pay.makePayment(month, amount);
    }
}





