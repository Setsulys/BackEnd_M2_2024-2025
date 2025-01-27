//package fr.uge.jee.hibernate.student;
//
//import jakarta.persistence.*;
//
//@Entity
//public class Address {
//    @Id
//    @GeneratedValue
//    @Column(name="AddressID")
//    private long id;
//
//    @Column(name="Address")
//    private String address;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Student owner;
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public Address(String address) {
//    }
//}
