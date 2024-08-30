package lean.toc.manajerobackend.dad.service;


import lean.toc.manajerobackend.models.dad_models.Iteration;
import lean.toc.manajerobackend.models.dad_models.Project;
import lean.toc.manajerobackend.repositories.dad_repositories.IterationRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ProjectRepository;
import lean.toc.manajerobackend.services.dad_services.IterationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IterationServiceImplTests {

    @InjectMocks
    private IterationServiceImpl iterationService;

    @Mock
    private IterationRepository iterationRepository;

    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddIteration() {
        String projectId = "proj123";
        Iteration iteration = new Iteration();
        Project project = new Project();

        // Mocking repository calls
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(iterationRepository.save(any(Iteration.class))).thenReturn(iteration);

        // Calling the method under test
        Iteration result = iterationService.addIteration(iteration, projectId);

        // Verifying interactions
        verify(projectRepository).findById(projectId);
        verify(iterationRepository).save(iteration);
        assert(result != null);
    }

    @Test
    public void testGetIterations() {
        String projectId = "proj123";
        Iteration iteration1 = new Iteration();
        Iteration iteration2 = new Iteration();
        List<Iteration> iterations = List.of(iteration1, iteration2);

        // Mocking repository call
        when(iterationRepository.findAllByProject_ProjectId(projectId)).thenReturn(iterations);

        // Calling the method under test
        List<Iteration> result = iterationService.getIterations(projectId);

        // Verifying interactions
        verify(iterationRepository).findAllByProject_ProjectId(projectId);
        assert(result != null);
        assert(result.size() == 2);
    }
}

