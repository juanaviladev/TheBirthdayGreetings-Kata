package com.greetings.legacy;

import com.greetings.legacy.model.Employee;
import com.greetings.legacy.model.EmployeeService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainController implements ActionListener {

    private MainWindow window;
    private EmployeeService service;

    public MainController(MainWindow window) {
        this.window = window;
        this.service = new EmployeeService();
        refresh();
    }

    private void refresh() {
        try {
            List<Employee> employees = service.findAll();
            window.setEmployees(employees);
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void showErrorMessage(String e) {
        JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("change_db")) {
                JFileChooser fileChooser = new JFileChooser(Config.employeesDB);
                int option = fileChooser.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    Config.employeesDB = fileChooser.getSelectedFile();
                }
                List<Employee> employees = service.findAll();
                window.setEmployees(employees);
            } else if (e.getActionCommand().equals("send")) {
                int sentEmails = service.sendGreetings();
                JOptionPane.showMessageDialog(null,sentEmails + "greetings emails have been sent","Info",JOptionPane.INFORMATION_MESSAGE);
            }
            else if(e.getActionCommand().equals("refresh")) {
                refresh();
            }
            else {
                showErrorMessage("Unknown error");
            }
        }
        catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }
}
