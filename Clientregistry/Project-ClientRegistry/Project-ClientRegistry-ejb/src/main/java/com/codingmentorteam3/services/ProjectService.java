package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.ProjectDaoImpl;
import com.codingmentorteam3.entities.Project;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Bicsak Daniel
 */
@Stateless
public class ProjectService {

    @Inject
    private ProjectDaoImpl projectDao;

    public void createProject(Project project) {
        projectDao.create(project);
    }

    public Project getProject(Long projectId) {
        return projectDao.read(projectId);
    }

    public Project editProject(Project project) {
        return projectDao.update(project);
    }

    public Project deleteProject(Project project) {
        return projectDao.delete(project);
    }
    
    public List<Project> getProjectsList(Integer limit, Integer offset) {
        return projectDao.getProjectsList(limit, offset);
    }
    
}
