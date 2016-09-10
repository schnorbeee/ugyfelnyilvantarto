package com.codingmentorteam3.controllers;

import com.codingmentorteam3.daos.RoleDaoImpl;
import com.codingmentorteam3.daos.UserDaoImpl;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.RoleType;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.LastAdminException;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;


/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@ManagedBean(name = "roleController")
public class RoleController {

    @Inject
    private UserDaoImpl userDao;
    
    @Inject
    private RoleDaoImpl roleDao;
    
    public Role setUserAdminRoleToRoleTable(String username) {
        User current = userDao.getUserByUsername(username);
        RoleType adminRole = RoleType.ADMIN;
        if (null != current) {
            for (Role role : userDao.getRolesListByUserId(current.getId())) {
                if (role.getRoleType().equals(adminRole)) {
                    return role;
                }
            }
            Role newRole = new Role(adminRole, current.getUsername());
            return roleDao.create(newRole);
        }
        throw new BadRequestException("This user not exist in the database.");
    }

    public Role deleteAdminRoleForThisUser(String username) {
        User current = userDao.getUserByUsername(username);
        RoleType adminRole = RoleType.ADMIN;
        if (null != current) {
            for (Role role : roleDao.getRolesListByRoleType(adminRole)) {
                if (roleDao.getRolesListByRoleType(adminRole).size() == 1) {
                    throw new LastAdminException("This admin is last admin in database. So you don't know delete his role.");
                }
                if (role.getUsername().equals(username)) {
                    return roleDao.delete(role);
                }
            }
            throw new BadRequestException("This user haven't admin privilege.");
        }
        throw new BadRequestException("This user for this username don't exist in database.");
    }

}
