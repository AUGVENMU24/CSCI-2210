
/*
 * Angel Garcia Vega 
 * Stage 3
 */


 //Basic equipment info for the gym
public class Equipment {
    private String name;
    private Integer id;
    private String datePurchased;
    private int avglife; //average life in years 


    /*
     * Create a new piece of Equipment.
     * @param name           The name of the equipment.
     * @param id             The unique ID number of the equipment.
     * @param datePurchased  The date the equipment was purchased.
     * @param avglife        The average lifespan of the equipment in years.
     */
    public Equipment(String name, Integer id, String datePurchased, int avglife)
    {
        this.name = name;
        this.id = id;
        this.datePurchased = datePurchased;
        this.avglife = avglife;
    }


    /*
     * @return The equipment name.
     */
    public String getName()
    {
        return name;
    }


    /*
     * @return The date it was purchased.
     */
    public String getdatePurchased()
    {
        return datePurchased;
    }


    /*
     * @return The equipment ID.
     */
    public Integer getId()
    {
        return id;
    }


    /**
     * @return The average life in years.
     */
    public int getAvglife()
    {
        return avglife;
    }


    /*
     * Sets a new name.
     * @param name The updated name.
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /*
     * Sets a new purchase date.
     *
     * @param datePurchased The updated date.
     */
    public void setDatePurchased(String datePurchased)
    {
        this.datePurchased = datePurchased;
    }


    /*
     * Sets a new average life.
     *
     * @param avglife The updated life in years.
     */
    public void setAverageLife(Integer avglife)
    {
        this.avglife = avglife;
    }

    

    // Display Equipment information 
    public void displayEquipment()
    {
        System.out.println("Equipment Name: " + name);
        System.out.println("Equipment Id: " + id);
        System.out.println("Date Purchased: " + datePurchased);
        System.out.println("Average Life: " + avglife + " YRS\n");
    }


}


