package com.manajero.disciplinedAgileDelivery.service;

import com.manajero.disciplinedAgileDelivery.models.Collaboration;
import com.manajero.disciplinedAgileDelivery.models.Role;
import com.manajero.disciplinedAgileDelivery.models.Team;
import com.manajero.disciplinedAgileDelivery.models.User;
import com.manajero.disciplinedAgileDelivery.repository.CollaborationRepository;
import com.manajero.disciplinedAgileDelivery.repository.TeamRepository;
import com.manajero.disciplinedAgileDelivery.repository.UserRepository;
import com.manajero.disciplinedAgileDelivery.services.CollaborationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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

