package lean.toc.manajerobackend.dad.service.IT;


import lean.toc.manajerobackend.models.dad_models.Tutorial;
import lean.toc.manajerobackend.repositories.dad_repositories.TutorialRepository;
import lean.toc.manajerobackend.services.dad_services.TutorialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // Ensure you have a test profile
public class TutorialServiceImplIntegrationTest {

    @Autowired
    private TutorialServiceImpl tutorialService;

    @Autowired
    private TutorialRepository tutorialRepository;

    private Tutorial testTutorial;

    @BeforeEach
    public void setUp() {
        // Clean up the repository
        tutorialRepository.deleteAll();

        // Create a test tutorial
        testTutorial = new Tutorial();
        testTutorial.setTitle("Test Tutorial");
        testTutorial.setDescription("Test Description");
        testTutorial.setContent("Test Content");
        testTutorial.setCreationDate(LocalDateTime.now());
        testTutorial.setLastUpdated(LocalDateTime.now());
        testTutorial.setIsArchived(false);
    }

    @Test
    public void testAddTutorial() {
        Tutorial savedTutorial = tutorialService.addTutorial(testTutorial);
        assertThat(savedTutorial).isNotNull();
        assertThat(savedTutorial.getTutorialId()).isNotNull();
        assertThat(savedTutorial.getTitle()).isEqualTo(testTutorial.getTitle());
    }

    @Test
    public void testGetTutorials() {
        tutorialService.addTutorial(testTutorial);
        List<Tutorial> tutorials = tutorialService.getTutorials();
        assertThat(tutorials).isNotEmpty();
        assertThat(tutorials.size()).isEqualTo(1);
    }

    @Test
    public void testEditTutorial() {
        Tutorial savedTutorial = tutorialService.addTutorial(testTutorial);
        savedTutorial.setTitle("Updated Title");
        Tutorial updatedTutorial = tutorialService.editTutorial(savedTutorial);
        assertThat(updatedTutorial).isNotNull();
        assertThat(updatedTutorial.getTitle()).isEqualTo("Updated Title");
    }

    @Test
    public void testDeleteTutorial() {
        Tutorial savedTutorial = tutorialService.addTutorial(testTutorial);
        tutorialService.deleteTutorial(savedTutorial.getTutorialId());
        assertThat(tutorialRepository.findById(savedTutorial.getTutorialId())).isEmpty();
    }

    @Test
    public void testArchiveAndRestoreTutorial() {
        Tutorial savedTutorial = tutorialService.addTutorial(testTutorial);

        // Archive the tutorial
        tutorialService.archiveTutorial(savedTutorial.getTutorialId());
        Tutorial archivedTutorial = tutorialService.getTutorials().get(0);
        assertThat(archivedTutorial).isNotNull();
        assertThat(archivedTutorial.getIsArchived()).isTrue();

        // Restore the tutorial
        tutorialService.restoreTutorial(savedTutorial.getTutorialId());
        Tutorial restoredTutorial = tutorialService.getTutorials().get(0);
        assertThat(restoredTutorial).isNotNull();
        assertThat(restoredTutorial.getIsArchived()).isFalse();
    }
}

