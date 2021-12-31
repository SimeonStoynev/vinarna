package bg.tu_varna.sit.vinarna.data.repositories;

import bg.tu_varna.sit.vinarna.data.entities.GrapeCategory;
import bg.tu_varna.sit.vinarna.data.mysql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GrapeCategoryRepository implements DAORepository<GrapeCategory> {
    private static final Logger log = Logger.getLogger(GrapeCategoryRepository.class);

    public static GrapeCategoryRepository getInstance() {
        return GrapeCategoryRepository.GrapeCategoryRepositoryHolder.INSTANCE;
    }

    private static class GrapeCategoryRepositoryHolder {
        public static final GrapeCategoryRepository INSTANCE = new GrapeCategoryRepository();
    }

    @Override
    public void save(GrapeCategory obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("GrapeCategory saved successfully");
        } catch (Exception ex) {
            log.error("GrapeCategory save error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(GrapeCategory obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(obj);
            log.info("GrapeCategory updated successfully");
        } catch (Exception ex) {
            log.error("GrapeCategory update error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(GrapeCategory obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(obj);
            log.info("GrapeCategory deleted successfully");
        } catch (Exception ex) {
            log.error("GrapeCategory delete error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional<GrapeCategory> getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<GrapeCategory> grapeCategory = null;

        try {
            grapeCategory = session.byId(GrapeCategory.class).loadOptional(id);
            log.info("Get GrapeCategory by id successfully");
        } catch (Exception ex) {
            log.error("Get GrapeCategory by id error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return grapeCategory;
    }

    @Override
    public List<GrapeCategory> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<GrapeCategory> grapeCategories = new LinkedList<>();

        try {
            String jpql = "SELECT r FROM GrapeCategory r";
            grapeCategories.addAll(session.createQuery(jpql, GrapeCategory.class).getResultList());
            log.info("Got all GrapeCategories");
        } catch(Exception ex){
            log.error("Get GrapeCategories failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return grapeCategories;
    }
}
