package lean.toc.manajerobackend.controllers.dad_controllers;


import lean.toc.manajerobackend.models.dad_models.Project;
import lean.toc.manajerobackend.services.dad_services.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dad/projects")
public class ProjectController {
    private IProjectService iProjectService;

    @PostMapping("/add")
    public Project addProject (@RequestBody Project project){
        return iProjectService.addProject(project);
    }

    @GetMapping("/getAll")
    public List<Project> getAllProject (){
        return iProjectService.getAllProject();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProject(@PathVariable String id){
        this.iProjectService.deleteProject(id);
    }
}
