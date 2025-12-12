package MemberManagementGUI;

import EquipmentGUIManagement.EquipmentManagerWindow;
import FitnessClassManagementGUI.FitnessClassGUI;
import MemberManagementGUI.MemberMenuGUI.MemberMenuGUI;
import EquipmentGUIManagement.EquipmentManager;
import MemberManagementGUI.MemberManagement.MemberManager;
import PaymentManagementGUI.PaymentGUI;
import StaffManagementGUI.StaffManager;
import StaffManagementGUI.StaffManagerGUI;
import StaffManagementGUI.Staff;
import TrainerManagementGUI.TrainerGUI;
import TrainerManagementGUI.TrainerManager;
import TrainerManagementGUI.Trainer;
import FitnessClassManagementGUI.FitnessClass;
import FitnessClassManagementGUI.FitnessClassManager;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * GUI
 * The GUI class,
 * Provides the means for constructing a GUI
*/

public class Main_GUI {
  private JFrame frame;
  private JPanel mainPanel;
  private MemberManager memberManager;
  private EquipmentManager equipmentManager;
  private StaffManager staffManager;
  private TrainerManager trainerManager;
  private FitnessClassManager fitnessClassManager;

  public Main_GUI() {
      memberManager = new MemberManager();
      equipmentManager = new EquipmentManager();
      staffManager = new StaffManager();
      trainerManager = new TrainerManager();
      fitnessClassManager = new FitnessClassManager();

      //Mock Member Data
      memberManager.addMember("Alice Johnson", 28, 1001, 1234, "555-1234", "alice.johnson@email.com", 1);
      memberManager.addMember("Bob Smith", 35, 1002, 5678, "555-5678", "bob.smith@email.com", 2);
      memberManager.addMember("Charlie Davis", 22, 1003, 9012, "555-9012", "charlie.davis@email.com", 3);
      memberManager.addMember("Diana Ross", 42, 1004, 3456, "555-3456", "diana.ross@email.com", 2);
      memberManager.addMember("Ethan Lee", 30, 1005, 7890, "555-7890", "ethan.lee@email.com", 1);
      memberManager.addMember("Fiona Green", 25, 1006, 2345, "555-2345", "fiona.green@email.com", 3);
      memberManager.addMember("George King", 38, 1007, 6789, "555-6789", "george.king@email.com", 2);
      memberManager.addMember("Hannah Scott", 31, 1008, 0123, "555-0123", "hannah.scott@email.com", 1);
      memberManager.addMember("Ian Clark", 27, 1009, 4567, "555-4567", "ian.clark@email.com", 3);
      memberManager.addMember("Julia Adams", 29, 1010, 8901, "555-8901", "julia.adams@email.com", 1);



      // Mock Staff
      staffManager.addStaff(new Staff("Alice Johnson", 30, "555-1234", 1001, 1)); // Rank 1
      staffManager.addStaff(new Staff("Bob Smith", 45, "555-5678", 1002, 2));     // Rank 2
      staffManager.addStaff(new Staff("Carol Williams", 28, "555-8765", 1003, 3)); // Rank 3
      staffManager.addStaff(new Staff("David Brown", 38, "555-4321", 1004, 2));
      staffManager.addStaff(new Staff("Eve Davis", 25, "555-1357", 1005, 1));
      staffManager.addStaff(new Staff("Frank Miller", 50, "555-2468", 1006, 3));
      staffManager.addStaff(new Staff("Grace Wilson", 33, "555-9753", 1007, 2));

      //Mock Trainers
      // Add mock trainers
      trainerManager.addTrainer(new Trainer("Alex Carter", 32, "555-1010", 2001, 2, "Strength Training"));
      trainerManager.addTrainer(new Trainer("Bianca Lee", 28, "555-1020", 2002, 1, "Yoga"));
      trainerManager.addTrainer(new Trainer("Carlos Rivera", 40, "555-1030", 2003, 3, "Cardio & Endurance"));
      trainerManager.addTrainer(new Trainer("Dana Kim", 35, "555-1040", 2004, 2, "Pilates"));
      trainerManager.addTrainer(new Trainer("Ethan Smith", 30, "555-1050", 2005, 1, "HIIT"));
      trainerManager.addTrainer(new Trainer("Fiona Adams", 27, "555-1060", 2006, 1, "Functional Training"));
      trainerManager.addTrainer(new Trainer("George Thompson", 45, "555-1070", 2007, 3, "Weightlifting"));
      trainerManager.addTrainer(new Trainer("Hannah White", 33, "555-1080", 2008, 2, "CrossFit"));
      trainerManager.addTrainer(new Trainer("Isaac Brooks", 38, "555-1090", 2009, 2, "Martial Arts"));
      trainerManager.addTrainer(new Trainer("Julia Patel", 29, "555-1100", 2010, 1, "Dance Fitness"));

      //Mock Fitness Classes
      // Add mock fitness classes
      fitnessClassManager.addClass(new FitnessClass("Morning Yoga", "2025-12-12", "08:00", "Gentle yoga for all levels", trainerManager.searchTrainer(2002)));
      fitnessClassManager.addClass(new FitnessClass("HIIT Blast", "2025-12-12", "09:30", "High-intensity interval training", trainerManager.searchTrainer(2005)));
      fitnessClassManager.addClass(new FitnessClass("Strength 101", "2025-12-12", "11:00", "Intro to weightlifting techniques", trainerManager.searchTrainer(2001)));
      fitnessClassManager.addClass(new FitnessClass("Pilates Core", "2025-12-12", "12:30", "Focus on core strength and stability", trainerManager.searchTrainer(2004)));
      fitnessClassManager.addClass(new FitnessClass("Cardio Endurance", "2025-12-13", "07:30", "Improve stamina with cardio circuits", trainerManager.searchTrainer(2003)));
      fitnessClassManager.addClass(new FitnessClass("Dance Fitness", "2025-12-13", "10:00", "Fun dance routines to get moving", trainerManager.searchTrainer(2010)));
      fitnessClassManager.addClass(new FitnessClass("Functional Training", "2025-12-13", "14:00", "Exercises for daily movement efficiency", trainerManager.searchTrainer(2006)));
      fitnessClassManager.addClass(new FitnessClass("CrossFit Challenge", "2025-12-14", "09:00", "CrossFit-style functional workouts", trainerManager.searchTrainer(2008)));
      fitnessClassManager.addClass(new FitnessClass("Martial Arts Basics", "2025-12-14", "11:00", "Intro to basic martial arts techniques", trainerManager.searchTrainer(2009)));
      fitnessClassManager.addClass(new FitnessClass("Weightlifting Advanced", "2025-12-14", "13:00", "Advanced lifting techniques for experienced participants", trainerManager.searchTrainer(2007)));


      frame = new JFrame("H.I.I.T Labs Gym - Main Menu");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);

