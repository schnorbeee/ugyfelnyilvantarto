package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.RoleType;
import com.codingmentorteam3.exceptions.query.BadRequestException;
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

    public List<Role> getRolesListByUsername(String username) {
        if (null != username) {
            TypedQuery<Role> query = em.createNamedQuery("role.list.by.username", Role.class);
            query.setParameter("name", username);
            return query.getResultList();
        }
        throw new BadRequestException("Username has not been defined correctly.");
    }

    public List<User> getUsersListByRoleType(RoleType roleType, int limit, int offset) {
        TypedQuery<User> query = em.createNamedQuery("role.list.users.by.role.type", User.class);
        query.setParameter("rtype", roleType);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

}
