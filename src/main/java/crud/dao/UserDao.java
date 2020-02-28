package crud.dao;

import crud.model.User;

import java.util.List;


public interface UserDao {
    void addUser(User user);

    void updateUser(User user);

    void removeUser(Long id);

    User getUserById(Long id);

    List<User> listUser();

    User findByUsername(String name);

    User findByUserEmail(String email);
}
