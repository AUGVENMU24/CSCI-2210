package EquipmentGUIManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class EquipmentManagerWindow {

    private final EquipmentManager manager;

    private JFrame frame;
    private JTable regularTable;
    private JTable specializedTable;
    private DefaultTableModel regularModel;
    private DefaultTableModel specializedModel;

    public EquipmentManagerWindow(EquipmentManager manager) {
        this.manager = manager;
        SwingUtilities.invokeLater(this::initGUI);
    }

    private void initGUI() {
        frame = new JFrame("Gym Equipment Manager");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        // Top toolbar with actions
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addBtn = new JButton("Add Equipment");
        JButton removeBtn = new JButton("Remove by ID");
        JButton updateBtn = new JButton("Update by ID");
        JButton searchBtn = new JButton("Search by ID");
        JButton reportBtn = new JButton("Show Report (text)");
        JButton refreshBtn = new JButton("Refresh Tables");

        topPanel.add(addBtn);
        topPanel.add(removeBtn);
        topPanel.add(updateBtn);
        topPanel.add(searchBtn);
        topPanel.add(reportBtn);
        topPanel.add(refreshBtn);

        frame.add(topPanel, BorderLayout.NORTH);

        // Tabbed view: Regular | Specialized
        JTabbedPane tabs = new JTabbedPane();

        // Regular table
        String[] regularCols = {"No", "Name", "ID", "Purchased", "Life(Yrs)"};
        regularModel = new DefaultTableModel(regularCols, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        regularTable = new JTable(regularModel);
        tabs.addTab("Regular Equipment", new JScrollPane(regularTable));

        // Specialized table
        String[] specCols = {"No", "Name", "ID", "Purchased", "Life(Yrs)", "Date", "Start", "End"};
        specializedModel = new DefaultTableModel(specCols, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        specializedTable = new JTable(specializedModel);
        tabs.addTab("Specialized Equipment", new JScrollPane(specializedTable));

        frame.add(tabs, BorderLayout.CENTER);

        // Wire actions
        addBtn.addActionListener(e -> showAddDialog());
        removeBtn.addActionListener(e -> removeByIdDialog());
        updateBtn.addActionListener(e -> updateByIdDialog());
        searchBtn.addActionListener(e -> searchByIdDialog());
        reportBtn.addActionListener(e -> showReportDialog());
        refreshBtn.addActionListener(e -> refreshTables());

        refreshTables();
        frame.setVisible(true);
    }

    private void refreshTables() {
        // Regular
        regularModel.setRowCount(0);
        List<Equipment> reg = manager.getRegularEquipment();
        int i = 1;
        for (Equipment e : reg) {
            regularModel.addRow(new Object[]{i++, e.getName(), e.getId(), e.getDatePurchased(), e.getAvgLife()});
        }

        // Specialized
        specializedModel.setRowCount(0);
        List<SpecializedEquipment> spec = manager.getSpecializedEquipment();
        i = 1;
        for (SpecializedEquipment s : spec) {
            specializedModel.addRow(new Object[]{i++, s.getName(), s.getId(), s.getDatePurchased(),
                    s.getAvgLife(), s.getDate(), s.getStartTime(), s.getEndTime()});
        }
    }

    // Add equipment via a sequence of dialog boxes
    private void showAddDialog() {
        String[] options = {"Regular", "Specialized"};
        int choice = JOptionPane.showOptionDialog(frame, "Choose equipment type:", "Add Equipment",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.CLOSED_OPTION) return;

        try {
            String name = JOptionPane.showInputDialog(frame, "Name:");
            if (name == null) return;
            String idStr = JOptionPane.showInputDialog(frame, "ID (integer):");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr.trim());

            // Check duplicate
            if (manager.searchEquipment(id) != null) {
                JOptionPane.showMessageDialog(frame, "ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String datePurchased = JOptionPane.showInputDialog(frame, "Date Purchased (e.g. 1/1/24):");
            if (datePurchased == null) return;
            String lifeStr = JOptionPane.showInputDialog(frame, "Average life (years):");
            if (lifeStr == null) return;
            int life = Integer.parseInt(lifeStr.trim());

            if (choice == 0) {
                // Regular
                Equipment eq = new Equipment(name, id, datePurchased, life);
                manager.addEquipment(eq);
            } else {
                String start = JOptionPane.showInputDialog(frame, "Start Time (e.g. 8:00 AM):");
                if (start == null) return;
                String end = JOptionPane.showInputDialog(frame, "End Time (e.g. 12:00 PM):");
                if (end == null) return;
                String availDate = JOptionPane.showInputDialog(frame, "Availability Date (e.g. 1/23/24):");
                if (availDate == null) return;
                SpecializedEquipment seq = new SpecializedEquipment(name, id, datePurchased, life, start, end, availDate);
                manager.addEquipment(seq);
            }

            JOptionPane.showMessageDialog(frame, "Equipment added.");
            refreshTables();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid number input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeByIdDialog() {
        String idStr = JOptionPane.showInputDialog(frame, "Enter ID to remove:");
        if (idStr == null) return;
        try {
            int id = Integer.parseInt(idStr.trim());
            boolean removed = manager.removeEquipment(id);
            JOptionPane.showMessageDialog(frame, removed ? "Removed." : "ID not found.");
            refreshTables();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateByIdDialog() {
        String idStr = JOptionPane.showInputDialog(frame, "Enter ID to update:");
        if (idStr == null) return;
        try {
            int id = Integer.parseInt(idStr.trim());
            Equipment eq = manager.searchEquipment(id);
            if (eq == null) {
                JOptionPane.showMessageDialog(frame, "ID not found.");
                return;
            }

            String newName = JOptionPane.showInputDialog(frame, "New name (leave blank to keep):", eq.getName());
            if (newName == null) return;
            if (newName.trim().isEmpty()) newName = eq.getName();

            String newLifeStr = JOptionPane.showInputDialog(frame, "New average life (leave blank to keep):", String.valueOf(eq.getAvgLife()));
            if (newLifeStr == null) return;
            int newLife = eq.getAvgLife();
            if (!newLifeStr.trim().isEmpty()) newLife = Integer.parseInt(newLifeStr.trim());

            // Update base fields
            manager.updateEquipment(id, newName, newLife);

            // If specialized, ask for availability fields
            if (eq instanceof SpecializedEquipment) {
                SpecializedEquipment se = (SpecializedEquipment) eq;
                String newDate = JOptionPane.showInputDialog(frame, "New availability date (leave blank to keep):", se.getDate());
                if (newDate == null) return;
                if (!newDate.trim().isEmpty()) se.setDate(newDate);

                String newStart = JOptionPane.showInputDialog(frame, "New start time (leave blank to keep):", se.getStartTime());
                if (newStart == null) return;
                if (!newStart.trim().isEmpty()) se.setStartTime(newStart);

                String newEnd = JOptionPane.showInputDialog(frame, "New end time (leave blank to keep):", se.getEndTime());
                if (newEnd == null) return;
                if (!newEnd.trim().isEmpty()) se.setEndTime(newEnd);
            }

            JOptionPane.showMessageDialog(frame, "Updated.");
            refreshTables();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid numeric input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchByIdDialog() {
        String idStr = JOptionPane.showInputDialog(frame, "Enter ID to search:");
        if (idStr == null) return;
        try {
            int id = Integer.parseInt(idStr.trim());
            Equipment eq = manager.searchEquipment(id);
            if (eq == null) {
                JOptionPane.showMessageDialog(frame, "Not found.");
            } else {
                JOptionPane.showMessageDialog(frame, eq.toString(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showReportDialog() {
        String report = manager.getAllEquipmentReport();
        JTextArea ta = new JTextArea(report, 25, 80);
        ta.setEditable(false);
        ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane sp = new JScrollPane(ta);
        JOptionPane.showMessageDialog(frame, sp, "Full Equipment Report", JOptionPane.PLAIN_MESSAGE);
    }



}
