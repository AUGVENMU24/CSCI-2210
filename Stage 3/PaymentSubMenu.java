

/*
 * 
 * Stage 3
 * 
 */

import java.util.Scanner;

public class PaymentSubMenu implements GymMenu {

    private PaymentSubMenuManager subMenuManager;
    private Scanner input;


    /*
     * Sets up the payment submenu and manager
     *
     * @param pay PaymentManager used for all payment actions
     */
    public PaymentSubMenu(PaymentManager pay) 
    {
        subMenuManager = new PaymentSubMenuManager(pay);
        input = new Scanner(System.in);
    }


    /*
     * Shows the payment menu and handles user input
     */
    public void showMenu() {
        while (true) {
            System.out.println("\n ====== Payment Management Menu ======");
            System.out.println("1. Pay Balance");
            System.out.println("2. Search Payment Information");
            System.out.println("3. Update Payment Information");
            System.out.println("4. Display All Payments");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1": subMenuManager.payBalance();break;
                case "2": subMenuManager.searchPayment();break;
                case "3": subMenuManager.updatePayment();break;
                case "4": subMenuManager.showAllPayments();break;
                case "5": return;
                default:
                System.out.println("Invalid Option. Please try again.");
            }
        }
    }
}
