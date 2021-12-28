package bg.tu_varna.sit.vinarna.data.repositories;

import bg.tu_varna.sit.vinarna.data.entities.Role;
import bg.tu_varna.sit.vinarna.data.mysql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RoleRepository implements DAORepository<Role>{

    private static final Logger log = Logger.getLogger(RoleRepository.class);

    public static RoleRepository getInstance() {
        return RoleRepository.RoleRepositoryHolder.INSTANCE;
    }

    private static class RoleRepositoryHolder {
        public static final RoleRepository INSTANCE = new RoleRepository();
    }

    @Override
    public void save(Role obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Role saved successfully");
        } catch (Exception ex) {
            log.error("Role save error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Role obj) {
        Session session=Connection.openSession();
        Transaction transaction =session.beginTransaction();
        try {
            session.save(obj);
            log.info("Role updated successfully");
        } catch (Exception ex) {
            log.error("Role update error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(Role obj) {
        Session session=Connection.openSession();
        Transaction transaction =session.beginTransaction();
        try {
            session.save(obj);
            log.info("Role deleted successfully");
        } catch (Exception ex) {
            log.error("Role delete error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional<Role> getById(int id) {
        Session session=Connection.openSession();
        Transaction transaction=session.beginTransaction();
        Optional<Role> role = null;
        try {
            role = session.byId(Role.class).loadOptional(id);
            log.info("Get Role by id successfully");
        } catch (Exception ex) {
            log.error("Get Role by id error: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return role;
    }

    @Override
    public List<Role> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Role> roles = new LinkedList<>();
        try{
            String jpql = "SELECT r FROM Role r";
            roles.addAll(session.createQuery(jpql, Role.class).getResultList());
            log.info("Got all roles");
        }catch(Exception ex){
            log.error("Get roles failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return roles;
    }
}
