package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.enums.RoleType;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.EmptyListException;
import com.codingmentorteam3.exceptions.query.NoMatchForFilterException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class RoleDaoImpl extends AbstractDao<Role> {

    public RoleDaoImpl() {
        super(Role.class);
    }

    public List<Role> getRolesList(int limit, int offset) {
        TypedQuery<Role> query = em.createNamedQuery("role.list", Role.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
    
    public List<Role> getRolesListByUsername(String username) {
        if (null != username) {
            TypedQuery<Role> query = em.createNamedQuery("role.list.by.username", Role.class).setParameter("name", username);
            if (null != query.getResultList()) {
                return query.getResultList();
            }
            return null;
        }
        throw new BadRequestException("Username has not been defined correctly.");
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

}
