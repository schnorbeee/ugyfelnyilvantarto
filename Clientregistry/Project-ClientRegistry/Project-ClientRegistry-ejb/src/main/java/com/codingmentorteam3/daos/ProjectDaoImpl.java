package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Project;
import com.codingmentorteam3.exceptions.BadRequestException;
import com.codingmentorteam3.exceptions.EmptyListException;
import com.codingmentorteam3.exceptions.NoMatchForFilterException;
import java.util.List;
import javax.ejb.Stateless;

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
        if (null != name) {
            List<Project> query = em.createNamedQuery("project.by.name.filter", Project.class).setParameter("name", "%" + name + "%").getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("The results can not be found with this parameter: " + name);
            }
            return query;
        }
        return em.createNamedQuery("project.list", Project.class).getResultList();
    }

    public List<Project> getProjectsListByStatusFilter(String status) {
        if (null != status) {
            List<Project> query = em.createNamedQuery("project.by.status.filter", Project.class).setParameter("status", "%" + status + "%").getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("The results can not be found with this parameter: " + status);
            }
            return query;
        }
        return em.createNamedQuery("project.list", Project.class).getResultList();
    }

    public List<Project> getProjectsList() {
        List<Project> query = em.createNamedQuery("project.list", Project.class).getResultList();
        if (query.isEmpty()) {
            throw new EmptyListException("The project list is empty now.");
        }
        return query;
    }

    public List<Company> getCompaniesListByProjectId(Long projectId) {
        Project current = read(projectId);
        if (null != current) {
            List<Company> query = em.createNamedQuery("project.list.companies.by.id", Company.class).setParameter("id", projectId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This project haven't any company.");
            }
            return query;
        }
        throw new BadRequestException("We haven't got this project in database.");
    }

    public List<ConnectionChannel> getChannelsOfContacterByProjectId(Long projectId) {
        Project current = read(projectId);
        if (null != current) {
            List<ConnectionChannel> query = em.createNamedQuery("project.list.contacter.connection.channel.by.id", ConnectionChannel.class).setParameter("id", projectId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This project haven't contact person or contact person havan't any connection channel.");
            }
            return query;
        }
        throw new BadRequestException("We haven't got this project in database.");
    }

    public List<Project> getProjectsListDeadlineIsInThisWeek() {
        List<Project> query = em.createNamedQuery("project.list.in.this.week", Project.class).getResultList();
        if (query.isEmpty()) {
            throw new EmptyListException("In this week we haven't project in deadline period.");
        }
        return query;
    }

}
