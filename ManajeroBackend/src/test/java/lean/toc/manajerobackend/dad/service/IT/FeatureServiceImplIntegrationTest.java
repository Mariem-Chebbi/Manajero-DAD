package lean.toc.manajerobackend.dad.service.IT;


import lean.toc.manajerobackend.dto.dad_dto.StatusPercentage;
import lean.toc.manajerobackend.models.dad_models.*;
import lean.toc.manajerobackend.repositories.dad_repositories.*;
import lean.toc.manajerobackend.services.dad_services.FeatureServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // Ensure you have a test profile
public class FeatureServiceImplIntegrationTest {

    @Autowired
    private FeatureServiceImpl featureService;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private IterationRepository iterationRepository;

    @Autowired
    private ReleaseRepository releaseRepository;

    private Feature testFeature;
    private Project testProject;
    private Iteration testIteration;
    private Release testRelease;
    private User testUser;

    @BeforeEach
    public void setUp() {
        // Clean up the repositories
        featureRepository.deleteAll();
        projectRepository.deleteAll();
        iterationRepository.deleteAll();
        releaseRepository.deleteAll();

        // Create and save a test project
        testProject = Project.builder()
                .projectId("test-project-id")
                .title("Test Project")
                .status("In Progress")
                .statementWork("Sample Statement")
                .description("Test Project Description")
                .dateSubmitted(LocalDate.now())
                .archived(false)
                .build();
        projectRepository.save(testProject);

        // Create and save a test iteration
        testIteration = Iteration.builder()
                .iterationId("test-iteration-id")
                .name("Test Iteration")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(1))
                .status("to do")
                .project(testProject)
                .build();
        iterationRepository.save(testIteration);

        // Create and save a test release
        testRelease = Release.builder()
                .releaseId("test-release-id")
                .name("Test Release")
                .status("Released")
                .progres(50.0f)
                .startDate("2024-01-01")
                .releaseDate("2024-06-01")
                .description("Test Release Description")
                .project(testProject)
                .isArchived(false)
                .build();
        releaseRepository.save(testRelease);

        // Create and save a test user
        testUser = User.builder()
                ._id("test-user-id")
                .username("Test User")
                .email("testuser@example.com")
                .build();

        // Create and save a test feature
        testFeature = Feature.builder()
                .featureId("test-feature-id")
                .title("Test Feature")
                .description("Test Feature Description")
                .status("to do")
                .priority("High")
                .project(testProject)
                .release(testRelease)
                .user(testUser)
                .iteration(testIteration)
                .isArchived(false)
                .build();
        featureRepository.save(testFeature);
    }

    @Test
    public void testAddFeature() {
        Feature newFeature = Feature.builder()
                .featureId("new-feature-id")
                .title("New Feature")
                .description("New Feature Description")
                .status("to do")
                .priority("Medium")
                .project(testProject)
                .release(testRelease)
                .user(testUser)
                .iteration(testIteration)
                .isArchived(false)
                .build();

        Feature savedFeature = featureService.addFeature(newFeature, testProject.getProjectId());
        assertThat(savedFeature).isNotNull();
        assertThat(savedFeature.getFeatureId()).isEqualTo("new-feature-id");
        assertThat(savedFeature.getTitle()).isEqualTo("New Feature");
    }

    @Test
    public void testEditFeature() {
        testFeature.setTitle("Updated Feature");
        Feature updatedFeature = featureService.editFeature(testFeature);
        assertThat(updatedFeature).isNotNull();
        assertThat(updatedFeature.getTitle()).isEqualTo("Updated Feature");
    }

    @Test
    public void testGetAllFeatures() {
        List<Feature> features = featureService.getAllFeatures(testProject.getProjectId());
        assertThat(features).isNotEmpty();
        assertThat(features.size()).isGreaterThan(0);
    }

    @Test
    public void testGetFeaturesByRelease() {
        List<Feature> features = featureService.getFeaturesByRelease(testRelease.getReleaseId());
        assertThat(features).isNotEmpty();
        assertThat(features.size()).isGreaterThan(0);
    }

    @Test
    public void testDeleteFeature() {
        featureService.deleteFeature(testFeature.getFeatureId());
        Feature deletedFeature = featureRepository.findById(testFeature.getFeatureId()).orElse(null);
        assertThat(deletedFeature).isNull();
    }

    @Test
    public void testCalculateStatusPercentages() {
        List<StatusPercentage> statusPercentages = featureService.calculateStatusPercentages(testProject.getProjectId());
        assertThat(statusPercentages).isNotEmpty();

        StatusPercentage toDoPercentage = statusPercentages.stream()
                .filter(sp -> "to do".equals(sp.getStatus()))
                .findFirst()
                .orElse(null);

        assertThat(toDoPercentage).isNotNull();
        assertThat(toDoPercentage.getPercentage()).isGreaterThan(0);
    }

    @Test
    public void testAssignFeaturesToIteration() {
        Feature newFeature = Feature.builder()
                .featureId("assign-feature-id")
                .title("Assign Feature")
                .description("Feature to Assign")
                .status("to do")
                .priority("Low")
                .project(testProject)
                .release(testRelease)
                .user(testUser)
                .isArchived(false)
                .build();
        featureRepository.save(newFeature);

        featureService.assignFeaturesToIteration(Collections.singletonList(newFeature), testIteration.getIterationId());
        Feature assignedFeature = featureRepository.findById(newFeature.getFeatureId()).orElse(null);
        assertThat(assignedFeature).isNotNull();
        assertThat(assignedFeature.getIteration()).isEqualTo(testIteration);
    }

    @Test
    public void testUnassignFeaturesToIteration() {
        testFeature.setIteration(testIteration);
        featureRepository.save(testFeature);

        featureService.UnassignFeaturesToIteration(testFeature);
        Feature updatedFeature = featureRepository.findById(testFeature.getFeatureId()).orElse(null);
        assertThat(updatedFeature).isNotNull();
        assertThat(updatedFeature.getIteration()).isNull();
    }

    @Test
    public void testArchiveFeature() {
        featureService.archiveFeature(testFeature.getFeatureId());
        Feature archivedFeature = featureRepository.findById(testFeature.getFeatureId()).orElse(null);
        assertThat(archivedFeature).isNotNull();
        assertThat(archivedFeature.getIsArchived()).isTrue();
    }

    @Test
    public void testRestoreFeature() {
        featureService.archiveFeature(testFeature.getFeatureId()); // Archive first
        featureService.restoreFeature(testFeature.getFeatureId());
        Feature restoredFeature = featureRepository.findById(testFeature.getFeatureId()).orElse(null);
        assertThat(restoredFeature).isNotNull();
        assertThat(restoredFeature.getIsArchived()).isFalse();
    }
}
