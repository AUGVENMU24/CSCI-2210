
/* Jacey Tsosie
 *  Stage 3
 */

import java.util.Scanner;

public class TrainerSubMenu implements GymMenu {

    private TrainerSubMenuManager subMenuManager;
    private Scanner input;


    /*
     * Creates the trainer submenu and passes in the TrainerManager
     * @param tm TrainerManager used for trainer operations
     */
    public TrainerSubMenu(TrainerManager tm) 
    {
        subMenuManager = new TrainerSubMenuManager(tm);
        input = new Scanner(System.in);
    }
    
    /*
     * Shows the trainer menu and handles user options
     */
    public void showMenu() {

        while (true) {
            System.out.println("\n ====== Trainer Management Menu ======");
            System.out.println("1. Add Trainer");
            System.out.println("2. Remove Trainer");
            System.out.println("3. Modify Trainer");
            System.out.println("4. Display All Trainers");
            System.out.println("5. Search for Trainer (ID)");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");

            String choice = input.nextLine();

            switch (choice) {
                case "1": subMenuManager.addTrainer(); break;
                case "2": subMenuManager.removeTrainer(); break;
                case "3": subMenuManager.modifyTrainer(); break;
                case "4": subMenuManager.showAllTrainers(); break;
                case "5": subMenuManager.searchTrainer(); break;
                case "6": return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
