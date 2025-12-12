package EquipmentGUIManagement;

/**
 * Model class for a regular equipment item (POJO).
 */
public class Equipment {
    private String name;
    private Integer id;
    private String datePurchased;
    private int avglife; // average life in years

    public Equipment(String name, Integer id, String datePurchased, int avglife) {
        this.name = name;
        this.id = id;
        this.datePurchased = datePurchased;
        this.avglife = avglife;
    }

    // Getters / Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDatePurchased() { return datePurchased; }
    public void setDatePurchased(String datePurchased) { this.datePurchased = datePurchased; }

    public int getAvgLife() { return avglife; }
    public void setAvgLife(int avglife) { this.avglife = avglife; }

    @Override
    public String toString() {
        return String.format("Name: %s, ID: %d, Purchased: %s, Life: %d yrs",
                name, id, datePurchased, avglife);
    }

    // Convenience for console display
    public void displayEquipment() {
        System.out.println("Equipment Name: " + name);
        System.out.println("Equipment Id: " + id);
        System.out.println("Date Purchased: " + datePurchased);
        System.out.println("Average Life: " + avglife + " YRS\n");
    }
}
