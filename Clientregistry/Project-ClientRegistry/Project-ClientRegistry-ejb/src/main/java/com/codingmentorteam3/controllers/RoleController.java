package com.codingmentorteam3.controllers;

import com.codingmentorteam3.dtos.RoleDTO;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.exceptions.query.BadRequestException;
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

    public List<RoleDTO> getRoleListByUserName(String username) {
        User currentUser = userService.getUserByUsername(username);
        if (null != currentUser) {
            List<RoleDTO> roleDTOs = new ArrayList<>();
            for (Role r : roleService.getRolesListByUsername(username)) {
                RoleDTO roleDTO = new RoleDTO(r);
                roleDTOs.add(roleDTO);
            }
            return roleDTOs;
        }
        throw new BadRequestException();
    }

}
