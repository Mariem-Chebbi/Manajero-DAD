package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Collaboration;
import com.manajero.disciplinedAgileDelivery.models.Role;
import com.manajero.disciplinedAgileDelivery.models.Team;
import com.manajero.disciplinedAgileDelivery.models.User;
import com.manajero.disciplinedAgileDelivery.repository.CollaborationRepository;
import com.manajero.disciplinedAgileDelivery.repository.TeamRepository;
import com.manajero.disciplinedAgileDelivery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CollaborationServiceImpl implements ICollaborationService {
    private CollaborationRepository collaborationRepository;
    private TeamRepository teamRepository;
    private UserRepository userRepository;

    public List<Collaboration> getCollaborationsByProjectId(String projectId) {
        return collaborationRepository.findByTeam_Project_ProjectId(projectId);
    }

    public Collaboration assignUserToTeam(String email, String projectId, Role role) {
        // Vérifiez si une collaboration existe déjà pour l'utilisateur et l'équipe
        Optional<Collaboration> existingCollaboration = collaborationRepository.findByTeam_Project_ProjectIdAndUser_Email(projectId, email);

        // Debugging output to track what's happening
        System.out.println("Existing Collaboration: " + existingCollaboration);

        Team team = teamRepository.findTeamByProjectProjectId(projectId);
        User user = userRepository.findUserByEmail(email).orElse(null);

        if (team == null || user == null) {
            System.out.println("Team or User not found!");
            return null; // or handle as needed
        }

        if (existingCollaboration.isPresent()) {
            // Mise à jour de la collaboration existante
            Collaboration collaboration = existingCollaboration.get();
            collaboration.setRole(role);
            System.out.println("Updating existing collaboration");
            return collaborationRepository.save(collaboration);
        } else {
            // Création d'une nouvelle collaboration
            Collaboration newCollaboration = Collaboration.builder()
                    .user(user)
                    .team(team)
                    .role(role)
                    .build();
            System.out.println("Creating new collaboration");
            return collaborationRepository.save(newCollaboration);
        }
    }

    @Override
    public void deleteCollaboration(String id) {
        collaborationRepository.deleteById(id);
    }

}
