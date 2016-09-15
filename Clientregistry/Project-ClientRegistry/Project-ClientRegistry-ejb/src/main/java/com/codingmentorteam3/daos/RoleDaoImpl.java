package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.enums.RoleType;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.NoMatchForFilterException;
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
        if (null != roleType) {
            List<Role> query = em.createNamedQuery("role.list.by.role.type", Role.class).setParameter("rtype", roleType).getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("No results can be found with the given parameter: " + roleType);
            }
            return query;
        }
        throw new BadRequestException("Incorrect role type");
    }
    
    public List<Role> getRolesList() {
        List<Role> query = em.createNamedQuery("role.list", Role.class).getResultList();
//        if (query.isEmpty()) {
//            throw new EmptyListException("There are no users to show.");
//        }
        return query;
    }

}