      //Frame Layout
      frame.setLayout(new BorderLayout());

      //Page Title
      JLabel pageTitle = new JLabel("H.I.I.T Labs Gym - Main Menu");
      pageTitle.setHorizontalAlignment(JLabel.CENTER);
      frame.add(pageTitle, BorderLayout.NORTH);

      //Primary Panel
      mainPanel = new JPanel();
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

      //Wrap the Main Panel within a scroll pane
      JScrollPane scrollPane = new JScrollPane(mainPanel);
      frame.add(scrollPane, BorderLayout.CENTER); //Add the scroll pane

      //Add Sections to the Main Panel
      addSection("Member Management System",
              "Open Member Management", "member");//A Helper function as to not have to manually create each section

      addSection("Payment Management",
              "Open Payment Management", "payment");

      addSection("Fitness Class Management",
              "Open Fitness Class Management", "fit class");

      addSection("Equipment Management",
              "Open Equipment Management", "equipment");

      addSection("Trainer Management",
              "Open Trainer Management", "trainer");

      addSection("Employee Management",
              "Open Employee Management (Staff)", "employee");

      frame.setSize(400,400);
      frame.setVisible(true);

  }

  private void addSection(String labelText, String buttonText, String actionButtonCommand) {
    JLabel label = new JLabel(labelText);
    label.setAlignmentX(Component.CENTER_ALIGNMENT);

    JButton button = new JButton(buttonText);
    button.setActionCommand(actionButtonCommand);
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.addActionListener(menuListener);

    mainPanel.add(Box.createVerticalStrut(40));
    mainPanel.add(label);
    mainPanel.add(button);
  }

  private ActionListener menuListener = e -> {
      switch (e.getActionCommand()) {
          case "member":
              new MemberMenuGUI(memberManager);
              break;
          case "payment":
              new PaymentGUI(memberManager);
              break;
          case "fit class":
              new FitnessClassGUI(fitnessClassManager, trainerManager);
              break;
          case "equipment":
              new EquipmentManagerWindow(equipmentManager);
              break;
          case "trainer":
              new TrainerGUI(trainerManager);
              break;
          case "employee":
              new StaffManagerGUI(staffManager);
              break;
          default:
              break;
      }
  };

}
