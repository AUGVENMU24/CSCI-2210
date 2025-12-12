package TrainerManagementGUI;
/* Jacey Tsosie
 *  Stage 3
 */

import java.util.ArrayList;

public class TrainerManager {

    private ArrayList<Trainer> trainerList;


    /*
     * Creates a trainer manager that stores all trainers
     */
    public TrainerManager() {
        trainerList = new ArrayList<>();
    }


    /*
     * Adds a trainer to the trainer list
     * @param t Trainer object to add
     */
    public void addTrainer(Trainer t) {
        trainerList.add(t);
    }


    /*
     * Removes a trainer based on ID
     * @param id Trainer ID to remove
     * @return true if removed successfully, false if not found
     */
    public boolean removeTrainer(int id) {
        Trainer t = searchTrainer(id);

        if (t == null) {
            System.out.println("Trainer not found.");
            return false;
        }

        trainerList.remove(t);
        System.out.println("Trainer removed successfully.");
        return true;
    }


    /*
     * Searches for a trainer using their staff ID
     * @param id Trainer ID to search for
     * @return Trainer object if found, otherwise null
     */
    public Trainer searchTrainer(int id) {
        for (Trainer t : trainerList) {
            if (t.getStaffId() == id) return t;
        }
        return null;
    }


    /*
     * Updates trainer information
     * @param id Trainer ID
     * @param newName Updated trainer name
     * @param newAge Updated trainer age
     * @param newPhone Updated phone number
     * @param newRank Updated trainer rank
     * @param newSpecialty Updated specialty field
     */
    public void updateTrainer(int id, String newName, Integer newAge, String newPhone, Integer newRank, String newSpecialty) {
        Trainer t = searchTrainer(id);
        if (t != null) {
            if (newName != null && !newName.isEmpty()) t.setName(newName);
            if (newAge != null) t.setAge(newAge);
            if (newPhone != null && !newPhone.isEmpty()) t.setPhoneContact(newPhone);
            if (newRank != null) t.setStaffRank(newRank);
            if (newSpecialty != null && !newSpecialty.isEmpty()) t.setSpecialty(newSpecialty);
        }
    }



    /*
     * Displays all trainers in the list
     */
    public void showAllTrainers() {
        if (trainerList.isEmpty()) {
            System.out.println("No trainers available.");
            return;
        }

        System.out.println("\n========= Trainer List =========");
        for (Trainer t : trainerList) {
            t.displayStaff();
        }
    }


    /*
     * @return All trainers in a list
     */
    public ArrayList<Trainer> getAllTrainers() {
        return trainerList;
    }
}
