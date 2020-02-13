package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    void addUser(User user);

    void updateUser(User user);

    void removeUser(int id);

    User getUserById(int id);

    List<User> listUser();

}
