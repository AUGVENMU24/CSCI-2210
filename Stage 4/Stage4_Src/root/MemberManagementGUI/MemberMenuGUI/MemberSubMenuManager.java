package MemberManagementGUI.MemberMenuGUI;
/*
package MemberManagement;
import Old.PaymentManagement.Payment;

/*
 *
 *
 * Stage 3
 *
 *
 */


import MemberManagementGUI.MemberManagement.Member;
import MemberManagementGUI.MemberManagement.MemberManager;

import java.util.Scanner;

public class MemberSubMenuManager {

    private static MemberManager memberManager = new MemberManager();
    private static Scanner input;


    /*
     * Sets up the submenu manager
     * @param mem MemberManagement.MemberManager used for all member actions
     */
    public MemberSubMenuManager(MemberManager mem) {
        memberManager = mem;
        input = new Scanner(System.in);
    }



    /*
     * Adds a new gym member and collects required payment info
     */
    public static void addMember(String name, int age, int id, int pin, String phone, String email, int membershipType) {
        /*
        //Required name
        String name = "";
        while (name.isEmpty())
        {
            System.out.print("Enter MemberManagement.Member Name: ");
            name = input.nextLine().trim();
            if (name.isEmpty()) System.out.println("Name is required.");
        }



        //required Age
        int age = -1;
        while (age <= 0)
        {
            System.out.print("Enter MemberManagement.Member Age: ");
            try {
                age = Integer.parseInt(input.nextLine());
                if (age <= 0) System.out.println("Age must be a positive number.");
            } catch (Exception e) {
                System.out.println("Invalid age. Please enter a number.");
            }
        }


        //required MemberManagement.Member ID
        int id = -1;
        while (String.valueOf(id).length() != 4)
        {
            System.out.print("Enter MemberManagement.Member ID (4 Digits): ");
            String inputId = input.nextLine().trim();

            if (!inputId.matches("\\d{4}")) {
            System.out.println("ID must be exactly 4 digits.");
            continue;
            }

            id = Integer.parseInt(inputId);

            if (memberManager.searchMember(id) != null) {
                System.out.println("A member with this ID already exists.");
                id = -1; // reset
            }
        }

        // Required Pin
        int pin = -1;
        while (String.valueOf(pin).length() != 4)
        {
            System.out.print("Enter Check-in PIN (4 digits): ");
            String inputPin = input.nextLine().trim();

            if (!inputPin.matches("\\d{4}")) {
                System.out.println("PIN must be exactly 4 digits.");
                continue;
            }

            pin = Integer.parseInt(inputPin);
        }


        String phone = "";
        while (phone.isEmpty()) {
            System.out.print("Enter Phone Number: ");
            phone = input.nextLine().trim();
            if (phone.isEmpty()) System.out.println("Phone number is required.");
        }



        String email = "";
        while (email.isEmpty()) {
            System.out.print("Enter Email: ");
            email = input.nextLine().trim();
            if (email.isEmpty()) System.out.println("Email is required.");
        }



        int membershipType = 1;
        while (true) {
            System.out.println("Select Membership Type:");
            System.out.println("1. Basic");
            System.out.println("2. Premium");
            System.out.println("3. Gold");
            System.out.print("Enter choice: ");

            try {
                membershipType = Integer.parseInt(input.nextLine());
                if (membershipType >= 1 && membershipType <= 3) break;
            } catch (Exception e) { }

            System.out.println("Invalid choice. Enter 1, 2, or 3.");
        }
        */


        Member newMember = new Member(name, age, id, pin, phone, email, membershipType);
        //memberManager.addMember(newMember);

        System.out.println("\nMemberManagement.Member added successfully!");
        System.out.println("\nEnter payment information for " + newMember.getName());
    }
        /*
        String cardEnding = "";
        while (cardEnding.isEmpty())
        {
            System.out.print("Enter card number (last 4 digits): ");
            cardEnding = input.nextLine().trim();

            if (!cardEnding.matches("\\d{4}")) {
                System.out.println("Card number must be exactly 4 digits.");
                cardEnding = "";
            }
        }


        // Expiration date (MM/YY)
        String expDate = "";
        while (expDate.isEmpty())
        {
            System.out.print("Enter expiration date (MM/YY): ");
            expDate = input.nextLine().trim();

            if (!expDate.matches("\\d{2}/\\d{2}"))
            {
                System.out.println("Format must be MM/YY.");
                expDate = "";
            }
        }


        // CVV (3 digits)
        String cvv = "";
        while (cvv.isEmpty()) {
            System.out.print("Enter CVV: ");
            cvv = input.nextLine().trim();

            if (!cvv.matches("\\d{3}")) {
                System.out.println("CVV must be 3 digits.");
                cvv = "";
            }
        }


        Payment payment = new Payment(newMember, cardEnding, expDate, cvv);
        System.out.println("\nPaymentManagement.Payment added for MemberManagement.Member " + newMember.getName());
        System.out.println("Card Ending in " + payment.getCardEnding());
    }*/



    /*
     * Searches for a member by ID and displays info
     */
    public void searchMember() {

        System.out.print("Enter MemberManagement.Member ID to search: ");
        int id = Integer.parseInt(input.nextLine());
        Member mem = memberManager.searchMember(id);

        if (mem != null) {
            mem.displayMember();
        } else {
            System.out.println("MemberManagement.Member with ID " + id + " not found.");
        }
    }


    /*
     * Removes a member by ID
     */
    public static void removeMember(int id)
    {
        System.out.print("Enter MemberManagement.Member ID to remove: ");
        //int id = Integer.parseInt(input.nextLine());
        memberManager.removeMember(id);

    }


    /*
     * Updates a member's personal information
     */
    public void updateMemberInfo()
    {
        //memberManager.changeMembersInfo(Member mem,
        //       String name, String phone, String email,
        //     Integer age, Integer membershipType);
    }


    /*
     * Updates a member's check-in pin
     */
    public void updateMemberPin()
    {
        System.out.print("Enter MemberManagement.Member ID to update PIN: ");
        int id = Integer.parseInt(input.nextLine());
        memberManager.changeMembersPin(id);
    }



    /*
     * Shows all members in a chart
     */
    public void showAllMembers()
    {
        memberManager.displayMembersChart();
    }



    /*
     * Checks in a member using ID and PIN
     */
    public void checkInMember()
    {
        System.out.print("Old CLI Method. Member Was Mostly Likely Checked In If You Can Read This. ");
        //memberManager.checkInMember();
    }



    /*
     * Displays all members currently checked in
     */
    public void showCheckedInMembers()
    {
        memberManager.displayCheckedInMembers();
    }
}


