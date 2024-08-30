package lean.toc.manajerobackend.dad.service;


import lean.toc.manajerobackend.models.dad_models.Collaboration;
import lean.toc.manajerobackend.models.dad_models.Role;
import lean.toc.manajerobackend.models.dad_models.Team;
import lean.toc.manajerobackend.models.dad_models.User;
import lean.toc.manajerobackend.repositories.dad_repositories.CollaborationRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.TeamRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.UserRepository;
import lean.toc.manajerobackend.services.dad_services.CollaborationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CollaborationServiceImplTests {

    @InjectMocks
    private CollaborationServiceImpl collaborationService;

    @Mock
    private CollaborationRepository collaborationRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCollaborationsByProjectId() {
        String projectId = "proj123";

        // Mocking the repository call
        when(collaborationRepository.findByTeam_Project_ProjectId(projectId)).thenReturn(List.of());

        // Calling the method under test
        collaborationService.getCollaborationsByProjectId(projectId);

        // Verifying interactions
        verify(collaborationRepository).findByTeam_Project_ProjectId(projectId);
    }

    @Test
    public void testAssignUserToTeam_existingCollaboration() {
        String email = "user@example.com";
        String projectId = "proj123";
        Role role = Role.INTEGRATOR;

        Team team = new Team();
        User user = new User();
        Collaboration existingCollaboration = new Collaboration();
        existingCollaboration.setRole(role);

        // Mocking repository calls
        when(collaborationRepository.findByTeam_Project_ProjectIdAndUser_Email(projectId, email)).thenReturn(Optional.of(existingCollaboration));
        when(teamRepository.findTeamByProjectProjectId(projectId)).thenReturn(team);
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
        when(collaborationRepository.save(any(Collaboration.class))).thenReturn(existingCollaboration);

        // Calling the method under test
        Collaboration result = collaborationService.assignUserToTeam(email, projectId, role);

        // Verifying interactions
        verify(collaborationRepository).findByTeam_Project_ProjectIdAndUser_Email(projectId, email);
        verify(collaborationRepository).save(existingCollaboration);
        assert(result != null);
    }

    @Test
    public void testAssignUserToTeam_newCollaboration() {
        String email = "user@example.com";
        String projectId = "proj123";
        Role role = Role.INTEGRATOR;

        Team team = new Team();
        User user = new User();

        // Mocking repository calls
        when(collaborationRepository.findByTeam_Project_ProjectIdAndUser_Email(projectId, email)).thenReturn(Optional.empty());
        when(teamRepository.findTeamByProjectProjectId(projectId)).thenReturn(team);
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
        when(collaborationRepository.save(any(Collaboration.class))).thenReturn(new Collaboration());

        // Calling the method under test
        Collaboration result = collaborationService.assignUserToTeam(email, projectId, role);

        // Verifying interactions
        verify(collaborationRepository).findByTeam_Project_ProjectIdAndUser_Email(projectId, email);
        verify(collaborationRepository).save(any(Collaboration.class));
        assert(result != null);
    }

    @Test
    public void testDeleteCollaboration() {
        String collaborationId = "collab123";

        // Calling the method under test
        collaborationService.deleteCollaboration(collaborationId);

        // Verifying interactions
        verify(collaborationRepository).deleteById(collaborationId);
    }
}

