package MemberManagementGUI.Old.FitnessClassManagement;
import TrainerManagement.TrainerManager;
import MemberManagementGUI.MemberManagement.MemberManager;
import Old.StaffManagement.StaffManager;

import TrainerManagement.Trainer;
import MemberManagementGUI.MemberManagement.Member;
import Old.StaffManagement.Staff;

import java.util.Scanner;

public class FitnessClassSubMenuManager {

    private FitnessClassManager classManager;
    private TrainerManager trainerManager;
    private StaffManager staffManager;
    private MemberManager memberManager;

    private Scanner input;


    /*
     * Sets up all managers used by this submenu
     * @param tm TrainerManagement.TrainerManager for trainer actions
     * @param sm Old.StaffManagement.StaffManager for staff actions
     * @param mem MemberManagement.MemberManager for member actions
     * @param fcm Old.FitnessClassManagement.FitnessClassManager for class actions
     */
    public FitnessClassSubMenuManager(
        TrainerManager tm,
        StaffManager sm,
        MemberManager mem,
        FitnessClassManager fcm) {

        this.trainerManager = tm;
        this.staffManager = sm;
        this.memberManager = mem;
        this.classManager = fcm;
        this.input = new Scanner(System.in);
    }




    /*
     * Adds a new fitness class and tries auto assigning a trainer if needed
     */
    public void addClass() {

        System.out.print("Enter Class Name: ");
        String className = input.nextLine();

        System.out.print("Enter Class Date (e.g., 12/25/2025): ");
        String date = input.nextLine();

        System.out.print("Enter Class Time (e.g., 5:00 PM): ");
        String time = input.nextLine();

        System.out.print("Enter Class Description: ");
        String desc = input.nextLine();

        //Auto assign trainer if user presses Enter
        System.out.print("Enter TrainerManagement.Trainer ID (or press Enter to auto assign): ");
        String trainerInput = input.nextLine();

        Trainer trainer = null;

        if (trainerInput.isEmpty()) {

            //java.util.List<Trainer> trainers = trainerManager.getAllTrainers();
            // Shuffle random order
            java.util.Collections.shuffle(trainers);
            // Assign a free trainer

            for (Trainer t : trainerManager.getAllTrainers()) 
            {
                if (!classManager.isTrainerDoubleBooked(t, date, time)) 
                {
                    trainer = t;
                    System.out.println("Auto-assigned TrainerManagement.Trainer: " + t.getName());
                    break;
                }
            }

            // If no trainers found, use checked-in staff
            if (trainer == null) {
                Staff sub = staffManager.getLowestRankCheckedInStaff();
                if (sub != null) {
                    trainer = new Trainer(
                            sub.getName(),
                            sub.getAge(),
                            sub.getPhoneContact(),
                            sub.getStaffId(),
                            sub.getStaffRank(),
                            "General Fitness"
                    );
                    System.out.println("Assigned substitute staff: " + trainer.getName());
                } else {
                    System.out.println("ERROR: No trainers or checked-in staff available.");
                    return;
                }
            }

        } else {
            // User entered a TrainerManagement.Trainer ID
            int trainerId = Integer.parseInt(trainerInput);
            trainer = trainerManager.searchTrainer(trainerId);

            if (trainer == null) {
                System.out.println("TrainerManagement.Trainer not found. Checking checked-in staff...");

                Staff sub = staffManager.getLowestRankCheckedInStaff();
                if (sub != null) {
                    trainer = new Trainer(
                            sub.getName(),
                            sub.getAge(),
                            sub.getPhoneContact(),
                            sub.getStaffId(),
                            sub.getStaffRank(),
                            "General Fitness"
                    );
                    System.out.println("Assigned substitute staff: " + trainer.getName());
                } else {
                    System.out.println("No staff checked in. Cannot assign trainer.");
                    return;
                }
            }
        }

        // Prevent trainer from teaching two classes at same time
        if (classManager.isTrainerDoubleBooked(trainer, date, time)) {
            System.out.println("ERROR: This trainer is already teaching another class at this date and time.");
            System.out.println("Try a different date/time or assign another trainer.");
            return;
        }

        FitnessClass fc = new FitnessClass(className, date, time, desc, trainer);
        classManager.addClass(fc);

        System.out.println("Fitness class added successfully.");
    }



    /*
     * Removes a fitness class based on its name
     */
    public void removeClass() {
        System.out.print("Enter Class Name to remove: ");
        String name = input.nextLine();

        boolean removed = classManager.removeClass(name);
            
        if (!removed) {
            System.out.println("Class not found.");
        }
    }

    


    /*
     * Searches for a class and displays its info
     */
    public void searchClass() {

        System.out.print("Enter Class Name: ");
        String name = input.nextLine();
        FitnessClass c = classManager.searchClass(name);

        if (c != null) {
            c.displayClass();
        } else {
            System.out.println("Class not found.");
        }
    }

   

    /*
     * Shows all fitness classes
     */
    public void showAllClasses() {
        classManager.showAllClasses();
    }



    /*
     * Signs a member up for a fitness class
     */
    public void signMemberUp() {

        System.out.print("Enter MemberManagement.Member ID: ");
        int memberId = Integer.parseInt(input.nextLine());

        Member m = memberManager.searchMember(memberId);

        if (m == null) 
        {
            System.out.println("MemberManagement.Member not found.");
            return;
        }

        if (m.getMembershipType() == 1) 
        {
            System.out.println("Only Gold and Premium members can sign up for classes.");
            return;
        }

        System.out.print("Enter Class Name to join: ");
        String className = input.nextLine();
        FitnessClass c = classManager.searchClass(className);

        if (c == null) 
        {
            System.out.println("Class not found.");
            return;
        }

        c.addMember(m);
        System.out.println(m.getName() + " has been enrolled in " + c.getClassName());
    }

    

    /*
     * Cancels a class and notifies enrolled members
     *
     * @return void
     */
    public void cancelClass() {

        System.out.print("Enter Class Name to cancel: ");
        String name = input.nextLine();

        FitnessClass c = classManager.searchClass(name);

        if (c == null) 
        {
            System.out.println("Class not found. Cannot cancel.");
            return;
        }

        System.out.println("\nNotifying enrolled members...");
        System.out.println("---------------------------------------");
        c.showEnrolledMembers();
        boolean removed = classManager.removeClass(name);

        if (removed) 
        {
            System.out.println("\nThe class \"" + name + "\" has been successfully cancelled.");
        } else {
            System.out.println("Error cancelling class.");
        }
    }

    
    /*
     * Shows a trainer's schedule of assigned classes
     */
    public void showTrainerSchedule() {

        System.out.print("Enter TrainerManagement.Trainer ID: ");
        int id = Integer.parseInt(input.nextLine());

        Trainer trainer = trainerManager.searchTrainer(id);

        if (trainer == null) {
            System.out.println("TrainerManagement.Trainer not found.");
            return;
        }

        classManager.showTrainerSchedule(id);
    }
}
