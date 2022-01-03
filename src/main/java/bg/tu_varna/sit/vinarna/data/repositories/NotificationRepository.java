package bg.tu_varna.sit.vinarna.data.repositories;

import bg.tu_varna.sit.vinarna.data.entities.GrapeStorage;
import bg.tu_varna.sit.vinarna.data.entities.Notification;
import bg.tu_varna.sit.vinarna.data.mysql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class NotificationRepository implements DAORepository<Notification> {
    private static final Logger log = Logger.getLogger(GrapeStorageRepository.class);

    public static NotificationRepository getInstance() {
        return NotificationRepository.NotificationRepositoryHolder.INSTANCE;
    }

    private static class NotificationRepositoryHolder {
        public static final NotificationRepository INSTANCE = new NotificationRepository();
    }

    @Override
    public void save(Notification obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Notification saved successfully");
        } catch (Exception ex) {
            log.error("Notification save error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Notification obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(obj);
            log.info("Notification updated successfully");
        } catch (Exception ex) {
            log.error("Notification update error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(Notification obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(obj);
            log.info("Notification deleted successfully");
        } catch (Exception ex) {
            log.error("Notification delete error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional<Notification> getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Notification> notification = null;

        try {
            notification = session.byId(Notification.class).loadOptional(id);
            log.info("Get Notification by id successfully");
        } catch (Exception ex) {
            log.error("Get Notification by id error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return notification;
    }

    @Override
    public List<Notification> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Notification> notifications = new LinkedList<>();

        try {
            String jpql = "SELECT n FROM Notification n";
            notifications.addAll(session.createQuery(jpql, Notification.class).getResultList());
            log.info("Got all Notifications");
        } catch(Exception ex){
            log.error("Get all Notifications failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }

        return notifications;
    }
}
