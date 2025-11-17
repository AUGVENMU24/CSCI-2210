
/*
 * Angel Garcia Vega 
 * Stage 3
 */

import java.util.ArrayList;



/*
 * Manages all gym equipment (regular + specialized).
 */
public class EquipmentManager {
    
    private ArrayList<Equipment> regularEquipment;
    private ArrayList<SpecializedEquipment> specializedEquipments;



    /*
     * Creates a new Arraylist where Reg and Spec Equipment will be stored.
     */
    public EquipmentManager()
    {
        regularEquipment = new ArrayList<>();
        specializedEquipments = new ArrayList<>();

    }



    /*
     * Adds a new equipment item to the correct list.
     *
     * @param neweq The equipment being added.
     */
    public void addEquipment(Equipment neweq)
    {
        if(neweq instanceof SpecializedEquipment)
        {
            specializedEquipments.add((SpecializedEquipment)neweq);
        } else {
            regularEquipment.add(neweq);
        }
        System.out.println("Equipment was added Succesfully");
    }
    


    /*
     * Searches for equipment using an ID.
     *
     * @param id The ID to search for.
     * @return The equipment found, or null if nothing matches.
     */
    public Equipment searchEquipment(Integer id)
    {
        for(Equipment e: regularEquipment)
        {
            if(e.getId().equals(id)) return e;
        }
        for (SpecializedEquipment se: specializedEquipments)
        {
            if(se.getId().equals(id)) return se;
        }
        System.out.println("Equipment piece not found");
        return null;
    }



    /*
     * Removes equipment by ID if it exists.
     *
     * @param id The ID of the equipment to remove.
     * @return true if removed, false if not found.
     */
    public boolean removeEquipment(Integer id)
    {
        Equipment e = searchEquipment(id);
        if(e == null)
        {
            System.out.println("Equipment with ID " + id + " not found");
            return false;
        }
        if (e instanceof SpecializedEquipment){
            specializedEquipments.remove(e);
            System.out.println("Specialized equipment removed");
        } else {
            regularEquipment.remove(e);
            System.out.println("Regular equipment removed");
        }
        return true;
    }



    /*
     * Updates an equipment piece (name + avg life).
     *
     * @param id The ID of the equipment to update.
     * @param newName The new name.
     * @param newAvglife The new average life in years.
     * @return true if updated successfully, false if not found.
     */
    public boolean updateEquipment(Integer id, String newName, int newAvglife){
        
        Equipment e = searchEquipment(id);
        if(e == null)
        {
            System.out.println("Equipment with ID " + id + " not found");
            return false;
        }

        e.setName(newName);
        e.setAverageLife(newAvglife);

        if (e instanceof SpecializedEquipment){
            System.out.println("Specialized Equipment Updated Successfully");
        } else {
             System.out.println("Equipment Updated Successfully");
        }
        return true;
    }



    /*
     * Shows all equipment for both lists in a table format.
     */
    public void showAllEquipment() {

        System.out.println("\n==================== REGULAR EQUIPMENT ====================");

        if (regularEquipment.isEmpty()) 
        {
            System.out.println("No regular equipment available.\n");

        } else {

            System.out.printf(
                "+----+------------------------------+------------+-----------------+-----------+\n");
            System.out.printf(
                "| No | Name                         | ID         | Purchased       | Life(Yrs) |\n");
            System.out.printf(
                "+----+------------------------------+------------+-----------------+-----------+\n");

            int i = 1;
            for (Equipment e : regularEquipment) 
            {
                System.out.printf(
                    "| %-2d | %-28s | %-10d | %-15s | %-9d |\n",
                    i++,
                    e.getName(),
                    e.getId(),
                    e.getdatePurchased(),
                    e.getAvglife()
                );
            }

            System.out.printf(
            "+----+------------------------------+------------+-----------------+-----------+\n\n");
        }

        System.out.println("==================== SPECIALIZED EQUIPMENT ====================");

        if (specializedEquipments.isEmpty()) 
        {
            System.out.println("No specialized equipment available.\n");

        } else {

            System.out.printf(
                "+----+------------------------------+------------+-----------------+-----------+-----------------+--------------+--------------+\n");
            System.out.printf(
                "| No | Name                         | ID         | Purchased       | Life(Yrs) | Available Date  | Start Time   | End Time     |\n");
            System.out.printf(
                "+----+------------------------------+------------+-----------------+-----------+-----------------+--------------+--------------+\n");

            int i = 1;
            for (SpecializedEquipment se : specializedEquipments) {
                System.out.printf(
                    "| %-2d | %-28s | %-10d | %-15s | %-9d | %-15s | %-12s | %-12s |\n",
                    i++,
                    se.getName(),
                    se.getId(),
                    se.getdatePurchased(),
                    se.getAvglife(),
                    se.getdate(),
                    se.getstartTime(),
                    se.getendTime()
                );
            }

            System.out.printf(
            "+----+------------------------------+------------+-----------------+-----------+-----------------+--------------+--------------+\n\n");
        }
    }

   

}
