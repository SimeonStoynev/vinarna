package bg.tu_varna.sit.vinarna.data.repositories;

import bg.tu_varna.sit.vinarna.data.entities.User;
import bg.tu_varna.sit.vinarna.data.mysql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements DAORepository<User> {

    private static final Logger log = Logger.getLogger(UserRepository.class);

    public static UserRepository getInstance() { return UserRepository.UserRepositoryHolder.INSTANCE;}

    private static class UserRepositoryHolder {
        public static final UserRepository INSTANCE = new UserRepository();
    }

    @Override
    public void save(User obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("User saved successfully.");
        } catch (Exception ex) {
            log.error("User save error: " + ex);
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(User obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(obj);
            log.info("User updated successfully.");
        } catch(Exception ex) {
            log.error("User update error: " + ex);
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(User obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(obj);
            log.info("User deleted successfully.");
        } catch(Exception ex) {
            log.error("User delete error: " + ex);
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional<User> getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<User> user = null;

        try {
            user = session.byId(User.class).loadOptional(id);
            log.info("Get User by id successfully.");
        } catch(Exception ex) {
            log.error("Get all users error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = new LinkedList<>();

        try{
            String jpql = "SELECT u FROM User u";
            users.addAll(session.createQuery(jpql, User.class).getResultList());
            log.info("Get all users.");
        } catch (Exception ex) {
            log.error("Get all users error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return users;
    }

    public User getByUsernameAndPassword(String username, String password) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        User user = null;

        try{
            String jpql = "SELECT u FROM User u WHERE username = '" + username + "' AND password = '" + password + "'";
            user = (User) session.createQuery(jpql).getSingleResult();
            log.info("Get User by username & password successfully.");
        } catch (Exception ex) {
            log.error("Get user by username & password error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return user;
    }

    public User getByUsername(String username) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        User user = null;

        try {
            String jpql = "SELECT u FROM User u WHERE username = '" + username + "'";
            user = (User) session.createQuery(jpql).getSingleResult();
            log.info("Get User by username successfully.");
        } catch(Exception ex) {
            log.error("Get user by username error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return user;
    }

    public User getByEmail(String email) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        User user = null;

        try{
            String jpql = "SELECT u FROM User u WHERE email = '" + email + "'";
            user = (User) session.createQuery(jpql).getSingleResult();
            log.info("Get User by email successfully.");
        } catch(Exception ex) {
            log.error("Get user by email error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return user;
    }
}
