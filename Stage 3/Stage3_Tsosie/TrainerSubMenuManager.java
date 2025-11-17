
/*
 * Jacey Tsosie
 *  Stage 3
 */

import java.util.Scanner;

public class TrainerSubMenuManager {

    private TrainerManager trainerManager;
    private Scanner input;


    /*
     * Creates the submenu manager for trainer operations
     * @param tm TrainerManager passed from main menu
     */
    public TrainerSubMenuManager(TrainerManager tm) 
    {
        this.trainerManager = tm;
        input = new Scanner(System.in);
    }




    /*
     * Adds a new trainer to the system
     */
    public void addTrainer() {

        System.out.print("Enter Trainer Name: ");
        String name = input.nextLine();

        System.out.print("Enter Trainer Age: ");
        int age = Integer.parseInt(input.nextLine());

        System.out.print("Enter Phone Contact: ");
        String phone = input.nextLine();

        System.out.print("Enter Trainer ID: ");
        int id = Integer.parseInt(input.nextLine());

        int rank = 1;

        System.out.print("Enter Trainer Specialty: ");
        String specialty = input.nextLine();

        Trainer exists = trainerManager.searchTrainer(id);

        if (exists != null) {
            System.out.println("Trainer already exists.");
            return;
        }

        Trainer trainer = new Trainer(name, age, phone, id, rank, specialty);
        trainerManager.addTrainer(trainer);

        System.out.println("Trainer added successfully.");
    }


    /*
     * Removes a trainer using their ID
     */
    public void removeTrainer() {
        System.out.print("Enter Trainer ID to remove: ");
        int id = Integer.parseInt(input.nextLine());

        trainerManager.removeTrainer(id);

    }



    /*
     * Updates trainer information
     */
    public void modifyTrainer() {

        System.out.print("Enter Trainer ID to update: ");
        int id = Integer.parseInt(input.nextLine());

        Trainer t = trainerManager.searchTrainer(id);

        if (t == null) {
            System.out.println("Trainer not found.");
            return;
        }

        System.out.println("Leave field EMPTY to keep current value.");
        System.out.println("-------------------------------------------");

        System.out.print("New Name (" + t.getName() + "): ");
        String newName = input.nextLine();
        if (newName.trim().isEmpty()) newName = t.getName();

        System.out.print("New Age (" + t.getAge() + "): ");
        String ageInput = input.nextLine();
        int newAge = ageInput.trim().isEmpty() ? t.getAge() : Integer.parseInt(ageInput);

        System.out.print("New Phone (" + t.getPhoneContact() + "): ");
        String newPhone = input.nextLine();
        if (newPhone.trim().isEmpty()) newPhone = t.getPhoneContact();

        // Trainer rank always defaults to 1 (General Staff)
        int newRank = 1;

        System.out.print("New Specialty (" + t.getSpecialty() + "): ");
        String newSpecialty = input.nextLine();
        if (newSpecialty.trim().isEmpty()) newSpecialty = t.getSpecialty();

        trainerManager.updateTrainer(id, newName, newAge, newPhone, newRank, newSpecialty);
    }


    /*
     * Searches for a trainer using their ID
     */
    public void searchTrainer() {

    System.out.print("Enter Trainer ID to search: ");
        int id = Integer.parseInt(input.nextLine());

        Trainer t = trainerManager.searchTrainer(id);

        if (t != null) {
            System.out.println("Trainer Found:");
            t.displayStaff();
        } else {
            System.out.println("Trainer with ID " + id + " not found.");
        }
    }


    /*
     * Displays all trainers in the system
     */
    public void showAllTrainers() {
        trainerManager.showAllTrainers();
    }
}
