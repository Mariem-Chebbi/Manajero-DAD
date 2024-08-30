package lean.toc.manajerobackend.controllers.dad_controllers;


import lean.toc.manajerobackend.models.dad_models.Collaboration;
import lean.toc.manajerobackend.models.dad_models.Role;
import lean.toc.manajerobackend.services.dad_services.ICollaborationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dad/collaborations")
public class CollaborationController {
    private ICollaborationService iCollaborationService;

    @GetMapping("/getAll/{projectId}")
    public List<Collaboration> getCollaborationsByProjectId(@PathVariable String projectId) {
        return iCollaborationService.getCollaborationsByProjectId(projectId);
    }

    @PutMapping("/assign/{email}/{projectId}/{role}")
    public Collaboration assignUserToTeam(@PathVariable String email,@PathVariable String projectId,@PathVariable Role role){
        return iCollaborationService.assignUserToTeam(email,projectId,role);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCollaboration(@PathVariable String id) {
        iCollaborationService.deleteCollaboration(id);
    }
}
