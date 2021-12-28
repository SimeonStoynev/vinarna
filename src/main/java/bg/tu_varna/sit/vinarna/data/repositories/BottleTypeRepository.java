package bg.tu_varna.sit.vinarna.data.repositories;

import bg.tu_varna.sit.vinarna.data.entities.BottleType;
import bg.tu_varna.sit.vinarna.data.mysql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BottleTypeRepository implements DAORepository<BottleType> {
    private static final Logger log = Logger.getLogger(BottleTypeRepository.class);

    public static BottleTypeRepository getInstance() {
        return BottleTypeRepository.BottleTypeRepositoryHolder.INSTANCE;
    }

    private static class BottleTypeRepositoryHolder {
        public static final BottleTypeRepository INSTANCE = new BottleTypeRepository();
    }

    @Override
    public void save(BottleType obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("BottleType saved successfully");
        } catch (Exception ex) {
            log.error("BottleType save error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(BottleType obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(obj);
            log.info("BottleType updated successfully");
        } catch (Exception ex) {
            log.error("BottleType update error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(BottleType obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(obj);
            log.info("BottleType deleted successfully");
        } catch (Exception ex) {
            log.error("BottleType delete error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional<BottleType> getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<BottleType> bottleType = null;

        try {
            bottleType = session.byId(BottleType.class).loadOptional(id);
            log.info("Get BottleType by id successfully");
        } catch (Exception ex) {
            log.error("Get BottleType by id error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return bottleType;
    }

    @Override
    public List<BottleType> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<BottleType> bottleTypes = new LinkedList<>();

        try {
            String jpql = "SELECT r FROM BottleType r";
            bottleTypes.addAll(session.createQuery(jpql, BottleType.class).getResultList());
            log.info("Got all BottleTypes");
        } catch(Exception ex){
            log.error("Get BottleTypes failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return bottleTypes;
    }
}
