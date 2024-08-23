package com.manajero.disciplinedAgileDelivery.service.IT;

import com.manajero.disciplinedAgileDelivery.models.Objective;
import com.manajero.disciplinedAgileDelivery.models.Project;
import com.manajero.disciplinedAgileDelivery.repository.ObjectiveRepository;
import com.manajero.disciplinedAgileDelivery.repository.ProjectRepository;
import com.manajero.disciplinedAgileDelivery.services.ObjectifServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // Make sure you have a test profile
public class ObjectifServiceImplIntegrationTest {

    @Autowired
    private ObjectifServiceImpl objectifService;

    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private Project testProject;
    private Objective testObjective;

    @BeforeEach
    public void setUp() {
        // Clean up the repositories
        objectiveRepository.deleteAll();
        projectRepository.deleteAll();

        // Create and save a test project
        testProject = new Project();
        testProject.setTitle("Test Project");
        testProject.setStatus("Active");
        testProject.setStatementWork("Test statement work");
        testProject.setDescription("Test description");
        testProject.setDateSubmitted(LocalDate.now());
        testProject.setArchived(false);
        testProject = projectRepository.save(testProject);

        // Create a test objective
        testObjective = new Objective();
        testObjective.setDescription("Test Objective");
        testObjective.setIsAchieved(false);
        testObjective.setIsArchived(false);
        testObjective.setProject(testProject);
    }

    @Test
    public void testAddObjectif() {
        Objective savedObjective = objectifService.addObjectif(testObjective, testProject.getProjectId());
        assertThat(savedObjective).isNotNull();
        assertThat(savedObjective.getObjectiveId()).isNotNull();
        assertThat(savedObjective.getDescription()).isEqualTo(testObjective.getDescription());
    }

    @Test
    public void testGetAllObjectif() {
        objectifService.addObjectif(testObjective, testProject.getProjectId());
        List<Objective> objectives = objectifService.getAllObjectif(testProject.getProjectId());
        assertThat(objectives).isNotEmpty();
        assertThat(objectives.size()).isEqualTo(1);
    }

    @Test
    public void testEditObjectif() {
        Objective savedObjective = objectifService.addObjectif(testObjective, testProject.getProjectId());
        savedObjective.setDescription("Updated Description");
        Objective updatedObjective = objectifService.editObjectif(savedObjective);
        assertThat(updatedObjective).isNotNull();
        assertThat(updatedObjective.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    public void testDeleteObjectif() {
        Objective savedObjective = objectifService.addObjectif(testObjective, testProject.getProjectId());
        objectifService.deleteObjectif(savedObjective.getObjectiveId());
        assertThat(objectiveRepository.findById(savedObjective.getObjectiveId())).isEmpty();
    }

    @Test
    public void testArchiveAndRestoreObjective() {
        Objective savedObjective = objectifService.addObjectif(testObjective, testProject.getProjectId());

        // Archive the objective
        objectifService.archiveObjective(savedObjective.getObjectiveId());
        Objective archivedObjective = objectifService.getObjectifById(savedObjective.getObjectiveId());
        assertThat(archivedObjective).isNotNull();
        assertThat(archivedObjective.getIsArchived()).isTrue();

        // Restore the objective
        objectifService.restoreObjective(savedObjective.getObjectiveId());
        Objective restoredObjective = objectifService.getObjectifById(savedObjective.getObjectiveId());
        assertThat(restoredObjective).isNotNull();
        assertThat(restoredObjective.getIsArchived()).isFalse();
    }
}
