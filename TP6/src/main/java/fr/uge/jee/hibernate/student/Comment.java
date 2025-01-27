//package fr.uge.jee.hibernate.student;
//
//import jakarta.persistence.*;
//
//@Entity
//public class Comment {
//    @Id
//    @GeneratedValue
//    private long id;
//
//    @Column(name="comment")
//    private String comment;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Student owner;
//
//    public Comment(String comment) {
//        this.comment=comment;
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
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//}
