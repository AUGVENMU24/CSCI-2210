package EquipmentGUIManagement;


public class SpecializedEquipmentGUI extends EquipmentGUI {
    private String date;       // availability date
    private String startTime;
    private String endTime;

    public SpecializedEquipmentGUI(String name, Integer id, String datePurchased, int avglife,
                                String startTime, String endTime, String date) {
        super(name, id, datePurchased, avglife);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters / Setters
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }


    public void setAvailability(String date, String startTime, String endTime) {
        setDate(date);
        setStartTime(startTime);
        setEndTime(endTime);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Date: %s, Start: %s, End: %s",
                date, startTime, endTime);
    }
}
