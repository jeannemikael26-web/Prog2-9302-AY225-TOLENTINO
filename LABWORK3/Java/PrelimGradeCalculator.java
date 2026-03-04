package LABWORK3.Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrelimGradeCalculator extends JFrame {
    private JTextField attendanceField, lab1Field, lab2Field, lab3Field;
    private JTextArea outputArea;
    private JButton calculateButton, clearButton;
    
    public PrelimGradeCalculator() {
        setTitle("Prelim Grade Calculator");
        setSize(700, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 246, 250));
        
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setPreferredSize(new Dimension(700, 100));
        headerPanel.setLayout(new GridBagLayout());
        
        JPanel headerContent = new JPanel();
        headerContent.setLayout(new BoxLayout(headerContent, BoxLayout.Y_AXIS));
        headerContent.setOpaque(false);
        
        JLabel titleLabel = new JLabel("Prelim Grade Calculator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Calculate Required Exam Scores");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(236, 240, 241));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        headerContent.add(titleLabel);
        headerContent.add(Box.createRigidArea(new Dimension(0, 5)));
        headerContent.add(subtitleLabel);
        
        headerPanel.add(headerContent);
        
        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(245, 246, 250));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        // Input Section
        JPanel inputSection = new JPanel();
        inputSection.setLayout(new BoxLayout(inputSection, BoxLayout.Y_AXIS));
        inputSection.setBackground(Color.WHITE);
        inputSection.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));
        
        JLabel inputTitle = new JLabel("Student Information");
        inputTitle.setFont(new Font("Arial", Font.BOLD, 16));
        inputTitle.setForeground(new Color(52, 73, 94));
        inputTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputSection.add(inputTitle);
        inputSection.add(Box.createRigidArea(new Dimension(0, 25)));
        
        // Attendance Input
        inputSection.add(createCenteredInputRow("Number of Attendances:", 
            attendanceField = createStyledTextField()));
        inputSection.add(Box.createRigidArea(new Dimension(0, 18)));
        
        // Lab Work Inputs in Grid
        JLabel labTitle = new JLabel("Lab Work Grades");
        labTitle.setFont(new Font("Arial", Font.BOLD, 14));
        labTitle.setForeground(new Color(52, 73, 94));
        labTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputSection.add(labTitle);
        inputSection.add(Box.createRigidArea(new Dimension(0, 15)));
        
        JPanel labGrid = new JPanel(new GridLayout(1, 3, 20, 0));
        labGrid.setBackground(Color.WHITE);
        labGrid.setMaximumSize(new Dimension(580, 70));
        
        labGrid.add(createLabInput("Lab 1", lab1Field = createStyledTextField()));
        labGrid.add(createLabInput("Lab 2", lab2Field = createStyledTextField()));
        labGrid.add(createLabInput("Lab 3", lab3Field = createStyledTextField()));
        
        inputSection.add(labGrid);
        inputSection.add(Box.createRigidArea(new Dimension(0, 25)));
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setMaximumSize(new Dimension(580, 45));
        
        calculateButton = createStyledButton("Calculate", new Color(41, 128, 185));
        clearButton = createStyledButton("Clear", new Color(149, 165, 166));
        
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        inputSection.add(buttonPanel);
        
        contentPanel.add(inputSection);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        // Output Section
        JPanel outputSection = new JPanel();
        outputSection.setLayout(new BorderLayout(0, 15));
        outputSection.setBackground(Color.WHITE);
        outputSection.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));
        
        JLabel outputTitle = new JLabel("Calculation Results");
        outputTitle.setFont(new Font("Arial", Font.BOLD, 16));
        outputTitle.setForeground(new Color(52, 73, 94));
        outputTitle.setHorizontalAlignment(SwingConstants.CENTER);
        outputSection.add(outputTitle, BorderLayout.NORTH);
        
        outputArea = new JTextArea(16, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputArea.setBackground(new Color(250, 251, 252));
        outputArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        outputArea.setLineWrap(false);
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 223, 230), 1));
        outputSection.add(scrollPane, BorderLayout.CENTER);
        
        contentPanel.add(outputSection);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        
        calculateButton.addActionListener(e -> calculateGrades());
        clearButton.addActionListener(e -> clearFields());
    }
    
    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 38));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 223, 230), 1),
            BorderFactory.createEmptyBorder(5, 12, 5, 12)
        ));
        return field;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 42));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    private JPanel createCenteredInputRow(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(580, 70));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setForeground(new Color(52, 73, 94));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        textField.setMaximumSize(new Dimension(350, 38));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        panel.add(textField);
        
        return panel;
    }
    
    private JPanel createLabInput(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setForeground(new Color(52, 73, 94));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        textField.setMaximumSize(new Dimension(180, 38));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        panel.add(textField);
        
        return panel;
    }
    
    private void calculateGrades() {
        try {
            double attendance = Double.parseDouble(attendanceField.getText().trim());
            double lab1 = Double.parseDouble(lab1Field.getText().trim());
            double lab2 = Double.parseDouble(lab2Field.getText().trim());
            double lab3 = Double.parseDouble(lab3Field.getText().trim());
            
            if (lab1 < 0 || lab1 > 100 || lab2 < 0 || lab2 > 100 || lab3 < 0 || lab3 > 100) {
                JOptionPane.showMessageDialog(this,
                    "Lab grades must be between 0 and 100.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (attendance < 0) {
                JOptionPane.showMessageDialog(this,
                    "Attendance cannot be negative.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            double labWorkAverage = (lab1 + lab2 + lab3) / 3.0;
            // Calculate Attendance Score (Prelim = 5 sessions total)
            double attendanceScore = Math.min((attendance / 5.0) * 100, 100);
            
            // Check if attendance meets 75% requirement (need at least 4 out of 5)
            if (attendanceScore < 75) {
                JOptionPane.showMessageDialog(this,
                    "FAILED: Attendance is below 75%.\nYou need at least 4 out of 5 attendances to pass the Prelim period.",
                    "Attendance Requirement Not Met",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double classStanding = (attendanceScore * 0.40) + (labWorkAverage * 0.60);
            double passingExam = calculateRequiredExam(75, classStanding);
            double excellentExam = calculateRequiredExam(100, classStanding);
            
            displayResults(attendance, attendanceScore, lab1, lab2, lab3,
                labWorkAverage, classStanding, passingExam, excellentExam);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Please enter valid numbers in all fields.",
                "Invalid Input",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private double calculateRequiredExam(double targetGrade, double classStanding) {
        // Prelim Grade = (Prelim Exam × 0.30) + (Class Standing × 0.70)
        // Solve for Prelim Exam: (targetGrade - classStanding * 0.70) / 0.30
        return (targetGrade - (classStanding * 0.70)) / 0.30;
    }
    
    private void displayResults(double attendance, double attendanceScore,
                               double lab1, double lab2, double lab3,
                               double labWorkAverage, double classStanding,
                               double passingExam, double excellentExam) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(centerText("═══════════════════════════════════════════════════════", 55));
        sb.append(centerText("PRELIM GRADE CALCULATION RESULTS", 55));
        sb.append(centerText("═══════════════════════════════════════════════════════", 55));
        sb.append("\n");
        
        sb.append(centerText("ATTENDANCE SUMMARY", 55));
        sb.append(centerText("─────────────────────────────────────────────────────", 55));
        sb.append(formatLine("Total Attendances", String.format("%.0f", attendance), 55));
        sb.append(formatLine("Attendance Score", String.format("%.2f%%", attendanceScore), 55));
        sb.append("\n");
        
        sb.append(centerText("LAB WORK SUMMARY", 55));
        sb.append(centerText("─────────────────────────────────────────────────────", 55));
        sb.append(formatLine("Lab Work 1", String.format("%.2f", lab1), 55));
        sb.append(formatLine("Lab Work 2", String.format("%.2f", lab2), 55));
        sb.append(formatLine("Lab Work 3", String.format("%.2f", lab3), 55));
        sb.append(formatLine("Lab Work Average", String.format("%.2f", labWorkAverage), 55));
        sb.append("\n");
        
        sb.append(centerText("CLASS STANDING", 55));
        sb.append(centerText("─────────────────────────────────────────────────────", 55));
        sb.append(formatLine("Current Class Standing", String.format("%.2f", classStanding), 55));
        sb.append(centerText("(Attendance 40% + Lab Average 60%)", 55));
        sb.append("\n");
        
        sb.append(centerText("═══════════════════════════════════════════════════════", 55));
        sb.append(centerText("REQUIRED PRELIM EXAM SCORES", 55));
        sb.append(centerText("═══════════════════════════════════════════════════════", 55));
        sb.append("\n");
        
        sb.append(centerText("TO PASS (Target Grade: 75)", 55));
        sb.append(centerText("─────────────────────────────────────────────────────", 55));
        if (passingExam > 100) {
            sb.append(formatLine("Required Exam Score", String.format("%.2f", passingExam), 55));
            sb.append(centerText("Status: Difficult to achieve (exceeds 100)", 55));
        } else if (passingExam < 0) {
            sb.append(formatLine("Required Exam Score", "0.00", 55));
            sb.append(centerText("Status: You will pass!", 55));
            sb.append(centerText("Your Class Standing ensures passing.", 55));
        } else {
            sb.append(formatLine("Required Exam Score", String.format("%.2f", passingExam), 55));
            sb.append(centerText("Status: Achievable with preparation.", 55));
        }
        sb.append("\n");
        
        sb.append(centerText("FOR EXCELLENT GRADE (Target Grade: 100)", 55));
        sb.append(centerText("─────────────────────────────────────────────────────", 55));
        if (excellentExam > 100) {
            sb.append(formatLine("Required Exam Score", String.format("%.2f", excellentExam), 55));
            sb.append(centerText("Status: Difficult to achieve (exceeds 100)", 55));
        } else if (excellentExam < 0) {
            sb.append(formatLine("Required Exam Score", "0.00", 55));
            sb.append(centerText("Status: You will achieve excellence!", 55));
            sb.append(centerText("Your Class Standing ensures it.", 55));
        } else {
            sb.append(formatLine("Required Exam Score", String.format("%.2f", excellentExam), 55));
            sb.append(centerText("Status: Achievable with excellent preparation.", 55));
        }
        sb.append("\n");
        
        sb.append(centerText("═══════════════════════════════════════════════════════", 55));
        sb.append(centerText("Prelim Grade = (Exam × 30%) + (Class Standing × 70%)", 55));
        sb.append(centerText("═══════════════════════════════════════════════════════", 55));
        
        outputArea.setText(sb.toString());
        outputArea.setCaretPosition(0);
    }
    
    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text + "\n";
    }
    
    private String formatLine(String label, String value, int width) {
        String line = label + ": " + value;
        int padding = (width - line.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + line + "\n";
    }
    
    private void clearFields() {
        attendanceField.setText("");
        lab1Field.setText("");
        lab2Field.setText("");
        lab3Field.setText("");
        outputArea.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrelimGradeCalculator calculator = new PrelimGradeCalculator();
            calculator.setVisible(true);
        });
    }
}