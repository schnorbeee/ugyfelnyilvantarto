package com.codingmentorteam3.controllers;

import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.entities.Project;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.ProjectService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author istvan.mosonyi
 */
@BeanValidation
@SessionScoped
@ManagedBean(name = "projectController")
public class ProjectController extends PageableEntityController<Project> {

    @Inject
    private ProjectService projectService;

    @Override
    protected void doPersistEntity() {
        projectService.createProject(getEntity());
    }

    @Override
    protected Project doUpdateEntity() {
        setEntity(projectService.editProject(getEntity()));
        return getEntity();
    }

    @Override
    public List<Project> getEntities() {
        return projectService.getProjectsList(getLimit(), getOffset());
    }

    @Override
    protected Project loadEntity(Long entityId) {
        if (entityId != null) {
            return projectService.getProject(entityId);
        }
        return new Project();
    }

    //atnezni a stringek helyesek-e az alabbi 3 override-nal
    @Override
    public String getListPage() {
        return "project-list";
    }

    @Override
    public String getNewItemOutcome() {
        return "composite/user.xhtml";
    }

    @Override
    public String getNoEntityMessage() {
        return "No project found in database!";
    }
    
}
