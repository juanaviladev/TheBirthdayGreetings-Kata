package com.greetings.legacy.model;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String phoneNumber;

    public Employee(int id, String firstName, String lastName, String birthDate, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static Employee from(String[] splittedData) {
        return new Employee(
                Integer.parseInt(splittedData[0]),
                splittedData[1].trim(),
                splittedData[2].trim(),
                splittedData[3].trim(),
                splittedData[4].trim(),
                splittedData[5].trim()
        );
    }

    public String toString() {
        return id + ", " + firstName + ", " + lastName + ", " + birthDate + ", " + email + ", " + phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
