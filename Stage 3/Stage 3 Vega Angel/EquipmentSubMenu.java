
/*
 * Angel Garcia Vega 
 * Stage 3
 */

import java.util.Scanner;

public class EquipmentSubMenu implements GymMenu {

    private EquipmentSubMenuManager subMenuManager;
    private Scanner input;


    
    /*
     * Sets up the submenu manager and scanner.
     *
     * @param eq The EquipmentManager used to handle all equipment actions.
     */
    public EquipmentSubMenu(EquipmentManager eq) 
    {
        subMenuManager = new EquipmentSubMenuManager(eq);
        input = new Scanner(System.in);
    }


    /*
     * Shows the equipment menu and waits for user options.
     *
     * @return void
     */
    public void showMenu(){
        while(true){
            System.out.println("\n ====== Equipment Management Menu ======");
            System.out.println("1. Create New Equipment");
            System.out.println("2. Remove Equipment");
            System.out.println("3. Search Equipment by ID");
            System.out.println("4. Update Equipment Information");
            System.out.println("5. Display All Equipment");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1": subMenuManager.createNewEquipment();break;
                case "2": subMenuManager.removeEquipment();break;
                case "3": subMenuManager.searchEquipment();break;
                case "4": subMenuManager.updateEquipment();break;
                case "5": subMenuManager.showAllEquipment();break;
                case "6": return;
                default:
                    System.out.println("Invalid Option Please Try again");
            }
        }
    }
    
}
