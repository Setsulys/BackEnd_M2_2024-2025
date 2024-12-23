package fr.uge.jee.hibernate;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="Employee")
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    @Column(name="fistname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @Column(name="salary")
    private int salary;

    Employee(){}
    Employee(String firstName,String lastName,int salary){
        this.firstName=firstName;
        this.lastName=lastName;
        this.salary=salary;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "["+id+"] "+firstName+" "+ lastName+": "+ salary;
    }
}