package com.manajero.disciplinedAgileDelivery.service;

import com.manajero.disciplinedAgileDelivery.models.Team;
import com.manajero.disciplinedAgileDelivery.repository.TeamRepository;
import com.manajero.disciplinedAgileDelivery.services.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TeamServiceImplTests {

    @InjectMocks
    private TeamServiceImpl teamService;

    @Mock
    private TeamRepository teamRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTeam() {
        Team team = new Team();
        String projectId = "proj123";

        // Mocking repository call
        when(teamRepository.save(any(Team.class))).thenReturn(team);

        // Calling the method under test
        Team result = teamService.addTeam(team, projectId);

        // Verifying interactions
        verify(teamRepository).save(team);
        assert(result != null);
    }

    @Test
    public void testGetTeamByProjectId() {
        String projectId = "proj123";
        Team team = new Team();

        // Mocking repository call
        when(teamRepository.findTeamByProjectProjectId(eq(projectId))).thenReturn(team);

        // Calling the method under test
        Team result = teamService.getTeamByProjectId(projectId);

        // Verifying interactions
        verify(teamRepository).findTeamByProjectProjectId(projectId);
        assert(result != null);
    }
}
