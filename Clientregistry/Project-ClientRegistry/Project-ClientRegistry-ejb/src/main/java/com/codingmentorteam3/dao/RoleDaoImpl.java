package com.codingmentorteam3.dao;

import com.codingmentorteam3.entities.Role;
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

}
