package bg.tu_varna.sit.vinarna.data.repositories;

import bg.tu_varna.sit.vinarna.data.entities.WineRecipe;
import bg.tu_varna.sit.vinarna.data.mysql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class WineRecipeRepository implements DAORepository<WineRecipe> {
    private static final Logger log = Logger.getLogger(WineRecipeRepository.class);

    public static WineRecipeRepository getInstance() {
        return WineRecipeRepository.WineRecipeRepositoryHolder.INSTANCE;
    }

    private static class WineRecipeRepositoryHolder {
        public static final WineRecipeRepository INSTANCE = new WineRecipeRepository();
    }

    @Override
    public void save(WineRecipe obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("WineRecipe saved successfully");
        } catch (Exception ex) {
            log.error("WineRecipe save error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(WineRecipe obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("WineRecipe updated successfully");
        } catch(Exception ex) {
            log.error("WineRecipe update error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(WineRecipe obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("WineRecipe deleted successfully");
        } catch(Exception ex) {
            log.error("WineRecipe delete error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional<WineRecipe> getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<WineRecipe> wineRecipe = null;

        try {
            wineRecipe = session.byId(WineRecipe.class).loadOptional(id);
            log.info("Get WineRecipe by id successfully");
        } catch(Exception ex) {
            log.error("Get WineRecipe by id error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return wineRecipe;
    }

    @Override
    public List<WineRecipe> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<WineRecipe> wineRecipes = new LinkedList<>();

        try {
            String jpql = "SELECT r FROM WineRecipe r";
            wineRecipes.addAll(session.createQuery(jpql, WineRecipe.class).getResultList());
            log.info("Got all GrapeCategories");
        } catch(Exception ex){
            log.error("Get GrapeCategories failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return wineRecipes;
    }
}
