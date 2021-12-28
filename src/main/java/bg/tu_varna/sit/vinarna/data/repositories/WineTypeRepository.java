package bg.tu_varna.sit.vinarna.data.repositories;

import bg.tu_varna.sit.vinarna.data.entities.WineType;
import bg.tu_varna.sit.vinarna.data.mysql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class WineTypeRepository implements DAORepository<WineType> {
    private static final Logger log = Logger.getLogger(WineTypeRepository.class);

    public static WineTypeRepository getInstance() {
        return WineTypeRepository.WineTypeRepositoryHolder.INSTANCE;
    }

    private static class WineTypeRepositoryHolder {
        public static final WineTypeRepository INSTANCE = new WineTypeRepository();
    }

    @Override
    public void save(WineType obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("WineType saved successfully");
        } catch (Exception ex) {
            log.error("WineType save error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(WineType obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("WineType updated successfully");
        } catch (Exception ex) {
            log.error("WineType update error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(WineType obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("WineType deleted successfully");
        } catch (Exception ex) {
            log.error("WineType delete error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional<WineType> getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<WineType> wineType = null;

        try {
            wineType = session.byId(WineType.class).loadOptional(id);
            log.info("Get WineType by id successfully");
        } catch (Exception ex) {
            log.error("Get WineType by id error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return wineType;
    }

    @Override
    public List<WineType> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<WineType> wineTypes = new LinkedList<>();

        try {
            String jpql = "SELECT r FROM WineType r";
            wineTypes.addAll(session.createQuery(jpql, WineType.class).getResultList());
            log.info("Got all GrapeCategories");
        } catch(Exception ex){
            log.error("Get GrapeCategories failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return wineTypes;
    }
}
