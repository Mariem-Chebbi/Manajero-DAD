package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.models.dad_models.Collaboration;
import lean.toc.manajerobackend.models.dad_models.Role;
import lean.toc.manajerobackend.models.dad_models.Team;
import lean.toc.manajerobackend.models.dad_models.User;
import lean.toc.manajerobackend.repositories.dad_repositories.CollaborationRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.TeamRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
