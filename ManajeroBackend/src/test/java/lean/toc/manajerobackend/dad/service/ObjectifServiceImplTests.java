package lean.toc.manajerobackend.dad.service;


import lean.toc.manajerobackend.models.dad_models.Objective;
import lean.toc.manajerobackend.models.dad_models.Project;
import lean.toc.manajerobackend.repositories.dad_repositories.ObjectiveRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ProjectRepository;
import lean.toc.manajerobackend.services.dad_services.ObjectifServiceImpl;
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
public class ObjectifServiceImplTests {

    @InjectMocks
    private ObjectifServiceImpl objectifService;

    @Mock
    private ObjectiveRepository objectiveRepository;

    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddObjectif() {
        String projectId = "proj123";
        Objective objective = new Objective();
        Project project = new Project();

        // Mocking repository calls
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(objectiveRepository.save(any(Objective.class))).thenReturn(objective);

        // Calling the method under test
        Objective result = objectifService.addObjectif(objective, projectId);

        // Verifying interactions
        verify(projectRepository).findById(projectId);
        verify(objectiveRepository).save(objective);
        assert(result != null);
        assert(result.getIsAchieved() == false);
        assert(result.getIsArchived() == false);
    }

    @Test
    public void testEditObjectif() {
        Objective objective = new Objective();

        // Mocking repository call
        when(objectiveRepository.save(any(Objective.class))).thenReturn(objective);

        // Calling the method under test
        Objective result = objectifService.editObjectif(objective);

        // Verifying interactions
        verify(objectiveRepository).save(objective);
        assert(result != null);
    }

    @Test
    public void testGetAllObjectif() {
        String projectId = "proj123";
        Objective objective1 = new Objective();
        Objective objective2 = new Objective();
        List<Objective> objectives = List.of(objective1, objective2);

        // Mocking repository call
        when(objectiveRepository.findAllByProject_ProjectId(projectId)).thenReturn(objectives);

        // Calling the method under test
        List<Objective> result = objectifService.getAllObjectif(projectId);

        // Verifying interactions
        verify(objectiveRepository).findAllByProject_ProjectId(projectId);
        assert(result != null);
        assert(result.size() == 2);
    }

    @Test
    public void testDeleteObjectif() {
        String objectiveId = "obj123";

        // Calling the method under test
        objectifService.deleteObjectif(objectiveId);

        // Verifying interactions
        verify(objectiveRepository).deleteById(objectiveId);
    }

    @Test
    public void testArchiveObjective() {
        String objectiveId = "obj123";
        Objective objective = new Objective();

        // Mocking repository calls
        when(objectiveRepository.findById(objectiveId)).thenReturn(Optional.of(objective));
        when(objectiveRepository.save(any(Objective.class))).thenReturn(objective);

        // Calling the method under test
        objectifService.archiveObjective(objectiveId);

        // Verifying interactions
        verify(objectiveRepository).findById(objectiveId);
        verify(objectiveRepository).save(objective);
        assert(objective.getIsArchived() == true);
    }

    @Test
    public void testRestoreObjective() {
        String objectiveId = "obj123";
        Objective objective = new Objective();

        // Mocking repository calls
        when(objectiveRepository.findById(objectiveId)).thenReturn(Optional.of(objective));
        when(objectiveRepository.save(any(Objective.class))).thenReturn(objective);

        // Calling the method under test
        objectifService.restoreObjective(objectiveId);

        // Verifying interactions
        verify(objectiveRepository).findById(objectiveId);
        verify(objectiveRepository).save(objective);
        assert(objective.getIsArchived() == false);
    }
}
