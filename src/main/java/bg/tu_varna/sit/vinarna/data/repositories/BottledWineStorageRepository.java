package bg.tu_varna.sit.vinarna.data.repositories;

import bg.tu_varna.sit.vinarna.data.entities.BottleStorage;
import bg.tu_varna.sit.vinarna.data.entities.BottleType;
import bg.tu_varna.sit.vinarna.data.entities.BottledWineStorage;
import bg.tu_varna.sit.vinarna.data.entities.WineType;
import bg.tu_varna.sit.vinarna.data.mysql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BottledWineStorageRepository implements DAORepository<BottledWineStorage> {
    private static final Logger log = Logger.getLogger(GrapeStorageRepository.class);

    public static BottledWineStorageRepository getInstance() {
        return BottledWineStorageRepository.BottledWineStorageRepositoryHolder.INSTANCE;
    }

    private static class BottledWineStorageRepositoryHolder {
        public static final BottledWineStorageRepository INSTANCE = new BottledWineStorageRepository();
    }

    @Override
    public void save(BottledWineStorage obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("BottledWineStorage saved successfully");
        } catch (Exception ex) {
            log.error("BottledWineStorage save error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(BottledWineStorage obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(obj);
            log.info("BottledWineStorage updated successfully");
        } catch (Exception ex) {
            log.error("BottledWineStorage update error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(BottledWineStorage obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(obj);
            log.info("BottledWineStorage deleted successfully");
        } catch (Exception ex) {
            log.error("BottledWineStorage delete error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional<BottledWineStorage> getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<BottledWineStorage> bottledWineStorage = null;

        try {
            bottledWineStorage = session.byId(BottledWineStorage.class).loadOptional(id);
            log.info("Get BottledWineStorage by id successfully");
        } catch (Exception ex) {
            log.error("Get BottledWineStorage by id error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return bottledWineStorage;
    }

    @Override
    public List<BottledWineStorage> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<BottledWineStorage> bottledWineStorages = new LinkedList<>();

        try {
            String jpql = "SELECT r FROM BottledWineStorage r";
            bottledWineStorages.addAll(session.createQuery(jpql, BottledWineStorage.class).getResultList());
            log.info("Got all BottledWineStorage");
        } catch(Exception ex){
            log.error("Get all BottledWineStorage failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return bottledWineStorages;
    }

    public List<BottledWineStorage> getLatestAllByWine(WineType wine) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<BottledWineStorage> bottleStorages = new LinkedList<>();

        try {
            String jpql = "SELECT b FROM BottledWineStorage b WHERE b.wine_type_id.id = '" + wine.getId() + "' GROUP BY b.bottle_type_id.id ORDER BY b.bottle_type_id.id DESC";
            bottleStorages.addAll(session.createQuery(jpql, BottledWineStorage.class).getResultList());
            log.info("Got all BottleStorage");
        } catch(Exception ex){
            log.error("Get BottleStorage failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return bottleStorages;
    }

    public BottledWineStorage getLastByBottleTypeAndWineType(BottleType bottle, WineType wine) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        BottledWineStorage bottledWineStorage = null;

        try{
            String jpql = "SELECT b FROM BottledWineStorage b WHERE b.bottle_type_id.id = '" + bottle.getId() + "' AND b.wine_type_id.id = '" + wine.getId() + "' ORDER BY b.id DESC";
            bottledWineStorage = (BottledWineStorage) session.createQuery(jpql).setMaxResults(1).getSingleResult();
            log.info("Get BottledWineStorage by bottle and wine successfully.");
        } catch(Exception ex) {
            log.error("Get BottledWineStorage by bottle and wine error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return bottledWineStorage;
    }
}
