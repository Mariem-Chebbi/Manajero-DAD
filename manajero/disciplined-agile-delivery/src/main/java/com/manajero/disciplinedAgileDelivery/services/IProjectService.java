package com.manajero.disciplinedAgileDelivery.services;


import com.manajero.disciplinedAgileDelivery.models.Project;

import java.util.List;

public interface IProjectService {
    Project addProject(Project project);
    List<Project> getAllProject();
    void deleteProject(String id);

}
