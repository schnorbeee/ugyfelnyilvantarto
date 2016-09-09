package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.enums.RoleType;
import com.codingmentorteam3.exceptions.BadRequestException;
import com.codingmentorteam3.exceptions.NoMatchForFilterException;
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
                throw new NoMatchForFilterException("The results can not be found with this parameter: " + roleType);
            }
            return query;
        }
        throw new BadRequestException("The role type is not be specificated in this query.");
    }

}
