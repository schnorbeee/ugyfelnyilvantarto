package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Project;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
            List<Project> query = em.createNamedQuery("project.by.name.filter", Project.class).setParameter("name", "%" + name + "%").getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Project> getProjectsListByStatusFilter(String status) {
        try {
            List<Project> query = em.createNamedQuery("project.by.status.filter", Project.class).setParameter("status", "%" + status + "%").getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Project> getProjectsList() {
        try {
            List<Project> query = em.createNamedQuery("project.list", Project.class).getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Company> getCompaniesListByProjectId(Long projectId) {
        Project current = read(projectId);
        if (null != current) {
            try {
                List<Company> query = em.createNamedQuery("project.list.companies.by.id", Company.class).setParameter("id", projectId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException("We haven't got this project in database.");
    }
    
    public List<ConnectionChannel> getChannelsByProjectId(Long projectId) {
    Project current = read(projectId);
        if (null != current) {
            try {
                List<ConnectionChannel> query = em.createNamedQuery("project.list.contacter.connection.channel.by.id", ConnectionChannel.class).setParameter("id", projectId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException("We haven't got this project in database.");
    }

    public List<Project> getProjectsListDeadlineIsInThisWeek() {
        try {
            List<Project> query = em.createNamedQuery("project.list.in.this.week", Project.class).getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }
    
    /*public List<Date> setWeekDaysToNow() {
        LocalDate now = LocalDate.now();
        Date mondayDate;
        Date sundayDate;
        switch(now.getDayOfWeek()) {
            case MONDAY:
                mondayDate = Date.valueOf(now);
                sundayDate = Date.valueOf(now.plusDays(6));
                break;
            case TUESDAY:
                mondayDate = Date.valueOf(now.minusDays(1));
                sundayDate = Date.valueOf(now.plusDays(5));
                break;
            case WEDNESDAY:
                mondayDate = Date.valueOf(now.minusDays(2));
                sundayDate = Date.valueOf(now.plusDays(4));
                break;    
            case THURSDAY:
                mondayDate = Date.valueOf(now.minusDays(3));
                sundayDate = Date.valueOf(now.plusDays(3));
                break;    
            case FRIDAY:
                mondayDate = Date.valueOf(now.minusDays(4));
                sundayDate = Date.valueOf(now.plusDays(2));
                break;
            case SATURDAY:
                mondayDate = Date.valueOf(now.minusDays(5));
                sundayDate = Date.valueOf(now.plusDays(1));
                break;
            case SUNDAY:
                mondayDate = Date.valueOf(now.minusDays(6));
                sundayDate = Date.valueOf(now.plusDays(0));
                break;
            default:
                throw new IllegalArgumentException("Illegal state of day registered.");
        }
        List<Date> days = new ArrayList();
        days.add(0, mondayDate);
        days.add(1, sundayDate);
        return days; 
    }*/
    
}
