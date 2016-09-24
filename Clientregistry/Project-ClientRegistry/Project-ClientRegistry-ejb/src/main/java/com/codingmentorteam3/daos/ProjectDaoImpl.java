package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Project;
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

    public List<Project> getProjectsListByStringFilter(String name, int limit, int offset) {
        if (null != name) {
            TypedQuery<Project> query = em.createNamedQuery("project.by.string.filter", Project.class);
            query.setParameter("name", "%" + name + "%");
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        }
        TypedQuery<Project> query = em.createNamedQuery("project.list", Project.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<Project> getProjectsListByStatusFilter(String status, int limit, int offset) {
        if (null != status) {
            TypedQuery<Project> query = em.createNamedQuery("project.by.status.filter", Project.class);
            query.setParameter("status", "%" + status + "%");
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        }
        TypedQuery<Project> query = em.createNamedQuery("project.list", Project.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
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
