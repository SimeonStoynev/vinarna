package bg.tu_varna.sit.vinarna.data.repositories;

import bg.tu_varna.sit.vinarna.data.entities.Bottle;
import bg.tu_varna.sit.vinarna.data.mysql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BottleRepository implements DAORepository<Bottle> {
    private static final Logger log = Logger.getLogger(BottleRepository.class);

    public static BottleRepository getInstance() {
        return BottleRepository.BottleRepositoryHolder.INSTANCE;
    }

    private static class BottleRepositoryHolder {
        public static final BottleRepository INSTANCE = new BottleRepository();
    }

    @Override
    public void save(Bottle obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(obj);
            log.info("Bottle saved successfully");
        } catch (Exception ex) {
            log.error("Bottle save error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Bottle obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(obj);
            log.info("Bottle updated successfully");
        } catch (Exception ex) {
            log.error("Bottle update error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(Bottle obj) {
        Session session=Connection.openSession();
        Transaction transaction =session.beginTransaction();

        try {
            session.delete(obj);
            log.info("Bottle deleted successfully");
        } catch (Exception ex) {
            log.error("Bottle delete error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional<Bottle> getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Bottle> bottle = null;

        try {
            bottle = session.byId(Bottle.class).loadOptional(id);
            log.info("Get Bottle by id successfully");
        } catch(Exception ex) {
            log.error("Get Bottle by id error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return bottle;
    }

    @Override
    public List<Bottle> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Bottle> bottles = new LinkedList<>();

        try {
            String jpql = "SELECT r FROM Bottle r";
            bottles.addAll(session.createQuery(jpql, Bottle.class).getResultList());
            log.info("Got all bottles");
        } catch(Exception ex) {
            log.error("Get bottles failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return bottles;
    }
}
