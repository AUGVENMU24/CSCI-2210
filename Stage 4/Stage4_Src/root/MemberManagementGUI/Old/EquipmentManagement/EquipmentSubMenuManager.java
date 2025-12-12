package MemberManagementGUI.Old.EquipmentManagement;
import MemberManagementGUI.Old.EquipmentManagement.SpecializedEquipmentManagement.SpecializedEquipment;

/*
 * Angel Garcia Vega 
 * Stage 3
 */

import java.util.Scanner;

public class EquipmentSubMenuManager {

    private EquipmentManager equipmentManager; 
    private Scanner input;
    

    /*
     * Sets up the submenu manager
     *
     * @param eq The Old.EquipmentManagement.EquipmentManager this submenu will use
     */
    public EquipmentSubMenuManager(EquipmentManager eq)
    {
        this.equipmentManager = eq;
        input = new Scanner(System.in);
    }


    /*
     * Creates a new equipment item (regular or specialized)
     *
     * @return void
     */
    public void createNewEquipment() {

        System.out.print("Do you want to create (1) Regular Old.EquipmentManagement.Equipment or (2) Special Old.EquipmentManagement.Equipment: ");
        int choice = Integer.parseInt(input.nextLine());

        System.out.print("Enter Name for Old.EquipmentManagement.Equipment: ");
        String name = input.nextLine().trim();

        // Making sure ID is Valid
        Integer id = null;

        while (id == null) {
            System.out.print("Enter Id of Old.EquipmentManagement.Equipment (4 Integers): ");

            String idInput = input.nextLine().trim();

            if (idInput.isEmpty()) 
            {
                System.out.println("ERROR: Old.EquipmentManagement.Equipment ID cannot be empty.");
                continue;
            }

            try {

                id = Integer.parseInt(idInput);

            } catch (NumberFormatException e) {

                System.out.println("ERROR: Old.EquipmentManagement.Equipment ID must be a number.");

            }
        }

        System.out.print("Enter date of purchase for Old.EquipmentManagement.Equipment: ");
        String datePurchased = input.nextLine().trim();

        System.out.print("Enter Average LifeSpan of Old.EquipmentManagement.Equipment in years: ");
        int avglife = Integer.parseInt(input.nextLine());

        Equipment exists = equipmentManager.searchEquipment(id);
        if (exists != null) 
        {
            System.out.println("ERROR: Old.EquipmentManagement.Equipment with this ID already exists.");
            return;
        }


        if (choice == 1) 
        {
            Equipment eq = new Equipment(name, id, datePurchased, avglife);
            equipmentManager.addEquipment(eq);
            System.out.println("Regular equipment added successfully.");
            return;
        }

        if (choice == 2) 
        {

            System.out.print("Enter Availability Date (e.g., 12/23/25): ");
            String date = input.nextLine().trim();

            System.out.print("Enter Start Time (e.g., 9:00 AM): ");
            String startTime = input.nextLine().trim();

            System.out.print("Enter End Time (e.g., 11:00 AM): ");
            String endTime = input.nextLine().trim();

            // Correct constructor order
            SpecializedEquipment seq = new SpecializedEquipment(name, id, datePurchased, avglife, startTime, endTime, date);

            equipmentManager.addEquipment(seq);
            System.out.println("Specialized equipment added successfully.");
            return;
        }

        System.out.println("Invalid selection. Please choose 1 or 2.");
    }



    /*
     * Searches for equipment and prints its info
     */
    public void searchEquipment() {

        System.out.println("Enter Old.EquipmentManagement.Equipment ID to search: ");
        int id = Integer.parseInt(input.nextLine());

        Equipment eq = equipmentManager.searchEquipment(id);

        if (eq != null) 
        {
            eq.displayEquipment();

        } else {
            
            System.out.println("Old.EquipmentManagement.Equipment with ID " + id + " not found.");
        }
    }

    

    /*
     * Removes an equipment item by its ID
     */
    public void removeEquipment() {
        System.out.print("Enter Old.EquipmentManagement.Equipment ID to remove: ");
        int id = Integer.parseInt(input.nextLine());

        Equipment eq = equipmentManager.searchEquipment(id);

        if (eq == null) 
        {
            System.out.println("Old.EquipmentManagement.Equipment with ID " + id + " not found.");
            return;
        }

        boolean removed = equipmentManager.removeEquipment(id);

        if (removed) 
        {
            System.out.println("Old.EquipmentManagement.Equipment successfully removed.");
        } else {
            System.out.println("Failed to remove equipment.");
        }
    }

    /*
     * Updates equipment info (regular or specialized)
     */
    public void updateEquipment() {

        System.out.print("Enter Old.EquipmentManagement.Equipment ID to update: ");
        int id = Integer.parseInt(input.nextLine());

        Equipment eq = equipmentManager.searchEquipment(id);

        if (eq == null) 
        {
            System.out.println("Old.EquipmentManagement.Equipment not found. No update performed.");
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
        if (!(eq instanceof SpecializedEquipment)) 
        {
            equipmentManager.updateEquipment(id, newName, newLife);
            return;
        }


        // Specialized Old.EquipmentManagement.Equipment Extra Fields
        SpecializedEquipment se = (SpecializedEquipment) eq;


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


        // Apply to the Old.EquipmentManagement.Equipment object
        se.setName(newName);
        se.setAverageLife(newLife);
        se.setAvailability(newDate, newStart, newEnd);

        System.out.println("Specialized Old.EquipmentManagement.Equipment Updated Successfully!");
    }



    /*
     * Displays all equipment from the manager
     */
    public void showAllEquipment() 
    {
        equipmentManager.showAllEquipment();
    }
}

