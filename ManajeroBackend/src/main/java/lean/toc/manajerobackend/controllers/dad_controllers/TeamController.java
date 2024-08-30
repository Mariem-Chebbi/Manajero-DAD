package lean.toc.manajerobackend.controllers.dad_controllers;



import lean.toc.manajerobackend.models.dad_models.Team;
import lean.toc.manajerobackend.repositories.dad_repositories.ProjectRepository;
import lean.toc.manajerobackend.services.dad_services.ITeamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/dad/teams")
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
