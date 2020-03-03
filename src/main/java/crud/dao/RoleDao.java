package crud.dao;

import crud.model.Role;

import java.util.List;

public interface RoleDao {
    void addRole(Role role);

    void updateRole(Role role);

    void removeRole(Long id);

    List<Role> listRole();
}
