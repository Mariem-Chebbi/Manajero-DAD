package lean.toc.manajerobackend.dad.service.IT;

import lean.toc.manajerobackend.dto.dad_dto.ProjectStatistics;
import lean.toc.manajerobackend.models.dad_models.*;
import lean.toc.manajerobackend.repositories.dad_repositories.*;
import lean.toc.manajerobackend.services.dad_services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test") // Ensure you have a test profile
public class DashboardServiceImplIntegrationTest {

    @Autowired
    private DashboardServiceImpl dashboardService;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private IterationRepository iterationRepository;

    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private Project testProject;

    @BeforeEach
    public void setUp() {
        // Clean up the repositories
        featureRepository.deleteAll();
        releaseRepository.deleteAll();
        iterationRepository.deleteAll();
        objectiveRepository.deleteAll();

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

        // Save the project to the repositories
        projectRepository.save(testProject);

        // Create and save test features
        Feature feature1 = Feature.builder()
                .featureId("feature-id-1")
                .title("Feature 1")
                .description("Description 1")
                .status("to do")
                .priority("High")
                .project(testProject)
                .isArchived(false)
                .build();
        Feature feature2 = Feature.builder()
                .featureId("feature-id-2")
                .title("Feature 2")
                .description("Description 2")
                .status("done")
                .priority("Low")
                .project(testProject)
                .isArchived(true) // Archived feature
                .build();
        featureRepository.saveAll(List.of(feature1, feature2));

        // Create and save test releases
        Release release1 = Release.builder()
                .releaseId("release-id-1")
                .name("Release 1")
                .status("Released")
                .progres(50.0f)
                .startDate(LocalDate.now().toString())
                .releaseDate(LocalDate.now().plusMonths(1).toString())
                .description("Release 1 Description")
                .project(testProject)
                .isArchived(false)
                .build();
        releaseRepository.save(release1);

        // Create and save test iterations
        Iteration iteration1 = Iteration.builder()
                .iterationId("iteration-id-1")
                .name("Iteration 1")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(1))
                .status("to do")
                .project(testProject)
                .build();
        iterationRepository.save(iteration1);

        // Create and save test objectives
        Objective objective1 = Objective.builder()
                .objectiveId("objective-id-1")
                .description("Objective 1 Description")
                .project(testProject)
                .isArchived(false)
                .build();
        objectiveRepository.save(objective1);
    }

    @Test
    public void testGetProjectStatistics() {
        ProjectStatistics statistics = dashboardService.getProjectStatistics(testProject.getProjectId());

        assertThat(statistics).isNotNull();
        assertThat(statistics.getProjectId()).isEqualTo(testProject.getProjectId());
        assertThat(statistics.getNumberOfFeatures()).isEqualTo(1); // Only non-archived feature should be counted
        assertThat(statistics.getNumberOfReleases()).isEqualTo(1);
        assertThat(statistics.getNumberOfIterations()).isEqualTo(1);
        assertThat(statistics.getNumberOfObjectives()).isEqualTo(1);
    }
}

