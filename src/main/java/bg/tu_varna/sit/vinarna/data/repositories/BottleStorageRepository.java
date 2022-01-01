package bg.tu_varna.sit.vinarna.data.repositories;

import bg.tu_varna.sit.vinarna.data.entities.BottleStorage;
import bg.tu_varna.sit.vinarna.data.entities.BottleType;
import bg.tu_varna.sit.vinarna.data.mysql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BottleStorageRepository implements DAORepository<BottleStorage> {
    private static final Logger log = Logger.getLogger(BottleStorageRepository.class);

    public static BottleStorageRepository getInstance() {
        return BottleStorageRepository.BottleStorageRepositoryHolder.INSTANCE;
    }

    private static class BottleStorageRepositoryHolder {
        public static final BottleStorageRepository INSTANCE = new BottleStorageRepository();
    }

    @Override
    public void save(BottleStorage obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("BottleStorage saved successfully");
        } catch (Exception ex) {
            log.error("BottleStorage save error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(BottleStorage obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(obj);
            log.info("BottleStorage updated successfully");
        } catch (Exception ex) {
            log.error("BottleStorage update error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(BottleStorage obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(obj);
            log.info("BottleStorage deleted successfully");
        } catch (Exception ex) {
            log.error("BottleStorage delete error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional<BottleStorage> getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<BottleStorage> bottleStorage = null;

        try {
            bottleStorage = session.byId(BottleStorage.class).loadOptional(id);
            log.info("Get BottleStorage by id successfully");
        } catch (Exception ex) {
            log.error("Get BottleStorage by id error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return bottleStorage;
    }

    @Override
    public List<BottleStorage> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<BottleStorage> bottleStorages = new LinkedList<>();

        try {
            String jpql = "SELECT r FROM BottleStorage r";
            bottleStorages.addAll(session.createQuery(jpql, BottleStorage.class).getResultList());
            log.info("Got all BottleStorage");
        } catch(Exception ex){
            log.error("Get BottleStorage failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return bottleStorages;
    }

    public List<BottleStorage> getLatestAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<BottleStorage> bottleStorages = new LinkedList<>();

        try {
            String jpql = "SELECT r FROM BottleStorage r GROUP BY r.bottle_type_id ORDER BY r.bottle_type_id.id DESC";
            bottleStorages.addAll(session.createQuery(jpql, BottleStorage.class).getResultList());
            log.info("Got all BottleStorage");
        } catch(Exception ex){
            log.error("Get BottleStorage failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return bottleStorages;
    }

    public List<BottleStorage> getAllByBottle(BottleType bottle) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<BottleStorage> bottleStorages = new LinkedList<>();

        try {
            String jpql = "SELECT r FROM BottleStorage r WHERE r.bottle_type_id = '" + bottle.getId() +"'";
            bottleStorages.addAll(session.createQuery(jpql, BottleStorage.class).getResultList());
            log.info("Got all BottleStorage");
        } catch(Exception ex){
            log.error("Get BottleStorage failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return bottleStorages;
    }

    public BottleStorage getLastByBottle(BottleType bottle) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        BottleStorage bottleStorage = null;

        try{
            String jpql = "SELECT s FROM BottleStorage s WHERE s.bottle_type_id = '" + bottle.getId() + "' ORDER BY id DESC";
            bottleStorage = (BottleStorage) session.createQuery(jpql).setMaxResults(1).getSingleResult();
            log.info("Get Bottle storage by bottle successfully.");
        } catch(Exception ex) {
            log.error("Get Bottle storage by bottle error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return bottleStorage;
    }
}
