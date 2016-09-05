package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.enums.RoleType;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class RoleDaoImpl extends AbstractDao<Role> {

    public RoleDaoImpl() {
        super(Role.class);
    }

    public List<Role> getRolesListByRoleType(RoleType roleType) {
        try {
            return em.createNamedQuery("role.list.by.role.type", Role.class).setParameter("rtype", roleType).getResultList();

        } catch (Exception e) {
            return null;
        }
    }

}
