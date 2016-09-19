package com.codingmentorteam3.controllers;

import com.codingmentorteam3.dtos.RoleDTO;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.RoleType;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.LastAdminException;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.RoleService;
import com.codingmentorteam3.services.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@BeanValidation
@RequestScoped
@ManagedBean(name = "roleController")
public class RoleController {

    @Inject
    private RoleService roleService;

    @Inject
    private UserService userService;

    public List<RoleDTO> getRolesListByUserName(String username) {
        User currentUser = userService.getUserByUsername(username);
        if (null != currentUser) {
            List<RoleDTO> roleDTOs = new ArrayList<>();
            for (Role r : roleService.getRolesListByUsername(username)) {
                RoleDTO roleDTO = new RoleDTO(r);
                roleDTOs.add(roleDTO);
            }
            return roleDTOs;
        }
        throw new BadRequestException("No user found in database!");
    }

    public List<RoleDTO> getRolesListByRoleType(RoleType type) {
        List<RoleDTO> roleDTOs = new ArrayList<>();
        for (Role role : roleService.getRolesListByRoleType(type)) {
            RoleDTO roleDTO = new RoleDTO(role);
            roleDTOs.add(roleDTO);
        }
        return roleDTOs;
    }

    public RoleDTO setUserAdminRoleToRoleTable(Long userId) {
        User currentUser = userService.getUser(userId);
        RoleType adminRole = RoleType.ADMIN;
        if (null != currentUser) {
            for (Role role : userService.getRolesListByUserId(userId)) {
                if (adminRole.equals(role.getRoleType())) {
                    RoleDTO roleDTO = new RoleDTO(role);
                    return roleDTO;
                }
            }
            Role newRole = new Role(adminRole, currentUser);
            roleService.createRole(newRole);
            RoleDTO roleDTO = new RoleDTO(newRole);
            return roleDTO;
        }
        throw new BadRequestException("This user not exist in the database.");
    }

    public List<RoleDTO> deleteAdminRoleForThisUser(Long userId) {
        User currentUser = userService.getUser(userId);
        RoleType adminRole = RoleType.ADMIN;
        if (roleService.getRolesListByRoleType(adminRole).size() == 1) {
            throw new LastAdminException("This admin is last admin in database. So you can't delete his role.");
        }
        if (null != currentUser) {
            List<RoleDTO> roleDTOs = new ArrayList<>();
            for (Role role : roleService.getRolesListByUsername(currentUser.getUsername())) {
                if(role.getRoleType().equals(adminRole)) {
                    roleService.deleteRole(role);
                } else {
                    RoleDTO roleDTO = new RoleDTO(role);
                    roleDTOs.add(roleDTO);
                }
            }
            return roleDTOs;
        }
        throw new BadRequestException("This user for username not found in database!");
    }

}
