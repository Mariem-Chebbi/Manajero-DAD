package lean.toc.manajerobackend.dad.service.IT;


import lean.toc.manajerobackend.models.dad_models.Feature;
import lean.toc.manajerobackend.models.dad_models.Project;
import lean.toc.manajerobackend.models.dad_models.Release;
import lean.toc.manajerobackend.repositories.dad_repositories.FeatureRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ProjectRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ReleaseRepository;
import lean.toc.manajerobackend.services.dad_services.ReleaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // Ensure you have a test profile
public class ReleaseServiceImplIntegrationTest {

    @Autowired
    private ReleaseServiceImpl releaseService;

    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private FeatureRepository featureRepository;

    private Release testRelease;
    private Project testProject;
    private Feature testFeature;

    @BeforeEach
    public void setUp() {
        // Clean up the repositories
        releaseRepository.deleteAll();
        projectRepository.deleteAll();
        featureRepository.deleteAll();

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

        // Create a test release
        testRelease = Release.builder()
                .releaseId("test-release-id")
                .name("Test Release")
                .status("Released")
                .progres(0.0f)
                .startDate("2024-01-01")
                .releaseDate("2024-06-01")
                .description("Test Release Description")
                .project(testProject)
                .isArchived(false)
                .build();
        releaseRepository.save(testRelease);

        // Create a test feature
        testFeature = Feature.builder()
                .featureId("test-feature-id")
                .title("Test Feature")
                .status("Done")
                .release(testRelease)
                .build();
        featureRepository.save(testFeature);
    }

    @Test
    public void testAddRelease() {
        Release newRelease = Release.builder()
                .releaseId("new-release-id")
                .name("New Release")
                .status("Not Released")
                .progres(0.0f)
                .startDate("2024-02-01")
                .releaseDate("2024-07-01")
                .description("New Release Description")
                .project(testProject)
                .isArchived(false)
                .build();

        Release savedRelease = releaseService.addRelease(newRelease, testProject.getProjectId());
        assertThat(savedRelease).isNotNull();
        assertThat(savedRelease.getReleaseId()).isNotNull();
        assertThat(savedRelease.getName()).isEqualTo(newRelease.getName());
    }

    @Test
    public void testEditRelease() {
        testRelease.setName("Updated Release Name");
        Release updatedRelease = releaseService.editRelease(testRelease);
        assertThat(updatedRelease).isNotNull();
        assertThat(updatedRelease.getName()).isEqualTo("Updated Release Name");
    }

    @Test
    public void testDeleteRelease() {
        releaseService.deleteRelease(testRelease.getReleaseId());
        Release deletedRelease = releaseRepository.findById(testRelease.getReleaseId()).orElse(null);
        assertThat(deletedRelease).isNull();
    }

    @Test
    public void testGetReleaseById() {
        Release retrievedRelease = releaseService.getReleaseById(testRelease.getReleaseId());
        assertThat(retrievedRelease).isNotNull();
        assertThat(retrievedRelease.getReleaseId()).isEqualTo(testRelease.getReleaseId());
    }

    @Test
    public void testCalculateReleasePredictability() {
        double predictability = releaseService.calculateReleasePredictability(testProject.getProjectId());
        assertThat(predictability).isEqualTo(100.0); // Assuming the test release is "Released" and not archived
    }

    @Test
    public void testCalculateReleaseProgress() {
        float progress = releaseService.calculateReleaseProgress(testRelease);
        assertThat(progress).isEqualTo(100.0f); // Since the test feature status is "Done"
    }

    @Test
    public void testArchiveRelease() {
        releaseService.archiveRelease(testRelease.getReleaseId());
        Release archivedRelease = releaseRepository.findById(testRelease.getReleaseId()).orElse(null);
        assertThat(archivedRelease).isNotNull();
        assertThat(archivedRelease.getIsArchived()).isTrue();
    }

    @Test
    public void testRestoreRelease() {
        releaseService.archiveRelease(testRelease.getReleaseId()); // First archive it
        releaseService.restoreRelease(testRelease.getReleaseId());
        Release restoredRelease = releaseRepository.findById(testRelease.getReleaseId()).orElse(null);
        assertThat(restoredRelease).isNotNull();
        assertThat(restoredRelease.getIsArchived()).isFalse();
    }
}

