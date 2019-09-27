package com.greetings.legacy;

import com.greetings.legacy.model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainWindow extends JFrame {

    private JButton sendGreetingsButton;
    private JTable employeesTable;
    private JButton selectDatabaseFileButton;
    private JPanel mainPanel;
    private JButton refreshButton;
    private MainController controller;
    private DefaultTableModel tableModel;

    public MainWindow() {
        setTitle("Greetings Kata v1.0");
        setContentPane(mainPanel);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("First name");
        tableModel.addColumn("Last name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone number");
        tableModel.addColumn("Birth date");
        tableModel.addColumn("Will receive an email?");

        employeesTable.setModel(tableModel);

        controller = new MainController(this);

        sendGreetingsButton.addActionListener(controller);
        selectDatabaseFileButton.addActionListener(controller);
        refreshButton.addActionListener(controller);
    }

    public void setEmployees(List<Employee> selectedEmployees) throws ParseException {
        tableModel.setRowCount(0);
        for (Employee e : selectedEmployees) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date employeeDate = dateFormat.parse(e.getBirthDate());

            Calendar cal = Calendar.getInstance();

            int actualMonth = cal.get(Calendar.MONTH);
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

            cal.setTime(employeeDate);

            int birthMonth = cal.get(Calendar.MONTH);
            int birthDay = cal.get(Calendar.DAY_OF_MONTH);

            boolean email = actualMonth == birthMonth && birthDay == dayOfMonth;
            tableModel.addRow(new Object[]{e.getId(), e.getFirstName(), e.getLastName(), e.getEmail(), e.getPhoneNumber(), e.getBirthDate(), email ? "Yes" : "No"});
        }
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.displayNow();
    }

    private void displayNow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
