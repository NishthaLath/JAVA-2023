// Assignment 2: Employee management system
// 2022427833_니스타

import java.text.NumberFormat;
import java.util.Locale;

public class Employee {
    // Properties
    private String firstname;
    private String lastname;
    private String position;
    private double salary;
    private int age;

    // Getters
    public int getAge() {
        return age;
    }

    public String getName() {
        return firstname + " " + lastname;
    }

    public double getSalary() {
            return salary;
    }
    
    public String getPosition() {
        return position;
    }

    // Setters
    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Methods
    public void printEmployee() {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        System.out.println(String.format("%-20s %-5d %-20s %-10s", this.getName(), this.getAge(), this.getPosition(), formatter.format(this.getSalary())));
    }
}
