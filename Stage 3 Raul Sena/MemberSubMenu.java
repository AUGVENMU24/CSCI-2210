
/*
 *
 * Stage 3
 * 
 */

import java.util.Scanner;

public class MemberSubMenu implements GymMenu {

    private MemberSubMenuManager subMenuManager;
    private Scanner input;

    /*
     * Sets up the submenu manager and scanner
     * @param mem MemberManager used for member actions
     */
    public MemberSubMenu(MemberManager mem) {
        subMenuManager = new MemberSubMenuManager(mem);
        input = new Scanner(System.in);
    }


    /*
     * Shows the member menu and handles user choices
     */
    public void showMenu() {
        while (true) {
            System.out.println("\n ====== Member Management Menu ======");
            System.out.println("1. Add New Member");
            System.out.println("2. Remove Member");
            System.out.println("3. Search Member by ID");
            System.out.println("4. Update Member Information");
            System.out.println("5. Update Member PIN");
            System.out.println("6. Display All Members");
            System.out.println("7. Check-in Member");
            System.out.println("8. Show Checked-in Members");
            System.out.println("9. Return to Main Menu");
            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1": subMenuManager.addMember(); break;
                case "2": subMenuManager.removeMember(); break;
                case "3": subMenuManager.searchMember(); break;
                case "4": subMenuManager.updateMemberInfo(); break;
                case "5": subMenuManager.updateMemberPin(); break;
                case "6": subMenuManager.showAllMembers(); break;
                case "7": subMenuManager.checkInMember(); break;
                case "8": subMenuManager.showCheckedInMembers(); break;
                case "9": return;
                default:
                    System.out.println("Invalid Option. Please try again.");
            }
        }
    }
}

