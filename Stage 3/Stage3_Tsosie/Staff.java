
/*
 * Jacey Tsosie 
 *  Stage 3
 * 
 */


public class Staff {

    private String name;
    private int age;
    private String phoneContact;
    private int staffId;
    private int staffRank; 


    /*
     * Creates a staff member with a name, age, contact info, ID, and rank
     * @param name Staff name
     * @param age Staff age
     * @param phoneContact Phone number for staff
     * @param staffId Unique staff ID
     * @param staffRank Rank level (1 = General, 2 = Assistant Manager, 3 = Manager)
     */
    public Staff(String name, int age, String phoneContact, int staffId, int staffRank) 
    {
        this.name = name;
        this.age = age;
        this.phoneContact = phoneContact;
        this.staffId = staffId;

        switch (staffRank) {
            case 2:
            case 3:
                this.staffRank = staffRank;
                break;
            default:
                this.staffRank = 1; 
        }
    }

    /*
     * @return Staff name
     */
    public String getName() 
    { 
        return name; 
    }


    /*
     * @return Staff age
     */
    public int getAge() 
    { 
        return age; 
    }


    /*
     * @return Staff Phone Number
     */
    public String getPhoneContact() 
    { 
        return phoneContact; 
    }
    

    /*
     * @return Staff ID
     */
    public int getStaffId() 
    { 
        return staffId; 
    }


    /*
     * @return Staff rank as a number
     */
    public int getStaffRank() 
    { 
        return staffRank; 
    }


    /*
     * Sets the staff name
     * @param name New name
     */
    public void setName(String name) 
    { 
        this.name = name; 
    }
    

    /*
     * Sets the staff age
     * @param age New age
     */
    public void setAge(int age) 
    { 
        this.age = age; 
    }


    /*
     * Sets the staff phone number
     * @param phoneContact New phone number
     */
    public void setPhoneContact(String phoneContact) 
    
    { 
        this.phoneContact = phoneContact; 
    }


    /*
     * Sets the staff rank (defaults to General Staff if invalid)
     * @param staffRank New rank level
     */
    public void setStaffRank(int staffRank) {
        switch (staffRank) {
            case 2:
            case 3:
                this.staffRank = staffRank;
                break;
            default:
                this.staffRank = 1;
        }
    }



    /*
     * Sets the staff ID
     * @param staffId New staff ID
     */
    public void setStaffId(int staffId) 
    { 
        this.staffId = staffId; 
    }



    /*
     * @return Staff rank in readable text
     */
    public String getStaffRankName() {
        switch (staffRank) {
            case 2: return "Assistant Manager";
            case 3: return "Manager";
            default: return "General Staff";
        }
    }


    /*
     * Displays information about this staff member
     */
    public void displayStaff() {
        System.out.println("ID: " + staffId +
                ", Name: " + name +
                ", Age: " + age +
                ", Phone: " + phoneContact +
                ", Rank: " + getStaffRankName());
    }
}
