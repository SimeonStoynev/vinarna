package bg.tu_varna.sit.vinarna.data.repositories;

import bg.tu_varna.sit.vinarna.data.entities.GrapeStorage;
import bg.tu_varna.sit.vinarna.data.mysql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GrapeStorageRepository implements DAORepository<GrapeStorage> {
    private static final Logger log = Logger.getLogger(GrapeStorageRepository.class);

    public static GrapeStorageRepository getInstance() {
        return GrapeStorageRepository.GrapeStorageRepositoryHolder.INSTANCE;
    }

    private static class GrapeStorageRepositoryHolder {
        public static final GrapeStorageRepository INSTANCE = new GrapeStorageRepository();
    }

    @Override
    public void save(GrapeStorage obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("GrapeStorage saved successfully");
        } catch (Exception ex) {
            log.error("GrapeStorage save error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(GrapeStorage obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(obj);
            log.info("GrapeStorage updated successfully");
        } catch (Exception ex) {
            log.error("GrapeStorage update error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(GrapeStorage obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(obj);
            log.info("GrapeStorage deleted successfully");
        } catch (Exception ex) {
            log.error("GrapeStorage delete error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional<GrapeStorage> getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<GrapeStorage> grapeStorage = null;

        try {
            grapeStorage = session.byId(GrapeStorage.class).loadOptional(id);
            log.info("Get GrapeStorage by id successfully");
        } catch (Exception ex) {
            log.error("Get GrapeStorage by id error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return grapeStorage;
    }

    @Override
    public List<GrapeStorage> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<GrapeStorage> grapeStorages = new LinkedList<>();

        try {
            String jpql = "SELECT r FROM GrapeStorage r";
            grapeStorages.addAll(session.createQuery(jpql, GrapeStorage.class).getResultList());
            log.info("Got all GrapeStorage");
        } catch(Exception ex){
            log.error("Get GrapeStorage failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return grapeStorages;
    }

    public GrapeStorage getLastByGrapeSortId(int grapeSortId) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        GrapeStorage grapeStorage = null;

        try{
            String jpql = "SELECT r FROM GrapeStorage r WHERE sort = '" + grapeSortId + "' ORDER BY id DESC";
            grapeStorage = (GrapeStorage) session.createQuery(jpql).setMaxResults(1).getSingleResult();
            log.info("Get User by email successfully.");
        } catch(Exception ex) {
            log.error("Get user by email error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return grapeStorage;
    }
}
