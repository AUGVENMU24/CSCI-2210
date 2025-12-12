package Old.StaffManagement;
/*
 * Jacey Tsosie
 *  Stage 3
 */


import java.util.Scanner;

public class StaffSubMenuManager {

    private StaffManager staffManager;
    private Scanner input;

    /*
     * Creates the submenu manager for handling staff operations
     * @param sm Old.StaffManagement.StaffManager passed from main menu
     */
    public StaffSubMenuManager(StaffManager sm) 
    {
        this.staffManager = sm;
        input = new Scanner(System.in);
    }



    /*
     * Adds a new staff member to the gym
     */
    public void addStaff() {

        System.out.print("Enter Old.StaffManagement.Staff Name: ");
        String name = input.nextLine();

        System.out.print("Enter Old.StaffManagement.Staff Age: ");
        int age = Integer.parseInt(input.nextLine());

        System.out.print("Enter Phone Contact: ");
        String phone = input.nextLine();

        System.out.print("Enter Old.StaffManagement.Staff ID: ");
        int staffId = Integer.parseInt(input.nextLine());

        System.out.print("Enter Rank (1 = General Old.StaffManagement.Staff, 2 = Assistant Manager, 3 = Manager): ");
        int rank = Integer.parseInt(input.nextLine());

        Staff exists = staffManager.searchStaff(staffId);

        if (exists != null) {
            System.out.println("Old.StaffManagement.Staff already exists.");
            return;
        }

        Staff staff = new Staff(name, age, phone, staffId, rank);
        staffManager.addStaff(staff);

        System.out.println("Old.StaffManagement.Staff successfully added.");
    }



    /*
     * Removes a staff member by ID
     */
    public void removeStaff() {

        System.out.print("Enter Old.StaffManagement.Staff ID to remove: ");
        int id = Integer.parseInt(input.nextLine());

        boolean removed = staffManager.removeStaff(id);
        if (!removed) {
            System.out.println("Could not remove staff. ID not found.");
        }
    }



    /*
     * Modifies staff information (leave blank to keep current values)
     */
    public void modifyStaff() {

        System.out.print("Enter Old.StaffManagement.Staff ID to update: ");
        int id = Integer.parseInt(input.nextLine());

        Staff st = staffManager.searchStaff(id);

        if (st == null) {
            System.out.println("Old.StaffManagement.Staff not found. No update performed.");
            return;
        }

        System.out.println("\n--- Leave any field blank to keep its current value ---");

        // NAME
        System.out.println("Current Name: " + st.getName());
        System.out.print("Enter new Old.StaffManagement.Staff Name: ");
        String newName = input.nextLine().trim();
        if (newName.isEmpty()) newName = st.getName();

        // AGE
        System.out.println("Current Age: " + st.getAge());
        System.out.print("Enter new Old.StaffManagement.Staff Age: ");
        String ageInput = input.nextLine().trim();
        int newAge = st.getAge();

        if (!ageInput.isEmpty()) {
            try {
                newAge = Integer.parseInt(ageInput);
            } catch (Exception e) {
                System.out.println("Invalid age. Keeping previous age.");
            }
        }

        // PHONE
        System.out.println("Current Phone Contact: " + st.getPhoneContact());
        System.out.print("Enter new Phone Contact: ");
        String newPhone = input.nextLine().trim();
        if (newPhone.isEmpty()) newPhone = st.getPhoneContact();

        // RANK
        System.out.println("Current Rank: " + st.getStaffRank() + 
            " (1=General, 2=Assistant Manager, 3=Manager)");
        System.out.print("Enter new Rank: ");
        String rankInput = input.nextLine().trim();
        int newRank = st.getStaffRank();
        if (!rankInput.isEmpty()) {
            try {
                newRank = Integer.parseInt(rankInput);
            } catch (Exception e) {
                System.out.println("Invalid rank. Keeping previous rank.");
            }
        }

        staffManager.updateStaff(id, newName, newAge, newPhone, newRank);
        System.out.println("\nOld.StaffManagement.Staff updated successfully!\n");
    }


    /*
     * Shows all staff members
     */
    public void showAllStaff() {
        staffManager.showAllStaff();
    }


    /*
     * Checks a staff member in
     */
    public void checkInStaff() {

        System.out.print("Enter Old.StaffManagement.Staff ID to Check In: ");
        int id = Integer.parseInt(input.nextLine());
        staffManager.checkInStaff(id);
    }


    /*
     * Shows all currently checked-in staff
     */
    public void showCheckedInStaff()
    {
        staffManager.showCheckedInStaff();
    }
}
