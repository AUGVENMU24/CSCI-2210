package MemberManagementGUI.Old;

import EquipmentGUIManagement.EquipmentGUI;
import EquipmentGUIManagement.EquipmentManagerGUI;
import EquipmentGUIManagement.SpecializedEquipmentGUI;

import java.util.Scanner;

public class EquipmentSubMenuManagerGUI {

    private EquipmentManagerGUI equipmentManager;
    private Scanner input;

    /*
     * Sets up the submenu manager
     * @param eq The EquipmentManager this submenu will use
     */
    public EquipmentSubMenuManagerGUI(EquipmentManagerGUI eq)
    {
        this.equipmentManager = eq;
        input = new Scanner(System.in);
    }

    public String createNewEquipmentFromGui(
            int choice,           // 1 = regular, 2 = special
            String name,
            int id,
            String datePurchased,
            int avglife,
            String availabilityDate, // only used if special
            String startTime,        // only used if special
            String endTime           // only used if special
    ) {
        // Check if ID already exists
        EquipmentGUI exists = equipmentManager.searchEquipment(id);
        if (exists != null) {
            return "ERROR: Equipment with this ID already exists.";
        }

        // Regular equipment
        if (choice == 1) {
            EquipmentGUI eq = new EquipmentGUI(name, id, datePurchased, avglife);
            equipmentManager.addEquipment(eq);
            return "Regular equipment added successfully.";
        }

        // Special equipment
        if (choice == 2) {
            SpecializedEquipmentGUI seq =
                    new SpecializedEquipmentGUI(name, id, datePurchased, avglife,
                            startTime, endTime, availabilityDate);
            equipmentManager.addEquipment(seq);
            return "Specialized equipment added successfully.";
        }

        return "Invalid selection. Please choose 1 or 2.";
    }




    /*
     * Searches for equipment and prints its info
     */
    public void searchEquipment() {

        System.out.println("Enter Equipment ID to search: ");
        int id = Integer.parseInt(input.nextLine());

        EquipmentGUI eq = equipmentManager.searchEquipment(id);

        if (eq != null)
        {
            eq.displayEquipment();

        } else {

            System.out.println("Equipment with ID " + id + " not found.");
        }
    }



    /*
     * Removes an equipment item by its ID
     */
    public void removeEquipment() {
        System.out.print("Enter Equipment ID to remove: ");
        int id = Integer.parseInt(input.nextLine());

        EquipmentGUI eq = equipmentManager.searchEquipment(id);

        if (eq == null)
        {
            System.out.println("Equipment with ID " + id + " not found.");
            return;
        }

        boolean removed = equipmentManager.removeEquipment(id);

        if (removed)
        {
            System.out.println("Equipment successfully removed.");
        } else {
            System.out.println("Failed to remove equipment.");
        }
    }

    /*
     * Updates equipment info (regular or specialized)
     */
    public void updateEquipment() {

        System.out.print("Enter Equipment ID to update: ");
        int id = Integer.parseInt(input.nextLine());

        EquipmentGUI eq = equipmentManager.searchEquipment(id);

        if (eq == null)
        {
            System.out.println("Equipment not found. No update performed.");
            return;
        }

        System.out.println("\n--- Leave any field blank to keep its current value ---");


        // Change Name or Keep the same
        System.out.println("Current Name: " + eq.getName());
        System.out.print("Enter new name: ");
        String newName = input.nextLine().trim();
        if (newName.isEmpty()) newName = eq.getName();


        // Change Avg life or Keep the same
        System.out.println("Current Life (yrs): " + eq.getAvglife());
        System.out.print("Enter new average life: ");
        String lifeInput = input.nextLine().trim();
        int newLife = eq.getAvglife();


        if (!lifeInput.isEmpty())
        {
            try {
                newLife = Integer.parseInt(lifeInput);
            } catch (Exception e) {
                System.out.println("Invalid number. Keeping previous life value.");
            }
        }


        // If not specialized equipment -> update and return
        if (!(eq instanceof SpecializedEquipmentGUI))
        {
            equipmentManager.updateEquipment(id, newName, newLife);
            return;
        }


        // Specialized Equipment Extra Fields
        SpecializedEquipmentGUI se = (SpecializedEquipmentGUI) eq;


        // Change Date or Keep the same
        System.out.println("Current Availability Date: " + se.getdate());
        System.out.print("Enter new date: ");
        String newDate = input.nextLine().trim();
        if (newDate.isEmpty()) newDate = se.getdate();


        // Change Start time or Keep the same
        System.out.println("Current Start Time: " + se.getstartTime());
        System.out.print("Enter new start time: ");
        String newStart = input.nextLine().trim();
        if (newStart.isEmpty()) newStart = se.getstartTime();


        // Change End Time or Keep the same
        System.out.println("Current End Time: " + se.getendTime());
        System.out.print("Enter new end time: ");
        String newEnd = input.nextLine().trim();
        if (newEnd.isEmpty()) newEnd = se.getendTime();


        // Apply to the Equipment object
        se.setName(newName);
        se.setAverageLife(newLife);
        se.setAvailability(newDate, newStart, newEnd);

        System.out.println("Specialized Equipment Updated Successfully!");
    }



    /*
     * Displays all equipment from the manager
     */
    public void showAllEquipment()
    {
        equipmentManager.showAllEquipment();
    }




}


