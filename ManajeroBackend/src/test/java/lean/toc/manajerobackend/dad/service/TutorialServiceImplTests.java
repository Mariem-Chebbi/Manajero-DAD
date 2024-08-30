package lean.toc.manajerobackend.dad.service;


import lean.toc.manajerobackend.models.dad_models.Tutorial;
import lean.toc.manajerobackend.repositories.dad_repositories.TutorialRepository;
import lean.toc.manajerobackend.services.dad_services.TutorialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TutorialServiceImplTests {

    @InjectMocks
    private TutorialServiceImpl tutorialService;

    @Mock
    private TutorialRepository tutorialRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTutorial() {
        Tutorial tutorial = new Tutorial();
        tutorial.setIsArchived(false);

        // Mocking repository call
        when(tutorialRepository.save(any(Tutorial.class))).thenReturn(tutorial);

        // Calling the method under test
        Tutorial result = tutorialService.addTutorial(tutorial);

        // Verifying interactions
        verify(tutorialRepository).save(tutorial);
        assert(result != null);
        assert(!result.getIsArchived());  // Verify that the tutorial is not archived
    }

    @Test
    public void testGetTutorials() {
        Tutorial tutorial1 = new Tutorial();
        Tutorial tutorial2 = new Tutorial();

        // Mocking repository call
        when(tutorialRepository.findAll()).thenReturn(Arrays.asList(tutorial1, tutorial2));

        // Calling the method under test
        List<Tutorial> result = tutorialService.getTutorials();

        // Verifying interactions
        verify(tutorialRepository).findAll();
        assert(result.size() == 2);
    }

    @Test
    public void testDeleteTutorial() {
        String tutorialId = "tutorial123";

        // Calling the method under test
        tutorialService.deleteTutorial(tutorialId);

        // Verifying interactions
        verify(tutorialRepository).deleteById(tutorialId);
    }

    @Test
    public void testEditTutorial() {
        Tutorial tutorial = new Tutorial();

        // Mocking repository call
        when(tutorialRepository.save(any(Tutorial.class))).thenReturn(tutorial);

        // Calling the method under test
        Tutorial result = tutorialService.editTutorial(tutorial);

        // Verifying interactions
        verify(tutorialRepository).save(tutorial);
        assert(result != null);
    }

    @Test
    public void testArchiveTutorial() {
        String tutorialId = "tutorial123";
        Tutorial tutorial = new Tutorial();
        tutorial.setIsArchived(false);

        // Mocking repository calls
        when(tutorialRepository.findById(eq(tutorialId))).thenReturn(Optional.of(tutorial));
        when(tutorialRepository.save(any(Tutorial.class))).thenReturn(tutorial);

        // Calling the method under test
        tutorialService.archiveTutorial(tutorialId);

        // Verifying interactions
        verify(tutorialRepository).findById(tutorialId);
        verify(tutorialRepository).save(tutorial);
        assert(tutorial.getIsArchived());
    }

    @Test
    public void testRestoreTutorial() {
        String tutorialId = "tutorial123";
        Tutorial tutorial = new Tutorial();
        tutorial.setIsArchived(true);

        // Mocking repository calls
        when(tutorialRepository.findById(eq(tutorialId))).thenReturn(Optional.of(tutorial));
        when(tutorialRepository.save(any(Tutorial.class))).thenReturn(tutorial);

        // Calling the method under test
        tutorialService.restoreTutorial(tutorialId);

        // Verifying interactions
        verify(tutorialRepository).findById(tutorialId);
        verify(tutorialRepository).save(tutorial);
        assert(!tutorial.getIsArchived());
    }
}

