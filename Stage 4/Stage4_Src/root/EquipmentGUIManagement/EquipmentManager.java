package EquipmentGUIManagement;
import EquipmentGUIManagement.Equipment;

import java.util.ArrayList;
import java.util.List;



public class EquipmentManager {

    private final ArrayList<Equipment> regularEquipment;
    private final ArrayList<SpecializedEquipment> specializedEquipments;

    public EquipmentManager() {
        regularEquipment = new ArrayList<>();
        specializedEquipments = new ArrayList<>();

        // Seed sample data
        regularEquipment.add(new Equipment("Threadmill", 1000, "12/24/25", 3));
        regularEquipment.add(new Equipment("Threadmill", 1100, "12/24/25", 3));
        regularEquipment.add(new Equipment("Threadmill", 1110, "12/24/25", 3));
        regularEquipment.add(new Equipment("Weight Bench", 2000, "1/19/25", 3));
        regularEquipment.add(new Equipment("Weight Bench", 2100, "1/21/25", 3));
        regularEquipment.add(new Equipment("Weight Bench", 2110, "1/20/25", 3));
        regularEquipment.add(new Equipment("Regular Bike", 3000, "1/4/24", 3));
        regularEquipment.add(new Equipment("Regular Bike", 3100, "6/12/25", 3));
        regularEquipment.add(new Equipment("Regular Bike", 3110, "7/12/23", 3));

        specializedEquipments.add(new SpecializedEquipment("Tanning Bed", 9000, "12/1/25", 8, "8:00 AM", "12:00 PM", "1/23/24"));
        specializedEquipments.add(new SpecializedEquipment("UnderWaterThreadmill", 9100, "12/1/25", 8, "10:00 AM", "2:00 PM", "1/23/24"));
        specializedEquipments.add(new SpecializedEquipment("Ski Simulator", 9110, "12/1/25", 8, "7:00 AM", "10:00 AM", "1/23/24"));
        specializedEquipments.add(new SpecializedEquipment("Golf Simulator", 9111, "12/1/25", 8, "10:00 AM", "3:00 PM", "1/23/24"));
        specializedEquipments.add(new SpecializedEquipment("Boat Rowing Simulator", 9211, "12/1/25", 8, "10:00 AM", "3:00 PM", "1/23/24"));
        specializedEquipments.add(new SpecializedEquipment("Peloton Bike", 9221, "1/3/25", 8, "7:00 AM", "10:00 AM", "1/23/24"));
    }

    // Accessors
    public List<Equipment> getRegularEquipment() {
        return regularEquipment;
    }

    public List<SpecializedEquipment> getSpecializedEquipment() {
        return specializedEquipments;
    }

    // Add equipment
    public void addEquipment(Equipment neweq) {
        if (neweq instanceof SpecializedEquipment) {
            specializedEquipments.add((SpecializedEquipment) neweq);
        } else {
            regularEquipment.add(neweq);
        }
    }

    // Search by ID across both lists
    public Equipment searchEquipment(Integer id) {
        for (Equipment e : regularEquipment) {
            if (e.getId().equals(id)) return e;
        }
        for (SpecializedEquipment se : specializedEquipments) {
            if (se.getId().equals(id)) return se;
        }
        return null;
    }

    // Remove by ID
    public boolean removeEquipment(Integer id) {
        Equipment e = searchEquipment(id);
        if (e == null) return false;
        if (e instanceof SpecializedEquipment) {
            specializedEquipments.remove(e);
        } else {
            regularEquipment.remove(e);
        }
        return true;
    }

    // Update name / avg life
    public boolean updateEquipment(Integer id, String newName, int newAvglife) {
        Equipment e = searchEquipment(id);
        if (e == null) return false;
        e.setName(newName);
        e.setAvgLife(newAvglife);
        return true;
    }

    // Create a text report
    public String getAllEquipmentReport() {
        StringBuilder sb = new StringBuilder();

        sb.append("===== REGULAR EQUIPMENT =====\n");
        if (regularEquipment == null || regularEquipment.isEmpty()) {
            sb.append("No regular equipment available.\n\n");
        } else {
            for (Equipment e : regularEquipment) {
                sb.append("Name: ").append(e.getName()).append("\n");
                sb.append("ID: ").append(e.getId()).append("\n");
                sb.append("Date Purchased: ").append(e.getDatePurchased()).append("\n");
                sb.append("Average Life: ").append(e.getAvgLife()).append(" years\n");
                sb.append("--------------------------------------\n");
            }
            sb.append("\n");
        }

        sb.append("===== SPECIALIZED EQUIPMENT =====\n");
        if (specializedEquipments == null || specializedEquipments.isEmpty()) {
            sb.append("No specialized equipment available.\n");
        } else {
            for (SpecializedEquipment se : specializedEquipments) {
                sb.append("Name: ").append(se.getName()).append("\n");
                sb.append("ID: ").append(se.getId()).append("\n");
                sb.append("Date Purchased: ").append(se.getDatePurchased()).append("\n");
                sb.append("Average Life: ").append(se.getAvgLife()).append(" years\n");
                sb.append("Date: ").append(se.getDate()).append("\n");
                sb.append("Start Time: ").append(se.getStartTime()).append("\n");
                sb.append("End Time: ").append(se.getEndTime()).append("\n");
                sb.append("--------------------------------------\n");
            }
        }

        return sb.toString();
    }


    public void showAllEquipment() {
        System.out.println(getAllEquipmentReport());
    }
}
