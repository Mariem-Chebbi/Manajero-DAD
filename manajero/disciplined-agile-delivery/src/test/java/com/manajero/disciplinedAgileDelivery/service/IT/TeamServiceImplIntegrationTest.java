package com.manajero.disciplinedAgileDelivery.service.IT;

import com.manajero.disciplinedAgileDelivery.models.Project;
import com.manajero.disciplinedAgileDelivery.models.Team;
import com.manajero.disciplinedAgileDelivery.repository.TeamRepository;
import com.manajero.disciplinedAgileDelivery.services.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // Ensure you have a test profile
public class TeamServiceImplIntegrationTest {

    @Autowired
    private TeamServiceImpl teamService;

    @Autowired
    private TeamRepository teamRepository;

    private Team testTeam;
    private Project testProject;

    @BeforeEach
    public void setUp() {
        // Clean up the repository
        teamRepository.deleteAll();

        // Create a test project
        testProject = Project.builder()
                .projectId("test-project-id")
                .title("Test Project")
                .status("In Progress")
                .statementWork("Sample Statement")
                .description("Test Project Description")
                .dateSubmitted(LocalDate.now())
                .archived(false)
                .build();

        // Create a test team
        testTeam = Team.builder()
                .teamId("test-team-id")
                .teamName("Test Team")
                .project(testProject)
                .build();
    }

    @Test
    public void testAddTeam() {
        Team savedTeam = teamService.addTeam(testTeam, testProject.getProjectId());
        assertThat(savedTeam).isNotNull();
        assertThat(savedTeam.getTeamId()).isNotNull();
        assertThat(savedTeam.getTeamName()).isEqualTo(testTeam.getTeamName());
        assertThat(savedTeam.getProject().getProjectId()).isEqualTo(testProject.getProjectId());
    }

    @Test
    public void testGetTeamByProjectId() {
        teamService.addTeam(testTeam, testProject.getProjectId());
        Team retrievedTeam = teamService.getTeamByProjectId(testProject.getProjectId());
        assertThat(retrievedTeam).isNotNull();
        assertThat(retrievedTeam.getTeamName()).isEqualTo(testTeam.getTeamName());
    }
}
