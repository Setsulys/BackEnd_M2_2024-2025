//package fr.uge.jee.hibernate.student;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Table(name="Student")
//public class Student {
//
//
//    @GeneratedValue
//    @Id
//    @Column
//    private int id;
//
//
//    @Column(name="Address")
//    private Address address;
//
//    @Column(name="University")
//    private University university;
//
//    @Column(name="Comments")
//    @OneToMany
//    private List<Comment> comments;
//
//
//    @Column(name="Lectures")
//    @OneToMany
//    private Set<Lecture> lectures;
//
//
//    @Column(name="FirstName")
//    private String firstname;
//    @Column(name="LastName")
//    private String lastName;
//
//    Student() {
//    }
//
//    Student(String firstName,String lastName,University university,Address address){
//        this.address=address;
//        this.university=university;
//        this.firstname=firstName;
//        this.lastName=lastName;
//    }
//
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    public void setUniversity(University university) {
//        this.university = university;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }
//
//    public void setLectures(Set<Lecture> lectures) {
//        this.lectures = lectures;
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public University getUniversity() {
//        return university;
//    }
//
//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public Set<Lecture> getLectures() {
//        return lectures;
//    }
//
//    public Address getAddress() {
//        return address;
//    }
//}
//
