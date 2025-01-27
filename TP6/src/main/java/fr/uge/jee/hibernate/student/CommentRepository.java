//package fr.uge.jee.hibernate.student;
//
//import fr.uge.jee.hibernate.PersistenceUtils;
//
//public class CommentRepository {
//
//    public long create(String comments){
//        return PersistenceUtils.inTransaction(em->{
//            var add = new Comment(comments);
//            em.persist(add);
//            return add.getId();
//        });
//    }
//
//    public boolean delete(long id) {
//        PersistenceUtils.inTransaction(em->{
//            var com = em.find(Comment.class,id);
//            if(com==null){
//                throw new IllegalArgumentException();
//            }
//            em.remove(com);
//            return true;
//        });
//        return false;
//    }
//
//    public boolean update(long id, String comment) {
//        PersistenceUtils.inTransaction(em->{
//            var com = em.find(Comment.class,id);
//            if(com!=null){
//                System.out.println(com);
//                com.setComment(comment);
//            }
//            return true;
//        });
//        return false;
//    }
//}
