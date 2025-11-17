
/*
 * 
 * Stage 3
 */


import java.util.ArrayList;
import java.util.Scanner;

public class MemberManager {
    
    private ArrayList<Member> gymMembers;
    private ArrayList<Member> checkedInMembers = new ArrayList<>();
    private Scanner input = new Scanner(System.in);


    /*
     * Creates the list that stores all gym members
     */
    public MemberManager()
    {
        gymMembers = new ArrayList<>();
    }



    /*
     * Adds a new member to the list
     * @param aMember The member to add
     */
    public void addMember(Member aMember)
    {
        gymMembers.add(aMember);
    }
    


    /*
     * Searches for a member using their ID
     * @param memberId The ID to search for
     * @return The found member or null if not found
     */
    public Member searchMember(int memberId)
    {
        for (Member mem: gymMembers)
        {
            if(mem.getMemberId() == memberId)
            {
                return mem;
            }
        }
        return null;
    }


    
    /*
     * Removes a member by their ID
     * @param memberId The ID of the member to remove
     */
    public void removeMember(int memberId)
    {
        Member mem = searchMember(memberId);
        if( mem != null)
        {
            gymMembers.remove(mem);
            System.out.println("Member:" + mem.getName() + " was removed successfully.");

        } else {

            System.out.println("Member not Found.");
        }
    }



    /*
     * Allows a member to change their PIN
     * @param memberId The ID of the member changing their pin
     */
    public void changeMembersPin(int memberId)
    {
        Member mem = searchMember(memberId);
        if (mem != null){

        System.out.print("Enter your old PIN (4 Digits): ");
        int oldPin = Integer.parseInt(input.nextLine());

            if (oldPin == mem.getCheckInPin()){
            System.out.print("Enter your new PIN: ");
            int newPin = Integer.parseInt(input.nextLine());

            mem.setCheckInPin(newPin);
            System.out.println("PIN updated successfully.");

            } else {
            System.out.println("Incorrect PIN. PIN not changed.");
            }

        } else {
        
            System.out.println("Member not found.");
        }
    }




    /*
     * Displays all members in a chart sorted by membership type
     */
    public void displayMembersChart() {
        if (gymMembers.isEmpty()) 
        {
            System.out.println("No members to display.");
            return;
        }

        gymMembers.sort((m1, m2) -> Integer.compare(m1.getMembershipType(), m2.getMembershipType()));

        System.out.printf("%-10s %-20s %-5s %-10s %-25s %-15s\n", 
            "ID", "Name", "Age", "PIN", "Email", "Membership");
        System.out.println("----------------------------------------------------------------------------");

        for (Member mem : gymMembers) {
            System.out.printf("%-10d %-20s %-5d %-10d %-25s %-15s\n",
                mem.getMemberId(),
                mem.getName(),
                mem.getAge(),
                mem.getCheckInPin(),
                mem.getEmail(),
                mem.getMembershipName()
            );
        }
    }




     /*
     * Updates a member's personal information
     */
    public void changeMembersInfo() {

        System.out.print("Enter Member ID to update: ");
        int memberId = Integer.parseInt(input.nextLine());
        Member mem = searchMember(memberId);

        if (mem == null) 
        {
            System.out.println("Member not found.");
            return;
        }

        System.out.println("Updating information for " + mem.getName());
        System.out.print("Enter new name (or press Enter to keep current): ");
        String name = input.nextLine();

        if (!name.isEmpty()) 
        {
            mem.setName(name);
        }

        System.out.print("Enter new age (or press Enter to keep current): ");
        String ageStr = input.nextLine();

        if (!ageStr.isEmpty()) {
            try {
                int age = Integer.parseInt(ageStr);
                mem.setAge(age);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age input, keeping current.");
            }
        }

        System.out.print("Enter new phone number (or press Enter to keep current): ");
        String phone = input.nextLine();
        if (phone.isEmpty()) phone = mem.getPhoneNum();

        System.out.print("Enter new email (or press Enter to keep current): ");
        String email = input.nextLine();
        if (email.isEmpty()) email = mem.getEmail();

        mem.setContactInformation(phone, email);

        System.out.println("Select new membership type (or press Enter to keep current):");
        System.out.println("1. Basic");
        System.out.println("2. Premium");
        System.out.println("3. Gold");
        String memChoice = input.nextLine();

        if (!memChoice.isEmpty()) 
        {
            try {
                int type = Integer.parseInt(memChoice);
                mem.setMembershipType(type); 
            } catch (NumberFormatException e) {
                System.out.println("Invalid membership input, keeping current.");
            }
        }
        System.out.println("Member information updated successfully.");
    }



    /*
     * Allows members to check in by verifying ID and PIN
     */
    public void checkInMember() {
        System.out.print("Enter Member ID: ");
        int memberId = Integer.parseInt(input.nextLine());
        Member mem = searchMember(memberId);

        if (mem == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.print("Enter your PIN: ");
        int pin = Integer.parseInt(input.nextLine());

        if (pin == mem.getCheckInPin()) {
            if (!checkedInMembers.contains(mem)) { 
                checkedInMembers.add(mem);
                System.out.println(mem.getName() + " has checked in successfully.");
            } else {
                System.out.println(mem.getName() + " is already checked in.");
            }
        } else {
        System.out.println("Incorrect PIN. Cannot check in.");
        }
    }



    /*
     * Displays all members currently checked in
     */
    public void displayCheckedInMembers(){
        if (checkedInMembers.isEmpty()) 
        {
            System.out.println("No members are currently checked in.");
            return;
        }

        System.out.println("=== Checked-In Members ===");
        System.out.printf("%-10s %-20s %-5s %-10s %-20s %-10s\n", 
            "ID", "Name", "Age", "PIN", "Email", "Membership");
        System.out.println("----------------------------------------------------------------------------");

        for (Member mem : checkedInMembers) {
            System.out.printf("%-10d %-20s %-5d %-10d %-20s %-10s\n",
                mem.getMemberId(),
                mem.getName(),
                mem.getAge(),
                mem.getCheckInPin(),
                mem.getEmail(),
                mem.getMembershipName()
            );
        }
    }
}
