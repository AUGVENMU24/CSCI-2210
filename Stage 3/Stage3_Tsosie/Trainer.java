
/*
 * Jacey Tsosie 
 *  Stage 3
 * 
 */

public class Trainer extends Staff {

    private String specialty; // e.g. Yoga, HIIT, Strength Training


    /*
     * Creates a trainer with all staff info plus a specialty
     * @param name Trainer name
     * @param age Trainer age
     * @param phoneContact Phone number for trainer
     * @param staffId Trainer ID
     * @param staffRank Trainer rank level
     * @param specialty Area of expertise for the trainer
     */
    public Trainer(String name, int age, String phoneContact, int staffId, int staffRank, String specialty) 
    {
        super(name, age, phoneContact, staffId, staffRank);
        this.specialty = specialty;
    }

    /*
     * @return Trainer specialty area
     */
    public String getSpecialty() 
    {
        return specialty;
    }


    /*
     * Sets the trainer specialty
     * @param specialty New specialty
     */
    public void setSpecialty(String specialty) 
    {
        this.specialty = specialty;
    }

    /*
     * Displays trainer information including specialty
     * @Override inherited method
     */

    public void displayStaff() {
        System.out.println("ID: " + getStaffId() +
            ", Name: " + getName() +
            ", Age: " + getAge() +
            ", Phone: " + getPhoneContact() +
            ", Rank: " + getStaffRankName() +
            ", Specialty: " + specialty);
    }
}
