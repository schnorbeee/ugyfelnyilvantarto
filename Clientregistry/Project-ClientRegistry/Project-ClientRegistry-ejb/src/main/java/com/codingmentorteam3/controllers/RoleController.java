package com.codingmentorteam3.controllers;

import com.codingmentorteam3.dtos.RoleDTO;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.RoleType;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.LastAdminException;
import com.codingmentorteam3.services.RoleService;
import com.codingmentorteam3.services.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@ManagedBean(name = "roleController")
public class RoleController {

    @Inject
    private RoleService roleService;

    @Inject
    private UserService userService;

    public Response getRoleListByUserName(@QueryParam("user_id") String username) {
        User currentUser = userService.getUserByUsername(username);
        if (null != currentUser) {
            List<RoleDTO> roles = new ArrayList();
            for (Role r : roleService.getRolesListByUsername(username)) {
                RoleDTO role = new RoleDTO(r);
                roles.add(role);
            }
            return Response.ok(roles).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response getRoleListByRoleType(@QueryParam("role_type") RoleType type) {
        if (null != type) {
            List<RoleDTO> roles = new ArrayList();
            for (Role r : roleService.getRolesListByRoleType(type)) {
                RoleDTO role = new RoleDTO(r);
                roles.add(role);
            }
            return Response.ok(roles).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response setUserAdminRoleToRoleTable(@QueryParam("user_id") Long id) {
        User currentUser = userService.getUser(id);
        RoleType adminRole = RoleType.ADMIN;
        if (null != currentUser) {
            for (Role role : userService.getRolesListByUserId(currentUser.getId())) {
                if (adminRole.equals(role.getRoleType())) {
                    RoleDTO dto = new RoleDTO(role);
                    return Response.status(Response.Status.FOUND).entity(dto).type(MediaType.APPLICATION_JSON).build();
                }
            }
            Role newRole = new Role(adminRole, currentUser);
            roleService.createRole(newRole);
            RoleDTO dto = new RoleDTO(newRole);
            return Response.status(Response.Status.CREATED).entity(dto).type(MediaType.APPLICATION_JSON).build();
        }
        throw new BadRequestException("This user not exist in the database.");
    }

    public Response deleteAdminRoleForThisUser(@QueryParam("user_id") Long id) {
        User current = userService.getUser(id);
        RoleType adminRole = RoleType.ADMIN;
        if (null != current) {
            for (Role role : roleService.getRolesListByRoleType(adminRole)) {
                if (roleService.getRolesListByRoleType(adminRole).size() == 1) {
                    throw new LastAdminException("This admin is last admin in database. So you can't delete his role.");
                }
                if (current.equals(role.getUser())) {
                    roleService.deleteRole(role);
                    RoleDTO dto = new RoleDTO(role);
                    return Response.status(Response.Status.ACCEPTED).entity(dto).type(MediaType.APPLICATION_JSON).build();
                }
            }
            throw new BadRequestException("This user haven't admin privilege.");
        }
        throw new BadRequestException("This user for this username don't exist in database.");
    }

}
