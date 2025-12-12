package MemberManagementGUI.Old.GymManagement;
import MemberManagementGUI.Old.PaymentManagement.PaymentSubMenu;
import TrainerManagement.TrainerManager;
import TrainerManagement.TrainerSubMenu;
import Old.StaffManagement.StaffSubMenu;
import Old.StaffManagement.StaffManager;



/*
 * Angel Garcia Vega 
 * Stage 3
 */

import MemberManagementGUI.Old.EquipmentManagement.EquipmentManager;
import MemberManagementGUI.Old.EquipmentManagement.EquipmentSubMenu;
import MemberManagementGUI.Old.FitnessClassManagement.FitnessClassManager;
import MemberManagementGUI.Old.FitnessClassManagement.FitnessClassSubMenu;
import MemberManagementGUI.MemberManagement.MemberManager;
import MemberManagementGUI.Old.PaymentManagement.PaymentManager;

import java.util.Scanner;

public class GymMainMenu implements Old.GymManagement.GymMenu {
    
    private EquipmentSubMenu equipmentSubMenu;
    //private MemberSubMenu memberSubMenu;
    private PaymentSubMenu paymentSubMenu;
    private TrainerSubMenu trainerSubMenu;
    private FitnessClassSubMenu fitnessClassSubMenu;
    private StaffSubMenu staffSubMenu;

    private EquipmentManager eq;
    private MemberManager mem;
    private PaymentManager pay;
    private TrainerManager tm;
    private FitnessClassManager fcm;
    private StaffManager sm;

    private Scanner input;


    /*
     * Sets up the main menu with all managers and submenus
     * @param eq Old.EquipmentManagement.EquipmentManager
     * @param mem MemberManagement.MemberManager
     * @param pay Old.PaymentManagement.PaymentManager
     * @param tm TrainerManagement.TrainerManager
     * @param fcm Old.FitnessClassManagement.FitnessClassManager
     * @param sm Old.StaffManagement.StaffManager
     */
    public GymMainMenu( EquipmentManager eq,
    MemberManager mem,
    PaymentManager pay,  
    TrainerManager tm,  
    FitnessClassManager fcm,  
    StaffManager sm)

    {
        
        this.eq = eq;
        this.mem = mem;
        this.pay = pay;
        this.sm = sm;
        this.tm = tm;
        this.fcm = fcm;

        input = new Scanner(System.in);


        equipmentSubMenu = new EquipmentSubMenu(eq);
        //memberSubMenu = new MemberSubMenu(mem);
        paymentSubMenu = new PaymentSubMenu(pay);
        staffSubMenu = new StaffSubMenu(sm);
        trainerSubMenu = new TrainerSubMenu(tm);
        fitnessClassSubMenu = new FitnessClassSubMenu(tm, sm, mem, fcm);

    }



    /*
     * Shows the main menu and handles user choices
     */
    public void showMenu(){
        while(true){
            System.out.println("\n---------------------------------------------");
            System.out.println("======== H.I.I.T LABS Gym MemberManagementGUI.Main Menu =========");
            System.out.println("---------------------------------------------");
            System.out.println("1. Old.EquipmentManagement.Equipment Management");
            System.out.println("2. Members Management ");
            System.out.println("3. Old.PaymentManagement.Payment Management");
            System.out.println("4. TrainerManagement.Trainer Management");
            System.out.println("5. Fitness Class Management");
            System.out.println("6. Employee Management (Old.StaffManagement.Staff)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1": equipmentSubMenu.showMenu();break;
                case "2": break;//memberSubMenu.showMenu();break;
                case "3": paymentSubMenu.showMenu();break;
                case "4": trainerSubMenu.showMenu();break;
                case "5": fitnessClassSubMenu.showMenu();break;
                case "6": staffSubMenu.showMenu();break;
                case "7": System.out.println("Exiting Application"); return;
                
                default:
                    System.out.println("Invalid Option Please Try again");
            }
        }

    }
}