package StaffManagementGUI;
/*
 * Jacey Tsosie
 *  Stage 3
 * 
 */

import java.util.ArrayList;

public class StaffManager {

    private ArrayList<Staff> staffList;
    private ArrayList<Staff> checkedInStaff;


    /*
     * Creates a new StaffManager with two lists:
     * one for all staff and one for checked-in staff
     */
    public StaffManager() {
        staffList = new ArrayList<>();
        checkedInStaff = new ArrayList<>();
    }
    
    public ArrayList<Staff> getStaffList() {
        return staffList;
   } 

    /*
     * Adds a new staff member to the system
     * @param s Staff object to add
     */
    public void addStaff(Staff s) {
        staffList.add(s);
        System.out.println("Staff Member Added Succesfully");
    }


    /*
     * Removes a staff member based on their ID
     * @param id Staff ID to remove
     * @return true if removed successfully, false if not found
     */
    public boolean removeStaff(int id) {

        Staff st = searchStaff(id);

        if (st != null) {
            staffList.remove(st);
            checkedInStaff.remove(st); // remove if checked in
            System.out.println("Staff Member has been removed");
            return true;
        }

        return false;
    }



    /*
     * Finds a staff member based on their ID
     * @param id Staff ID to look for
     * @return Staff object if found, otherwise null
     */
    public Staff searchStaff(int id) {
        for (Staff s : staffList) {
            if (s.getStaffId() == id) {
                return s;
            }
        }
        return null;
    }


    /*
     * Updates selected fields of a staff member
     * @param id Staff ID to update
     * @param newName Updated name
     * @param newAge Updated age
     * @param newPhone Updated phone number
     * @param newRank Updated rank
     */
    public void updateStaff(int id, String newName, int newAge, String newPhone, int newRank) {

        Staff st = searchStaff(id);

        if (st != null) {
            st.setName(newName);
            st.setAge(newAge);
            st.setPhoneContact(newPhone);
            st.setStaffRank(newRank);
        }
    }

    /*
     * Displays all staff sorted by rank
     */
    public void showAllStaff() {

        if (staffList.isEmpty()) {
            System.out.println("No staff available.");
            return;
        }

        // Sort by rank (3 = Manager, 2 = Assistant Manager, 1 = General Staff)
        staffList.sort((a, b) -> Integer.compare(b.getStaffRank(), a.getStaffRank()));

        System.out.println("\n=========================== STAFF LIST ============================");

        System.out.printf("%-15s %-5s %-15s %-10s %-15s\n",
            "Name", "Age", "Phone", "ID", "Rank");
        System.out.println("---------------------------------------------------------------------");

        for (Staff s : staffList) {

            String rankName;

            switch (s.getStaffRank()) {
                case 3:
                    rankName = "Manager";
                    break;
                case 2:
                    rankName = "Assistant Manager";
                    break;
                default:
                    rankName = "General Staff";
                    break;
            }

            System.out.printf("%-15s %-5d %-15s %-10d %-15s\n",
                s.getName(),
                s.getAge(),
                s.getPhoneContact(),
                s.getStaffId(),
                rankName
            );
        }

        System.out.println("===============================================================\n");
    }


    /*
     * Checks in a staff member based on their ID
     * @param id Staff ID to check in
     */
    public void checkInStaff(int id) {

        Staff st = searchStaff(id);

        if (st != null) {
            if (!checkedInStaff.contains(st)) 
            {
                checkedInStaff.add(st);
                System.out.println("Staff checked in.");

            } else {

                System.out.println("Staff already checked in.");
            }
        } else {
            System.out.println("Staff ID not found.");
        }
    }

    

    /*
     * Displays all currently checked-in staff
     */
    public void showCheckedInStaff() {

        if (checkedInStaff.isEmpty()) {
            System.out.println("\n====================================");
            System.out.println("      NO STAFF ARE CHECKED IN       ");
            System.out.println("====================================\n");
            return;
        }

        System.out.println("\n====================================");
        System.out.println("          CHECKED-IN STAFF          ");
        System.out.println("====================================");

        System.out.printf("%-15s %-5s %-15s %-10s %-15s\n",
            "Name", "Age", "Phone", "ID", "Rank");
        System.out.println("--------------------------------------------------------------");

        for (Staff s : checkedInStaff) {

            String rankName = "";

            switch (s.getStaffRank()) {
                case 3:
                    rankName = "Manager";
                    break;
                case 2:
                    rankName = "Assistant Manager";
                    break;
                default:
                    rankName = "General Staff";
                    break;
            }

            System.out.printf("%-15s %-5d %-15s %-10d %-15s\n",
                s.getName(),
                s.getAge(),
                s.getPhoneContact(),
                s.getStaffId(),
                rankName
            );
        }

        System.out.println("==============================================================\n");
    }



    /*
     * Finds the lowest ranked checked-in staff member
     * @return Staff object with lowest rank or null if none checked in
     */
    public Staff getLowestRankCheckedInStaff() {
        if (checkedInStaff.isEmpty()) 
        {
            return null;
        }

        Staff lowest = checkedInStaff.get(0);

        for (Staff s : checkedInStaff) {
            if (s.getStaffRank() < lowest.getStaffRank()) 
            {
                lowest = s;
            }
        }
        return lowest;
    }


}
