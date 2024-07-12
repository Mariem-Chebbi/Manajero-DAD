package com.manajero.disciplinedAgileDelivery.controller;

import com.manajero.disciplinedAgileDelivery.models.Project;
import com.manajero.disciplinedAgileDelivery.services.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/projects")
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
