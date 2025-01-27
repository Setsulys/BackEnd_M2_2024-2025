//package fr.uge.jee.hibernate.student;
//
//import fr.uge.jee.hibernate.PersistenceUtils;
//
//public class LectureRepository {
//    public long create(String address){
//        return PersistenceUtils.inTransaction(em->{
//            var lec = new Lecture(address);
//            em.persist(lec);
//            return lec.getId();
//        });
//    }
//
//    public boolean delete(long id) {
//        PersistenceUtils.inTransaction(em->{
//            var lec = em.find(Address.class,id);
//            if(lec==null){
//                throw new IllegalArgumentException();
//            }
//            em.remove(lec);
//            return true;
//        });
//        return false;
//    }
//
//    public boolean update(long id, String lecture) {
//        PersistenceUtils.inTransaction(em->{
//            var lec = em.find(Address.class,id);
//            if(lec!=null){
//                System.out.println(lec);
//                lec.setAddress(lecture);
//            }
//            return true;
//        });
//        return false;
//    }
//}
