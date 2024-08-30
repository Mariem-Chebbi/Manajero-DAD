package lean.toc.manajerobackend.dad.service;

import lean.toc.manajerobackend.models.dad_models.Feature;
import lean.toc.manajerobackend.models.dad_models.Project;
import lean.toc.manajerobackend.models.dad_models.Release;
import lean.toc.manajerobackend.repositories.dad_repositories.FeatureRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ProjectRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ReleaseRepository;
import lean.toc.manajerobackend.services.dad_services.ReleaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReleaseServiceImplTests {
    @Mock
    private ReleaseRepository releaseRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private FeatureRepository featureRepository;

    @InjectMocks
    private ReleaseServiceImpl releaseService;

    private Project project;
    private Release release;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        project = new Project();
        project.setProjectId("1");
        project.setTitle("New Project");
        project.setStatus("In Progress");
        project.setStatementWork("Work Statement");
        project.setDescription("Project Description");
        project.setDateSubmitted(LocalDate.now());
        project.setArchived(false);

        release = new Release();
        release.setReleaseId("1");
        release.setName("Release 1.0");
        release.setStatus("Pending");
        release.setProgres(0.0f);
        release.setStartDate("2024-01-01");
        release.setReleaseDate("2024-06-01");
        release.setDescription("Initial Release");
        release.setProject(project);
    }

    @Test
    public void getAllReleases_ReturnsReleasesList() {
        // Arrange
        List<Release> releases = new ArrayList<>();
        releases.add(release);

        when(releaseRepository.findAllByProject_ProjectId(project.getProjectId())).thenReturn(releases);

        // Act
        List<Release> retrievedReleases = releaseService.getAllReleases(project.getProjectId());

        // Assert
        assertThat(retrievedReleases).isNotEmpty();
        assertThat(retrievedReleases.size()).isEqualTo(1);
        assertThat(retrievedReleases.get(0).getName()).isEqualTo("Release 1.0");
    }

    @Test
    public void addRelease_SavesAndReturnsRelease() {
        // Arrange
        when(projectRepository.findById(project.getProjectId())).thenReturn(Optional.of(project));
        when(releaseRepository.save(any(Release.class))).thenReturn(release);

        // Act
        Release savedRelease = releaseService.addRelease(release, project.getProjectId());

        // Assert
        assertThat(savedRelease).isNotNull();
        assertThat(savedRelease.getName()).isEqualTo("Release 1.0");
        assertThat(savedRelease.getProject()).isEqualTo(project);
    }

    @Test
    public void editRelease_UpdatesAndReturnsRelease() {
        // Arrange
        release.setName("Updated Release");

        when(releaseRepository.save(any(Release.class))).thenReturn(release);

        // Act
        Release updatedRelease = releaseService.editRelease(release);

        // Assert
        assertThat(updatedRelease).isNotNull();
        assertThat(updatedRelease.getName()).isEqualTo("Updated Release");
    }

    @Test
    public void deleteRelease_RemovesRelease() {
        // Act
        releaseService.deleteRelease(release.getReleaseId());

        // Assert
        verify(releaseRepository, times(1)).deleteById(release.getReleaseId());
    }

    @Test
    public void getReleaseById_ReturnsRelease() {
        // Arrange
        when(releaseRepository.findById(release.getReleaseId())).thenReturn(Optional.of(release));

        // Act
        Release retrievedRelease = releaseService.getReleaseById(release.getReleaseId());

        // Assert
        assertThat(retrievedRelease).isNotNull();
        assertThat(retrievedRelease.getName()).isEqualTo("Release 1.0");
    }

    @Test
    public void calculateReleasePredictability_ReturnsCorrectPredictability() {
        // Arrange
        Release onTimeRelease = new Release();
        onTimeRelease.setStatus("Released");
        onTimeRelease.setIsArchived(false);

        List<Release> releases = new ArrayList<>();
        releases.add(onTimeRelease);
        releases.add(release);

        when(releaseRepository.findAllByProject_ProjectId(project.getProjectId())).thenReturn(releases);

        // Act
        double predictability = releaseService.calculateReleasePredictability(project.getProjectId());

        // Assert
        assertThat(predictability).isEqualTo(50.0);
    }

    @Test
    public void archiveRelease_SetsIsArchivedToTrue() {
        // Arrange
        when(releaseRepository.findById(release.getReleaseId())).thenReturn(Optional.of(release));

        // Act
        releaseService.archiveRelease(release.getReleaseId());

        // Assert
        assertThat(release.getIsArchived()).isTrue();
        verify(releaseRepository, times(1)).save(release);
    }

    @Test
    public void restoreRelease_SetsIsArchivedToFalse() {
        // Arrange
        release.setIsArchived(true);
        when(releaseRepository.findById(release.getReleaseId())).thenReturn(Optional.of(release));

        // Act
        releaseService.restoreRelease(release.getReleaseId());

        // Assert
        assertThat(release.getIsArchived()).isFalse();
        verify(releaseRepository, times(1)).save(release);
    }

    @Test
    public void calculateReleaseProgress_ReturnsCorrectProgress() {
        // Arrange
        Feature doneFeature = new Feature();
        doneFeature.setStatus("Done");

        List<Feature> features = new ArrayList<>();
        features.add(doneFeature);
        features.add(new Feature());

        when(featureRepository.findAllByRelease_ReleaseId(release.getReleaseId())).thenReturn(features);
        when(releaseRepository.save(any(Release.class))).thenReturn(release);

        // Act
        float progress = releaseService.calculateReleaseProgress(release);

        // Assert
        assertThat(progress).isEqualTo(50.0f);
        assertThat(release.getProgres()).isEqualTo(50.0f);
        verify(releaseRepository, times(1)).save(release);
    }
}
