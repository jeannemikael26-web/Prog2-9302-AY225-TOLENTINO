/*
 * Student Record System
 * Programmer: Jeanne Mikael Tolentino
 * Student ID: 230853916
 */

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentRecordSystem extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtID, txtName, txtGrade;
    private JButton btnAdd, btnDelete;

    public StudentRecordSystem() {
        setTitle("Student Records - Jeanne Mikael Tolentino [230853916]");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponents();
        loadCSV();
    }

    private void initializeComponents() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Table
        String[] columns = {"Student ID", "Name", "Grade"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(tableModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        txtID = new JTextField(10);
        txtName = new JTextField(10);
        txtGrade = new JTextField(5);

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(txtID);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(txtGrade);

        // Buttons
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(e -> addStudent());
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteStudent());

        inputPanel.add(btnAdd);
        inputPanel.add(btnDelete);

        panel.add(inputPanel, BorderLayout.NORTH);

        add(panel);
    }

    private void loadCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("MOCK_DATA.csv"))) {
            String line;
            boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) { header = false; continue; } // skip header
                String[] data = line.split(",", -1);
                if (data.length >= 3) {
                    tableModel.addRow(new Object[]{data[0], data[1], data[2]});
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "MOCK_DATA.csv not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addStudent() {
        if (txtID.getText().isEmpty() || txtName.getText().isEmpty() || txtGrade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        tableModel.addRow(new Object[]{txtID.getText(), txtName.getText(), txtGrade.getText()});
        txtID.setText(""); txtName.setText(""); txtGrade.setText("");
    }

    private void deleteStudent() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row first!", "Delete Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        tableModel.removeRow(row);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentRecordSystem().setVisible(true));
    }
}
