
/*
   Jacey Tsosie
 * Stage 3
 * 
 */

import java.util.ArrayList;

public class FitnessClass {

    private String className;
    private String date;
    private String time;
    private String description;
    private Trainer trainer;

    private ArrayList<Member> enrolledMembers = new ArrayList<>();



    /*
     * Creates a new fitness class
     *
     * @param className The name of the class
     * @param date The class date
     * @param time The class time
     * @param description A short class description
     * @param trainer The trainer leading the class
     */
    public FitnessClass(String className, String date, String time,String description, Trainer trainer) {

        this.className = className;
        this.date = date;
        this.time = time;
        this.description = description;
        this.trainer = trainer;
    }



    /*
     * @return The class name
     */
    public String getClassName() 
    { 
        return className; 
    }
    

    /*
     * @return The class date
     */
    public String getDate()
    { 
        return date; 
    }
    

    /*
     * @return The class time
     */
    public String getTime()
    { 
        return time; 
    }
    

    /*
     * @return The class description
     */
    public String getDescription()
    { 
        return description; 
    }
    

    /*
     * @return The assigned trainer
     */
    public Trainer getTrainer()
    { 
        return trainer; 
    }


    /*
     * Sets a new trainer for this class
     * @param trainer The updated trainer
     */
    public void setTrainer(Trainer trainer) 
    {
        this.trainer = trainer;
    }


    /*
     * Adds a member to the class
     * @param m The member to enroll
     */
    public void addMember(Member m) 
    {
        enrolledMembers.add(m);
    }


    /*
     * Converts membership type into readable text
     *
     * @param type The membership type number
     * @return The membership name
     */
    private String membershipToString(int type) 
    {
        switch (type) 
        {
            case 2: return "Gold";
            case 3: return "Premium";
            default: return "Basic";
        }
    }


    /*
     * Shows all class details and enrolled members
     */
    public void displayClass() {

        System.out.println("Class: " + className);
        System.out.println("Trainer: " + trainer.getName());
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Description: " + description);


        if (enrolledMembers.isEmpty()) {
            System.out.println("Enrolled Members: none");
        } else {
            
            System.out.println("Enrolled Members:");
            for (Member m : enrolledMembers) 

            {
                System.out.println(" - " + m.getName() + " (" + membershipToString(m.getMembershipType()) + ")");
            }
        }

        System.out.println("---------------------------------------");
    }



    /*
     * Shows only the names of enrolled members
     */
    public void showEnrolledMembers() {
        if (enrolledMembers.isEmpty()) 
        {
            System.out.println("No members enrolled.");
            return;
        }

        for (Member m : enrolledMembers) 
        {
            System.out.println(" - " + m.getName());
        }
    }


}
