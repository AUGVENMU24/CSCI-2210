package MemberManagementGUI.Old.PaymentManagement;

/*
 *
 *
 * Stage 3
 * 
 */


import MemberManagementGUI.MemberManagement.Member;

import java.util.ArrayList;

public class Payment {
    private Member member;
    private String cardEnding;       
    private String expDate;          
    private String cvv;    

    private double lastPaymentAmount = 0;
    private String lastPaymentMonth = "";

    private static ArrayList<Payment> paymentList = new ArrayList<>();


    /*
     * Creates a new payment record for a member
     * @param member The member tied to the payment
     * @param cardEnding Last 4 digits of the card
     * @param expDate Expiration date of the card
     * @param cvv Security code of the card
     */
    public Payment(Member member, String cardEnding, String expDate, String cvv) {
        this.member = member;
        this.cardEnding = cardEnding;
        this.expDate = expDate;
        this.cvv = cvv;

        paymentList.add(this);
    }


    /*
     * Makes a payment for a specific month and amount
     * @param month The month being paid for
     * @param amount The amount to charge
     */
    public void makePayment(String month, double amount) 
    {

        lastPaymentMonth = month;
        lastPaymentAmount = amount;

        System.out.println("\n========== PAYMENT RECEIPT ==========");
        System.out.println("MemberManagement.Member: " + member.getName());
        System.out.println("Membership Type: " + member.getMembershipName());
        System.out.println("Month Paid: " + month);
        System.out.println("Amount Paid: $" + amount);
        System.out.println("Card Used: **** " + cardEnding);
        System.out.println("Old.PaymentManagement.Payment Status: SUCCESS");
        System.out.println("======================================\n");
    }


    /*
     * @return The member tied to this payment
     */
    public Member getMember()
    { 
        return member; 
    }
    


    /*
     * @return Last 4 digits of the card
     */
    public String getCardEnding()
    {
        return cardEnding; 
    }
    


    /*
     * @return Card expiration date
     */
    public String getExpDate() 
    { 
        return expDate; 
    }


    /*
     * @return Card cvv
     */
    public String getCvv() 
    { 
        return cvv; 
    }

    
    /*
     * @param cardEnding New last 4 digits to store
     */
    public void setCardEnding(String cardEnding)
    {
        this.cardEnding = cardEnding; 
    }
    

    /*
     * @param expDate New expiration date
     */
    public void setExpDate(String expDate) 
    { 
        this.expDate = expDate;
    }


    /*
     * @param cvv New CVV code
     */
    public void setCvv(String cvv) 
    { 
        this.cvv = cvv;
    }

    
    /*
     * Displays basic payment info
     */
    public void displayPayment() 
    {
        System.out.println("MemberManagement.Member: " + member.getName() + " | Membership: " + member.getMembershipName());
        System.out.println("Card ending: " + cardEnding + " | Exp: " + expDate);
        System.out.println("----------------------------");
    }


    /*
     * @return List of all payments made
     */
    public static ArrayList<Payment> getPaymentList() 
    {
        return paymentList;
    }

}

