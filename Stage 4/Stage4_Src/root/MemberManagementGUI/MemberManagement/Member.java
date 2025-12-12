package MemberManagementGUI.MemberManagement;
/*
 * 
 * Stage 3
 * 
 */

import PaymentManagementGUI.Payment;

public class Member{
    private String name;
    private int age;
    private int memberId;
    private String phoneNumberC;
    private String emailContact;
    private int checkInPin;
    private int membershipType;
    private Payment payment;


    /*
     * Creates a new gym member
     * @param name MemberManagement.Member name
     * @param age MemberManagement.Member age
     * @param memberId MemberManagement.Member ID number
     * @param checkInPin MemberManagement.Member check-in pin
     * @param phoneNumberC Phone number
     * @param emailContact Email contact
     * @param membershipType Membership level (1-3)
     */
    public Member(String name, int age, int memberId,int checkInPin, String phoneNumberC, String emailContact, int membershipType) 
    {
        this.name = name; 
        this.age = age;
        this.memberId = memberId;
        this.checkInPin = checkInPin;
        this.phoneNumberC = phoneNumberC;
        this.emailContact = emailContact;
        this.membershipType = membershipType;
        this.payment = new Payment(getPriceForMembership(membershipType));
        setMembershipType(membershipType);
    }


    /*
     * @return The member's name
     */
    public String getName()
    {
        return name;
    }


    /*
     * @return The member's age
     */
    public Integer getAge()
    {
        return age;
    }


    /*
     * @return The member's ID
     */
    public Integer getMemberId()
    {
        return memberId;
    }


    /*
     * @return The member's phone number
     */
    public String getPhoneNum()
    {
        return phoneNumberC;
    }


    /*
     * @return The member's email
     */
    public String getEmail()
    {
        return emailContact;
    }
   

    /*
     * @return The membership type number
     */
    public int getMembershipType()
    {
        return membershipType;
    }


    
    /*
     * Converts membership type into a readable name
     * @return Membership type name
     */
    public String getMembershipName()
    {
        switch (membershipType) {
            case 1: return "Basic"; 
            case 2: return "Premium"; 
            case 3: return "Gold";
            default: return "Basic"; 
        }
    }


   
    /*
     * Sets the membership type (defaults to Basic if invalid)
     * @param membershipType The selected membership level
     */
    public void setMembershipType(int membershipType)
    {
        if (membershipType < 1 || membershipType > 3)
        {
            this.membershipType = 1;
        } else {
            this.membershipType = membershipType;
        }
    }


    /*
     * Sets a new name
     * @param name The updated name
     */
    public void setName(String name)
    {
        this.name = name;
    }



    /*
     * @return The member's check-in pin
     */
    public Integer getCheckInPin()
    {
        return checkInPin;
    }


    /*
     * Sets a new check-in pin
     * @param checkInPin The new pin
     */
    public void setCheckInPin(int checkInPin)
    {
        this.checkInPin = checkInPin;
    }


    /*
     * Updates contact information
     * @param phoneNumberC New phone number
     * @param emailContact New email
     */
    public void setEmail(String emailContact)
    {
        this.emailContact = emailContact;
    }

    public void setPhoneNumberC(String phoneNumberC)
    {
        this.phoneNumberC = phoneNumberC;
    }


    /*
     * Sets a new age
     * @param age The updated age
     */
    public void setAge(Integer age)
    {
        this.age = age;
    }

    /*
     * Displays all member info
     * @return void
     */
    public void displayMember()
    {
        System.out.println("MemberManagement.Member Name: " + name + "| MemberManagement.Member Age: " + age);
        System.out.println("MemberManagement.Member Id: " + memberId);
        System.out.println("Contact Information :");
        System.out.println("Email: " + emailContact);
        System.out.println("Phone Number: " + phoneNumberC);
        System.out.println("Membership Type: " + getMembershipName());

    }

    //Payment Info
    public Payment getPayment() {
        return payment;
    }

    private double getPriceForMembership(int type) {
        switch (type) {
            case 1: return 50.0; // Standard
            case 2: return 75.0; // Premium
            case 3: return 100.0; // Gold
            default: return 0;
        }
    }

    @Override
    public String toString() {
        return "ID: " + memberId +
                "\nName: " + name +
                "\nAge: " + age +
                "\nPhone: " + phoneNumberC +
                "\nEmail: " + emailContact +
                "\nMembership: " + getMembershipName();
    }


}
