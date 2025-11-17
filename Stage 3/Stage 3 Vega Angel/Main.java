

/*
 * Angel Garcia Vega 
 * Stage 3
 */

public class Main{

    /*
     * Main method of program
     */
    public static void main(String[] args) {
    

    /*
     * DUMMY DATA
     * Equipment Specialized and Regular
     * Fitness Classes
     * Gym Members 
     * Gym Staff Members
     * Gym Trainers
     * Payments of Members
     */
    EquipmentManager eq = new EquipmentManager();
    FitnessClassManager fcm = new FitnessClassManager();
    MemberManager mem = new MemberManager();
    StaffManager sm = new StaffManager();
    TrainerManager tm = new TrainerManager();
    PaymentManager pay = new PaymentManager();
    
    Equipment T1 = new Equipment("Threadmill", 1000, "12/24/25", 3);
    Equipment T2 = new Equipment("Threadmill", 1100, "12/24/25", 3);
    Equipment T3 = new Equipment("Threadmill", 1110, "12/24/25", 3);
   
    eq.addEquipment(T1);
    eq.addEquipment(T2);
    eq.addEquipment(T3);

    Equipment W1 = new Equipment("Weight Bench", 2000, "1/19/25", 3);
    Equipment W2 = new Equipment("Weight Bench", 2100, "1/21/25", 3);
    Equipment W3 = new Equipment("Weight Bench", 2110, "1/20/25", 3);
   
    eq.addEquipment(W1);
    eq.addEquipment(W2);
    eq.addEquipment(W3);

    Equipment B1 = new Equipment("Regular Bike", 3000, "1/4/24", 3);
    Equipment B2 = new Equipment("Regular Bike", 3100, "6/12/25", 3);
    Equipment B3 = new Equipment("Regular Bike", 3110, "7/12/23", 3);

    eq.addEquipment(B1);
    eq.addEquipment(B2);
    eq.addEquipment(B3);

    SpecializedEquipment SE1 = new SpecializedEquipment("Tanning Bed", 9000, "12/1/25", 8, "8:00 AM", "12:00 PM","1/23/24");
    SpecializedEquipment SE2 = new SpecializedEquipment("UnderWaterThreadmill", 9100, "12/1/25", 8, "10:00 AM", "2:00 PM","1/23/24");
    SpecializedEquipment SE3 = new SpecializedEquipment("Ski Simulator", 9110,"12/1/25",8,"7:00 AM","10:00 AM","1/23/24");
    SpecializedEquipment SE4 = new SpecializedEquipment("Golf Simulator",9111,"12/1/25",8,"10:00 AM","3:00 PM","1/23/24");
    SpecializedEquipment SE5 = new SpecializedEquipment("Boat Rowing Simulator",9211,"12/1/25",8,"10:00 AM","3:00 PM","1/23/24");
    SpecializedEquipment SE6 = new SpecializedEquipment("Peloton Bike",9221,"1/3/25",8,"7:00 AM","10:00 AM","1/23/24");

    eq.addEquipment(SE1);
    eq.addEquipment(SE2);
    eq.addEquipment(SE3);
    eq.addEquipment(SE4);
    eq.addEquipment(SE5);
    eq.addEquipment(SE6);

    Trainer Trainer1 = new Trainer("Sophie", 21, "678-1112", 5000, 1, "Zumba");
    Trainer Trainer2 = new Trainer("Antonio", 26, "676-9807", 5100, 1, "Cardio");
    Trainer Trainer3 = new Trainer("Julie", 23, "458-1674", 5110, 1, "Body Sculpting");
    Trainer Trainer4 = new Trainer("Sanchez", 28, "128-1133", 5111, 1, "Martial Arts");
    Trainer Trainer5 = new Trainer("Louis", 32, "199-1123", 5200, 1, "Calisthetics");

    tm.addTrainer(Trainer1);
    tm.addTrainer(Trainer2);
    tm.addTrainer(Trainer3);
    tm.addTrainer(Trainer4);
    tm.addTrainer(Trainer5);

    FitnessClass F1 = new FitnessClass("Zumba","1/23/24", "12:00 PM", "a popular exercise program, integrates Latin music with choreographed dance movements", Trainer1);
    FitnessClass F2 = new FitnessClass("Cardio","1/23/24", "3:00 PM", "Your respiratory system will start working harder as you begin to breathe faster and more deeply", Trainer2);
    FitnessClass F3 = new FitnessClass("Martial Arts","1/23/24", "7:00 AM", "combat readiness applications; to non-violent exercising, ceremonial and competition; physical, mental, and spiritual development", Trainer4);
    
    fcm.addClass(F1);
    fcm.addClass(F2);
    fcm.addClass(F3);


    Member M1 = new Member("Angel", 19, 1000, 1234, "678-1121", "noreply@gmail.com", 1);
    Member M2 = new Member("Antwoin", 23, 1100, 1267, "633-1144", "antwoin12@gmail.com", 3);
    Member M3 = new Member("Selene", 45, 1110, 6767, "223-131", "seleny30@gmail.com", 2);
    Member M4 = new Member("Ezekiel", 56, 1111, 6969, "656-1681", "xekiel129@gmail.com", 1);
    Member M5 = new Member("Junes", 34, 1200, 2109, "655-2212", "sjaejl@gmail.com", 1);
    Member M6 = new Member("Cruz", 23, 1210, 7272, "093-1411", "dsajecruz@gmail.com", 3);
    Member M7 = new Member("Juan", 76, 1211, 9999, "420-6969", "wizardlyguy67@gmail.com", 3);
    Member M8 = new Member("Lupe", 27, 1300, 1111, "693-3331", "ddmegadu120doo@gmail.com", 2);
    Member M9 = new Member("Veronica", 21, 1310, 2221, "645-2911", "luan123@gmail.com", 1);
    Member M10 = new Member("Jesus", 18, 1311, 3331, "112-3411", "jesusbbl12@gmail.com", 1);

    mem.addMember(M1);
    mem.addMember(M2);
    mem.addMember(M3);
    mem.addMember(M4);
    mem.addMember(M5);
    mem.addMember(M6);
    mem.addMember(M7);
    mem.addMember(M8);
    mem.addMember(M9);
    mem.addMember(M10);

    Payment P1 = new Payment(M1, "1234", "12/25", "111");
    Payment P2 = new Payment(M2, "5678", "11/24", "222");
    Payment P3 = new Payment(M3, "9012", "10/26", "333");
    Payment P4 = new Payment(M4, "4321", "01/27", "444");
    Payment P5 = new Payment(M5, "8765", "08/23", "555");
    Payment P6 = new Payment(M6, "9999", "09/25", "666");
    Payment P7 = new Payment(M7, "2222", "03/24", "777");
    Payment P8 = new Payment(M8, "3333", "04/26", "888");
    Payment P9 = new Payment(M9, "4444", "06/27", "999");
    Payment P10 = new Payment(M10, "5555", "07/28", "123");

    Staff ST1 = new Staff("Joseph", 23, "1122-1234", 1000, 1);
    Staff ST2 = new Staff("Lusero", 28, "1122-1234", 1100, 2);
    Staff ST3 = new Staff("Juanita", 39, "1122-1234", 1110, 1);
    Staff ST4 = new Staff("Anita", 20, "1122-1234", 1111, 1);
    Staff ST5 = new Staff("John", 29, "1122-1234", 1200, 3);
    Staff ST6 = new Staff("Carlos", 21, "1122-1234", 1210, 2);
    Staff ST7 = new Staff("Bruno", 19, "1122-1234", 1211, 2);
    Staff ST8 = new Staff("Washington", 67, "1122-1234", 1300, 1);

    sm.addStaff(ST1);
    sm.addStaff(ST2);
    sm.addStaff(ST3);
    sm.addStaff(ST4);
    sm.addStaff(ST5);
    sm.addStaff(ST6);
    sm.addStaff(ST7);
    sm.addStaff(ST8);

    
    GymMainMenu mainMenu = new GymMainMenu(eq, mem, pay, tm, fcm, sm);
    mainMenu.showMenu();

    }
}
