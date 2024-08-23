package com.manajero.disciplinedAgileDelivery.service.IT;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // Ensure you have a test profile
public class CollaborationServiceImplIntegrationTest {

    @Autowired
    private CollaborationServiceImpl collaborationService;

    @Autowired
    private CollaborationRepository collaborationRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    private Team testTeam;
    private User testUser;

    @BeforeEach
    public void setUp() {
        // Clean up the repositories
        collaborationRepository.deleteAll();
        teamRepository.deleteAll();
        userRepository.deleteAll();

        // Create and save a test user
        testUser = User.builder()
                .email("testuser@example.com")
                .username("Test User")
                .build();
        userRepository.save(testUser);

        // Create and save a test team
        testTeam = Team.builder()
                .teamId("test-team-id")
                .teamName("Test Team")
                .project(null) // Assuming project is not needed for this test
                .build();
        teamRepository.save(testTeam);
    }

    @Test
    public void testDeleteCollaboration() {
        // Create and save a collaboration
        Collaboration collaboration = Collaboration.builder()
                .team(testTeam)
                .user(testUser)
                .role(Role.SPECIALIST)
                .build();
        collaborationRepository.save(collaboration);

        // Delete the collaboration
        collaborationService.deleteCollaboration(collaboration.getCollaborationId());

        // Verify that the collaboration has been deleted
        assertThat(collaborationRepository.findById(collaboration.getCollaborationId())).isEmpty();
    }
}

