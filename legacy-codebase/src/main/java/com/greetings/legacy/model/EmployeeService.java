package com.greetings.legacy.model;

import com.greetings.legacy.Config;
import com.greetings.legacy.utils.Log;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EmployeeService {

    private Mailer mailer;

    public EmployeeService() {
        mailer = MailerBuilder
                .withSMTPServer(Config.SMTP_HOST, Config.SMTP_PORT, Config.MAIL_USER, Config.MAIL_PASS)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer();
    }

    public void sendMail(String subject, String body, String to) {
        Log.d("Sending an email to "+to);
        
        Email email = EmailBuilder.startingBlank()
                .from("Greetings Kata","greetings@kata.com")
                .to(to)
                .withSubject(subject)
                .withPlainText(body)
                .buildEmail();

        mailer.sendMail(email);
    }

    public List<Employee> findAll() throws IOException {
        File dbFile = Config.employeesDB;
        List<Employee> employees = new ArrayList<Employee>();
        if(dbFile.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(dbFile));
            String line;
            String[] splittedData;
            while ((line = br.readLine()) != null) {
                splittedData = line.split(",");
                employees.add(Employee.from(splittedData));
            }
            br.close();
        }
        else {
            Log.d("File does not exists: "+dbFile.getAbsolutePath());
        }
        return employees;
    }

    public int sendGreetings() throws IOException, ParseException {
        List<Employee> employees = findAll();

        int sentEmails = 0;
        for (Employee employee : employees) {

            Calendar cal = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date employeeDate = dateFormat.parse(employee.getBirthDate());

            int actualMonth = cal.get(Calendar.MONTH);
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

            cal.setTime(employeeDate);

            int birthMonth = cal.get(Calendar.MONTH);
            int birthDay = cal.get(Calendar.DAY_OF_MONTH);

            boolean email = actualMonth == birthMonth && birthDay == dayOfMonth;

            if (email) {
                sendMail("Happy birthday!", "Happy birthday, dear " + employee.getFirstName() + "!", employee.getEmail());
                sentEmails++;
            }

        }
        return sentEmails;
    }

}
