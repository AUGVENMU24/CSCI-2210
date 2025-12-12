package MemberManagementGUI.Old.EquipmentManagement.SpecializedEquipmentManagement;/*
 * Angel Garcia Vega 
 * Stage 3
 */

import MemberManagementGUI.Old.EquipmentManagement.Equipment;

public class SpecializedEquipment extends Equipment {
    private String startTime;
    private String endTime;
    private String date;


    /*
     * Creates a new specialized equipment item with availability information
     * @param name Old.EquipmentManagement.Equipment name
     * @param id Old.EquipmentManagement.Equipment ID
     * @param datePurchased Date the equipment was purchased
     * @param avglife Average lifespan in years
     * @param startTime Start time for availability
     * @param endTime End time for availability
     * @param date Available date
     */
    public SpecializedEquipment(String name, Integer id, String datePurchased, int avglife, String startTime, String endTime, String date)
    {
        super(name, id, datePurchased, avglife);
        this.startTime = startTime.trim();
        this.endTime = endTime.trim();
        this.date = date.trim();
    }


    /*
     * Updates the availability schedule for this equipment
     * @param date New available date
     * @param startTime New start time
     * @param endTime New end time
     */
    public void setAvailability(String date, String startTime, String endTime)
    {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    /*
     * @return Date of availability
     */
    public String getdate()
    {
        return date;
    }


    /*
     * @return Start time of availability
     */
    public String getstartTime()
    {
        return startTime;
    }
   

    /*
     * @return End time of availability
     */
    public String getendTime()
    {
        return endTime;
    }

    
    /*
     * Displays equipment info (specialized uses parent display)
     */
    public void displayEquipment()
    {
        super.displayEquipment();
    }

}
