package com.manajero.disciplinedAgileDelivery.controller;

import com.manajero.disciplinedAgileDelivery.models.Collaboration;
import com.manajero.disciplinedAgileDelivery.models.Role;
import com.manajero.disciplinedAgileDelivery.models.User;
import com.manajero.disciplinedAgileDelivery.services.ICollaborationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/collaborations")
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
