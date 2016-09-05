package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.Project;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class ProjectDaoImpl extends AbstractDao<Project> {

    public ProjectDaoImpl() {
        super(Project.class);
    }

    public List<Project> getProjectsListByNameFilter(String name) {
        try {
            return em.createNamedQuery("project.by.name.filter", Project.class).setParameter("name", name).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Project> getProjectsListByStatusFilter(String status) {
        try {
            return em.createNamedQuery("project.by.status.filter", Project.class).setParameter("status", status).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Project> getProjectsList() {
        try {
            return em.createNamedQuery("project.list", Project.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Company> getCompaniesListByProjectId(Long projectId) {
        Project current = read(projectId);
        if (null != current) {
            try {
                return em.createNamedQuery("project.list.companies.by.id", Company.class).setParameter("id", projectId).getResultList();
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException("We haven't got this event in database.");
    }

}
