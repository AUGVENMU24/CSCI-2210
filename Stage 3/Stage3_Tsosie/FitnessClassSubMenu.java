
/*
   Jacey Tsosie
 * Stage 3
 * 
 */

import java.util.Scanner;

public class FitnessClassSubMenu implements GymMenu {


    private FitnessClassSubMenuManager subMenuManager;
    private Scanner input;


    /*
     * Sets up the submenu manager and scanner
     * @param tm TrainerManager used for trainer actions
     * @param sm StaffManager used for staff actions
     * @param mem MemberManager used for member actions
     * @param fcm FitnessClassManager used for class actions
     */
    public FitnessClassSubMenu(TrainerManager tm, StaffManager sm,MemberManager mem,FitnessClassManager fcm){

        subMenuManager = new FitnessClassSubMenuManager(tm,sm,mem,fcm);
        input = new Scanner(System.in);

    }

    /*
     * Shows the fitness class menu and handles user choices
     */
    public void showMenu() {

        while (true) {
            System.out.println("\n ====== Fitness Class Menu ======");
            System.out.println("1. Add Fitness Class");
            System.out.println("2. Remove Fitness Class");
            System.out.println("3. Search Fitness Class");
            System.out.println("4. Show Class Schedule");
            System.out.println("5. Sign Member Up for Class");
            System.out.println("6. Cancel Class");
            System.out.println("7. Show Trainer Schedule");
            System.out.println("8. Return to Main Menu");
            System.out.print("Choose an option: ");

            String choice = input.nextLine();

            switch (choice) {
                case "1": subMenuManager.addClass(); break;
                case "2": subMenuManager.removeClass(); break;
                case "3": subMenuManager.searchClass(); break;
                case "4": subMenuManager.showAllClasses(); break;
                case "5": subMenuManager.signMemberUp(); break;
                case "6": subMenuManager.cancelClass(); break;
                case "7": subMenuManager.showTrainerSchedule(); break;
                case "8": return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}

