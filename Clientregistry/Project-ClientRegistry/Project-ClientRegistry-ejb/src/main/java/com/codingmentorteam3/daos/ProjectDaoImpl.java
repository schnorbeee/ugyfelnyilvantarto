package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Project;
import com.codingmentorteam3.exceptions.query.NoMatchForFilterException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class ProjectDaoImpl extends AbstractDao<Project> {

    public ProjectDaoImpl() {
        super(Project.class);
    }

    //kerdeses kell-e?
    public List<Project> getProjectsListByNameFilter(String name) {
        if (null != name) {
            List<Project> query = em.createNamedQuery("project.by.name.filter", Project.class).setParameter("name", "%" + name + "%").getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("Results can not be found with this parameter: " + name);
            }
            return query;
        }
        return em.createNamedQuery("project.list", Project.class).getResultList();
    }

    //kerdeses kell-e?
    public List<Project> getProjectsListByStatusFilter(String status) {
        if (null != status) {
            List<Project> query = em.createNamedQuery("project.by.status.filter", Project.class).setParameter("status", "%" + status + "%").getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("Results can not be found with this parameter: " + status);
            }
            return query;
        }
        return em.createNamedQuery("project.list", Project.class).getResultList();
    }

    public List<Project> getProjectsList(int limit, int offset) {
        TypedQuery<Project> query = em.createNamedQuery("project.list", Project.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<Company> getCompaniesListByProjectId(Long projectId) {
        TypedQuery<Company> query = em.createNamedQuery("project.list.companies.by.id", Company.class);
        query.setParameter("id", projectId);
        return query.getResultList();
    }

    public List<ConnectionChannel> getChannelsOfContacterByProjectId(Long projectId) {
        TypedQuery<ConnectionChannel> query = em.createNamedQuery("project.list.contacter.connection.channel.by.id", ConnectionChannel.class);
        query.setParameter("id", projectId);
        return query.getResultList();
    }

    public List<Project> getProjectsListDeadlineIsInThisWeek() {
        TypedQuery<Project> query = em.createNamedQuery("project.list.in.this.week", Project.class);
        return query.getResultList();
    }

}
