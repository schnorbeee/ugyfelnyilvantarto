package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.RoleDaoImpl;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.RoleType;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class RoleService {

    @Inject
    private RoleDaoImpl roleDao;

    public void createRole(Role role) {
        roleDao.create(role);
    }

    public Role getRole(Long roleId) {
        return roleDao.read(roleId);
    }

    public Role editRole(Role role) {
        return roleDao.update(role);
    }

    public Role deleteRole(Role role) {
        return roleDao.delete(role);
    }

    public List<Role> getRolesListByUsername(String username) {
        return roleDao.getRolesListByUsername(username);
    }

    public List<User> getUsersListByRoleType(RoleType roleType, Integer limit, Integer offset) {
        return roleDao.getUsersListByRoleType(roleType, limit, offset);
    }

}
