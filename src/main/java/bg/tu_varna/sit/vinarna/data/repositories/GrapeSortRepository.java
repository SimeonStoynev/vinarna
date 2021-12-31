package bg.tu_varna.sit.vinarna.data.repositories;

import bg.tu_varna.sit.vinarna.data.entities.GrapeSort;
import bg.tu_varna.sit.vinarna.data.entities.User;
import bg.tu_varna.sit.vinarna.data.mysql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GrapeSortRepository implements DAORepository<GrapeSort> {
    private static final Logger log = Logger.getLogger(GrapeSortRepository.class);

    public static GrapeSortRepository getInstance() {
        return GrapeSortRepository.GrapeSortRepositoryHolder.INSTANCE;
    }

    private static class GrapeSortRepositoryHolder {
        public static final GrapeSortRepository INSTANCE = new GrapeSortRepository();
    }

    @Override
    public void save(GrapeSort obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("GrapeSort saved successfully");
        } catch (Exception ex) {
            log.error("GrapeSort save error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(GrapeSort obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(obj);
            log.info("GrapeSort updated successfully");
        } catch (Exception ex) {
            log.error("GrapeSort update error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(GrapeSort obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(obj);
            log.info("GrapeSort deleted successfully");
        } catch (Exception ex) {
            log.error("GrapeSort delete error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional<GrapeSort> getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<GrapeSort> grapeSort = null;

        try {
            grapeSort = session.byId(GrapeSort.class).loadOptional(id);
            log.info("Get GrapeSort by id successfully");
        } catch (Exception ex) {
            log.error("Get GrapeSort by id error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return grapeSort;
    }

    @Override
    public List<GrapeSort> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<GrapeSort> grapeSorts = new LinkedList<>();

        try {
            String jpql = "SELECT r FROM GrapeSort r";
            grapeSorts.addAll(session.createQuery(jpql, GrapeSort.class).getResultList());
            log.info("Got all GrapeCategories");
        } catch(Exception ex){
            log.error("Get GrapeCategories failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return grapeSorts;
    }

    public GrapeSort getBySortName(String sortName) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        GrapeSort grapeSort = null;

        try {
            String jpql = "SELECT r FROM GrapeSort r WHERE name = '" + sortName + "'";
            grapeSort = (GrapeSort) session.createQuery(jpql).getSingleResult();
            log.info("Get GrapeSort by name successfully.");
        } catch(Exception ex) {
            log.error("Get GrapeSort by name error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return grapeSort;
    }
}
