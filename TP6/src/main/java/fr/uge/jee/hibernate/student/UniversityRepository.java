//package fr.uge.jee.hibernate.student;
//
//import fr.uge.jee.hibernate.PersistenceUtils;
//
//public class UniversityRepository {
//    public long create(String address){
//        return PersistenceUtils.inTransaction(em->{
//            var add = new Address(address);
//            em.persist(add);
//            return add.getId();
//        });
//    }
//
//    public boolean delete(long id) {
//        PersistenceUtils.inTransaction(em->{
//            var address = em.find(Address.class,id);
//            if(address==null){
//                throw new IllegalArgumentException();
//            }
//            em.remove(address);
//            return true;
//        });
//        return false;
//    }
//
//    public boolean update(long id, String address) {
//        PersistenceUtils.inTransaction(em->{
//            var add = em.find(Address.class,id);
//            if(add!=null){
//                System.out.println(add);
//                add.setAddress(address);
//            }
//            return true;
//        });
//        return false;
//    }
//}
