package lean.toc.manajerobackend.dad.service.IT;


import lean.toc.manajerobackend.models.dad_models.Iteration;
import lean.toc.manajerobackend.models.dad_models.Project;
import lean.toc.manajerobackend.repositories.dad_repositories.FeatureRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.IterationRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ProjectRepository;
import lean.toc.manajerobackend.services.dad_services.IterationServiceImpl;
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
public class IterationServiceImplIntegrationTest {

    @Autowired
    private IterationServiceImpl iterationService;

    @Autowired
    private IterationRepository iterationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private FeatureRepository featureRepository;

    private Iteration testIteration;
    private Project testProject;

    @BeforeEach
    public void setUp() {
        // Clean up the repositories
        iterationRepository.deleteAll();
        projectRepository.deleteAll();
        featureRepository.deleteAll();

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
    }

    @Test
    public void testAddIteration() {
        Iteration newIteration = Iteration.builder()
                .iterationId("new-iteration-id")
                .name("New Iteration")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(2))
                .status("to do")
                .project(testProject)
                .build();

        Iteration savedIteration = iterationService.addIteration(newIteration, testProject.getProjectId());
        assertThat(savedIteration).isNotNull();
        assertThat(savedIteration.getIterationId()).isEqualTo("new-iteration-id");
        assertThat(savedIteration.getName()).isEqualTo("New Iteration");
    }

    @Test
    public void testGetIterations() {
        List<Iteration> iterations = iterationService.getIterations(testProject.getProjectId());
        assertThat(iterations).isNotEmpty();
        assertThat(iterations.size()).isGreaterThan(0);
    }

    @Test
    public void testEditIteration() {
        testIteration.setName("Updated Iteration");
        Iteration updatedIteration = iterationService.editIteration(testIteration);
        assertThat(updatedIteration).isNotNull();
        assertThat(updatedIteration.getName()).isEqualTo("Updated Iteration");
    }

    @Test
    public void testDeleteIteration() {
        iterationService.deleteItertaion(testIteration.getIterationId());
        Iteration deletedIteration = iterationRepository.findById(testIteration.getIterationId()).orElse(null);
        assertThat(deletedIteration).isNull();
    }

    @Test
    public void testStartIteration() {
        Iteration startedIteration = iterationService.startIteration(testIteration.getIterationId());
        assertThat(startedIteration).isNotNull();
        assertThat(startedIteration.getStatus()).isEqualTo("current");
    }

    @Test
    public void testFinishIteration() {
        Iteration finishedIteration = iterationService.finishIteration(testIteration.getIterationId());
        assertThat(finishedIteration).isNotNull();
        assertThat(finishedIteration.getStatus()).isEqualTo("done");
    }

    @Test
    public void testCheckStatus() {
        boolean hasCurrentIteration = iterationService.checkStatus(testProject.getProjectId());
        assertThat(hasCurrentIteration).isFalse(); // Initially, status should be "to do"

        // Start the iteration
        iterationService.startIteration(testIteration.getIterationId());

        // Check status again
        hasCurrentIteration = iterationService.checkStatus(testProject.getProjectId());
        assertThat(hasCurrentIteration).isTrue();
    }
}

