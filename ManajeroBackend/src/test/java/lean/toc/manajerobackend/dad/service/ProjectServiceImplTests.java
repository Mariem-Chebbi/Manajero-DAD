package lean.toc.manajerobackend.dad.service;


import lean.toc.manajerobackend.models.dad_models.Project;
import lean.toc.manajerobackend.repositories.dad_repositories.ProjectRepository;
import lean.toc.manajerobackend.services.dad_services.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProjectServiceImplTests {

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProject() {
        Project project = new Project();

        // Mocking repository call
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        // Calling the method under test
        Project result = projectService.addProject(project);

        // Verifying interactions
        verify(projectRepository).save(project);
        assert(result != null);
    }

    @Test
    public void testGetAllProject() {
        Project project1 = new Project();
        Project project2 = new Project();
        List<Project> projects = List.of(project1, project2);

        // Mocking repository call
        when(projectRepository.findAll()).thenReturn(projects);

        // Calling the method under test
        List<Project> result = projectService.getAllProject();

        // Verifying interactions
        verify(projectRepository).findAll();
        assert(result != null);
        assert(result.size() == 2);
    }

    @Test
    public void testDeleteProject() {
        String projectId = "proj123";

        // Calling the method under test
        projectService.deleteProject(projectId);

        // Verifying interactions
        verify(projectRepository).deleteById(projectId);
    }
}

