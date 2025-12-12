package Old.StaffManagement;
import Old.GymManagement.GymMenu;

import java.util.Scanner;

public class StaffSubMenu implements GymMenu {

    private StaffSubMenuManager subMenuManager;
    private Scanner input;

    /*
     * Creates the staff submenu and passes in the Old.StaffManagement.StaffManager
     * @param sm Old.StaffManagement.StaffManager used for staff operations
     */
    public StaffSubMenu(StaffManager sm) 
    {
        subMenuManager = new StaffSubMenuManager(sm);
        input = new Scanner(System.in);
    }


    /*
     * Shows the staff menu and handles user choices
     */
    public void showMenu() {

        while (true) {
            System.out.println("\n ====== Old.StaffManagement.Staff Management Menu ======");
            System.out.println("1. Add Old.StaffManagement.Staff");
            System.out.println("2. Remove Old.StaffManagement.Staff");
            System.out.println("3. Modify Old.StaffManagement.Staff Information");
            System.out.println("4. Display All Old.StaffManagement.Staff");
            System.out.println("5. Check-In Old.StaffManagement.Staff");
            System.out.println("6. Display Checked-In Old.StaffManagement.Staff");
            System.out.println("7. Return to MemberManagementGUI.Main Menu");
            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1": subMenuManager.addStaff(); break;
                case "2": subMenuManager.removeStaff(); break;
                case "3": subMenuManager.modifyStaff(); break;
                case "4": subMenuManager.showAllStaff(); break;
                case "5": subMenuManager.checkInStaff(); break;
                case "6": subMenuManager.showCheckedInStaff(); break;
                case "7": return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}

