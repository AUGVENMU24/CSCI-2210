package FitnessClassManagementGUI;
import TrainerManagementGUI.Trainer;
/*
   Jacey Tsosie
 * Stage 3
 * 
 */

import java.util.ArrayList;

public class FitnessClassManager {


    private ArrayList<FitnessClass> classList;


    /*
     * Creates the list that stores all fitness classes
     */
    public FitnessClassManager() 
    {
        classList = new ArrayList<>();
    }



    /*
     * Adds a new fitness class to the list
     * @param c The class being added
     */
    public void addClass(FitnessClass c) 
    {
        classList.add(c);
        System.out.println("Fitness Class was added Succesfully");
    }



    /*
     * Removes a fitness class using its name
     * @param className The name of the class to remove
     * @return true if removed, false if it was not found
     */
    public boolean removeClass(String className) {
        FitnessClass c = searchClass(className);
        
        if (c != null) 
        {
            classList.remove(c);
            System.out.println("Class was removed.");
            return true;
        }

        return false;
    }



    /*
     * Searches for a fitness class based on name
     * @param className The name to search for
     * @return The found class or null if not found
     */
    public FitnessClass searchClass(String className) {
        for (FitnessClass c : classList) 
        {
            if (c.getClassName().equalsIgnoreCase(className)) {
                return c;
            }
        }
        
        return null;
    }



    /*
     * Shows all fitness classes in the schedule
     */
    public void showAllClasses() {

        /*
        if (classList.isEmpty()) 
        {
            System.out.println("No fitness classes available.");
            return;
        }

        System.out.println("\n========= Fitness Class Schedule =========");
        for (FitnessClass c : classList) 
        {
            c.displayClass();
        }*/
    }



    /*
     * Shows all classes assigned to a specific trainer
     * @param trainerId The trainer's ID
     */
    public String showTrainerSchedule(int trainerId) {
        StringBuilder sb = new StringBuilder();
        boolean found = false;

        sb.append("===== Trainer Schedule for ID ").append(trainerId).append(" =====\n\n");

        for (FitnessClass c : classList) {
            if (c.getTrainer().getStaffId() == trainerId) {
                sb.append(c.displayClassAsString());
                sb.append("\n");
                found = true;
            }
        }

        if (!found) {
            sb.append("No classes assigned to this trainer.\n");
        }

        return sb.toString();

        /*
        boolean found = false;

        System.out.println("\n===== Trainer Schedule =====");

        for (FitnessClass c : classList)
        {
            if (c.getTrainer().getStaffId() == trainerId)
            {
                c.displayClass();
                found = true;
            }
        }

        if (!found)
        {
            System.out.println("No classes assigned to this trainer.");
        }*/
    }



    /*
     * Checks if a trainer is already booked at the same date and time
     * @param trainer The trainer to check
     * @param date The date to compare
     * @param time The time to compare
     * @return true if the trainer is double booked, otherwise false
     */
    public boolean isTrainerDoubleBooked(Trainer trainer, String date, String time) 
    {

        for (FitnessClass c : classList) 
        {
            if (c.getTrainer().getStaffId() == trainer.getStaffId()
                && c.getDate().equalsIgnoreCase(date)
                && c.getTime().equalsIgnoreCase(time)) 
            {
            return true; // Trainer has been Double Booked
            }
        }

        return false;
    }

    public ArrayList<FitnessClass> getClassList() {
        return classList;
    }

    public ArrayList<FitnessClass> getClassesForTrainers(int trainerId) {
        ArrayList<FitnessClass> matches = new ArrayList<>();

        for (FitnessClass c : classList) {
            if (c.getTrainer().getStaffId() == trainerId) {
                matches.add(c);
            }
        }
        return matches;
    }

}



