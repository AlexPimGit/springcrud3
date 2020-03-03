package crud.dao;

import crud.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class RoleDaoImpl implements RoleDao {
    private Logger LOGGER = Logger.getLogger(RoleDaoImpl.class.getName());
    private SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(role);
        LOGGER.log(Level.INFO, "Role successfully saved. Role details: " + role);
    }

    @Override
    public void updateRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.update(role);
        LOGGER.log(Level.INFO, "Role successfully updated. Role details: " + role);
    }

    @Override
    public void removeRole(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Role role = session.load(Role.class, id);

        if (role != null) {
            session.delete(role);
        }
        LOGGER.log(Level.INFO, "Role successfully deleted. Role details: " + role);
    }

    @Override
    public List<Role> listRole() {
        Session session = sessionFactory.getCurrentSession();
        List<Role> roleList = session.createQuery("FROM Role").list();
        for (Role role : roleList) {
            LOGGER.log(Level.INFO, "Role list: " + role);
        }
        return roleList;
    }
}

