package com.manajero.disciplinedAgileDelivery.controller;


import com.manajero.disciplinedAgileDelivery.models.Team;
import com.manajero.disciplinedAgileDelivery.repository.ProjectRepository;
import com.manajero.disciplinedAgileDelivery.services.IProjectService;
import com.manajero.disciplinedAgileDelivery.services.ITeamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/teams")
public class TeamController {
    private ITeamService iTeamService;
    private ProjectRepository projectRepository;

    @PostMapping("/add")
    public Team addTeam (@RequestBody Team team, @RequestParam String idProject){
        team.setProject(projectRepository.findById(idProject).orElse(null));
        return iTeamService.addTeam(team,idProject);
    }

    @GetMapping("/get/{projectId}")
    public Team getTeamByProjectId(@PathVariable String projectId) {
        return iTeamService.getTeamByProjectId(projectId);
    }
}
