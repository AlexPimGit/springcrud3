package crud.dao;


import crud.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class UserDaoImpl implements UserDao {
    private Logger LOGGER = Logger.getLogger(UserDaoImpl.class.getName());

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //как в этих методах быть с исключениями??
    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        LOGGER.log(Level.INFO, "User successfully saved. User details: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        LOGGER.log(Level.INFO, "User successfully updated. User details: " + user);
    }

    @Override
    public void removeUser(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);

        if (user != null) {
            session.delete(user);
        }
        LOGGER.log(Level.INFO, "User successfully deleted. User details: " + user);
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        LOGGER.log(Level.INFO, "User successfully loaded. User details: " + user);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUser() {
        Session session = sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("FROM User").list();
        for (User user : userList) {
            LOGGER.log(Level.INFO, "User list: " + user);
        }
        return userList;
    }
}
