package lean.toc.manajerobackend.services.dad_services;



import lean.toc.manajerobackend.models.dad_models.Project;

import java.util.List;

public interface IProjectService {
    Project addProject(Project project);
    List<Project> getAllProject();
    void deleteProject(String id);

}
