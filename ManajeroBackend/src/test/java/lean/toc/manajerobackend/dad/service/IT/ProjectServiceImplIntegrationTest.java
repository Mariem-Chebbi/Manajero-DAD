package lean.toc.manajerobackend.dad.service.IT;


import lean.toc.manajerobackend.models.dad_models.Project;
import lean.toc.manajerobackend.repositories.dad_repositories.ProjectRepository;
import lean.toc.manajerobackend.services.dad_services.ProjectServiceImpl;
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
public class ProjectServiceImplIntegrationTest {

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private ProjectRepository projectRepository;

    private Project testProject;

    @BeforeEach
    public void setUp() {
        // Clean up the repository
        projectRepository.deleteAll();

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
        projectRepository.save(testProject);
    }

    @Test
    public void testAddProject() {
        Project newProject = Project.builder()
                .projectId("new-project-id")
                .title("New Project")
                .status("Planning")
                .statementWork("New Statement")
                .description("New Project Description")
                .dateSubmitted(LocalDate.now())
                .archived(false)
                .build();

        Project savedProject = projectService.addProject(newProject);
        assertThat(savedProject).isNotNull();
        assertThat(savedProject.getProjectId()).isEqualTo("new-project-id");
        assertThat(savedProject.getTitle()).isEqualTo("New Project");
    }

    @Test
    public void testGetAllProjects() {
        List<Project> projects = projectService.getAllProject();
        assertThat(projects).isNotEmpty();
        assertThat(projects.size()).isGreaterThan(0);
    }

    @Test
    public void testDeleteProject() {
        projectService.deleteProject(testProject.getProjectId());
        Project deletedProject = projectRepository.findById(testProject.getProjectId()).orElse(null);
        assertThat(deletedProject).isNull();
    }

    @Test
    public void testGetAllProjectsEmpty() {
        projectRepository.deleteAll(); // Ensure the repository is empty
        List<Project> projects = projectService.getAllProject();
        assertThat(projects).isEmpty();
    }
}
