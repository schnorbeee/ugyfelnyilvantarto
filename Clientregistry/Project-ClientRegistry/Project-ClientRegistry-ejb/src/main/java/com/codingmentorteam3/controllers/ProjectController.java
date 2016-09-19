package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.ProjectBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.dtos.CompanyDTO;
import com.codingmentorteam3.dtos.ProjectDTO;
import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.Project;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.EntityAlreadyExistsException;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.AddressService;
import com.codingmentorteam3.services.CompanyService;
import com.codingmentorteam3.services.ProjectService;
import java.util.ArrayList;
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
    
    @Inject
    private CompanyService companyService;

    //user method
    public String createProject(ProjectBean regProject, Long companyId) {
        Project newProject = new Project(regProject);
        Company currentCompany = companyService.getCompany(1L);
        for(Project p : currentCompany.getProjects()) {
            if(newProject.getName().equals(p.getName()) && newProject.getDescription().equals(p.getDescription())) {
                throw new EntityAlreadyExistsException("This project already exists in our database.");
            }
        }
        newProject.getCompanies().add(currentCompany);
        projectService.createProject(newProject);
        return "";
    }

    //user method
    public String getProjectById(Long projectId) {
        Project project = projectService.getProject(projectId);
        if (null != project) {
            return "";
        }
        throw new BadRequestException(getNoEntityMessage());
    }
    
    //user method
    public ProjectDTO updateProject(ProjectBean updateProject, Long projectId) {
        Project oldProject = projectService.getProject(projectId);
        if(null != oldProject) {
            Project currentProject = new Project(updateProject);
            oldProject = modifiedCheckerProject(oldProject, currentProject);
            projectService.editProject(oldProject);
            return new ProjectDTO(oldProject);
        }
        throw new BadRequestException(getNoEntityMessage());
    }
    
    //admin method
    public List<ProjectDTO> deleteCompanyById(Long projectId) {
        Project deleteProject = projectService.getProject(projectId);
        if (null != deleteProject) {
            for(Company c : deleteProject.getCompanies()) {
                c.getProjects().remove(deleteProject);
                companyService.editCompany(c);
            }
            projectService.deleteProject(deleteProject);
            List<ProjectDTO> projectDTOs = new ArrayList<>();
            for(Project p : getEntities()) {
                ProjectDTO projectDTO = new ProjectDTO(p);
                projectDTOs.add(projectDTO);
            }
            return projectDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }
    
    //user method
    public List<ProjectDTO> getProjectsList() {
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project p : getEntities()) {
            ProjectDTO projectDTO = new ProjectDTO(p);
            projectDTOs.add(projectDTO);
        }
        return projectDTOs;
    }
    
    //user method
    public List<CompanyDTO> getCompaniesListByProjectId(Long projectId) {
        Project currentProject = projectService.getProject(projectId);
        if (null != currentProject) {
            List<CompanyDTO> companyDTOs = new ArrayList<>();
            for (Company c : projectService.getCompaniesListByProjectId(projectId)) {
                CompanyDTO companyDTO = new CompanyDTO(c);
                companyDTOs.add(companyDTO);
            }
            return companyDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }
    
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
        return "composite/createProject.xhtml";
    }

    @Override
    public String getNoEntityMessage() {
        return "No project found in database!";
    }
   
    public Project modifiedCheckerProject(Project oldProject, Project currentProject) {
        if (!currentProject.getName().equals("")) {
            oldProject.setName(currentProject.getName());
        }
        if (!currentProject.getDescription().equals("")) {
            oldProject.setDescription(currentProject.getDescription());
        }
        if (!currentProject.getStatus().equals(oldProject.getStatus())) {
            oldProject.setStatus(currentProject.getStatus());
        }
        if (!currentProject.getStartDate().equals(oldProject.getStartDate())) {
            oldProject.setStartDate(currentProject.getStartDate());
        }
        if (!currentProject.getDeadline().equals(oldProject.getDeadline())) {
            oldProject.setDeadline(currentProject.getDeadline());
        }
        return oldProject;
    }
    
}
