package com.manajero.disciplinedAgileDelivery.service.IT;

import com.manajero.disciplinedAgileDelivery.models.Tutorial;
import com.manajero.disciplinedAgileDelivery.repository.TutorialRepository;
import com.manajero.disciplinedAgileDelivery.services.TutorialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
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

