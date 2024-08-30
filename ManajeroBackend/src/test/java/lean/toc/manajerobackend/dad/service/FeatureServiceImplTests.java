package lean.toc.manajerobackend.dad.service;


import lean.toc.manajerobackend.models.dad_models.Feature;
import lean.toc.manajerobackend.models.dad_models.Project;
import lean.toc.manajerobackend.models.dad_models.Release;
import lean.toc.manajerobackend.repositories.dad_repositories.FeatureRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ProjectRepository;
import lean.toc.manajerobackend.services.dad_services.FeatureServiceImpl;
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

public class FeatureServiceImplTests {

    @Mock
    private FeatureRepository featureRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private FeatureServiceImpl featureService;

    private Project project;
    private Feature feature;
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

        feature = new Feature();
        feature.setFeatureId("1");
        feature.setTitle("Feature 1");
        feature.setDescription("Feature Description");
        feature.setStatus("To do");
        feature.setPriority("High priority");
        feature.setProject(project);
        feature.setRelease(release);
    }

    @Test
    public void addFeature_SavesAndReturnsFeature() {
        // Arrange
        when(projectRepository.findById(project.getProjectId())).thenReturn(Optional.of(project));
        when(featureRepository.save(any(Feature.class))).thenReturn(feature);

        // Act
        Feature savedFeature = featureService.addFeature(feature, project.getProjectId());

        // Assert
        assertThat(savedFeature).isNotNull();
        assertThat(savedFeature.getTitle()).isEqualTo("Feature 1");
        assertThat(savedFeature.getProject()).isEqualTo(project);
    }

    @Test
    public void editFeature_UpdatesAndReturnsFeature() {
        // Arrange
        feature.setTitle("Updated Feature");

        when(featureRepository.save(any(Feature.class))).thenReturn(feature);

        // Act
        Feature updatedFeature = featureService.editFeature(feature);

        // Assert
        assertThat(updatedFeature).isNotNull();
        assertThat(updatedFeature.getTitle()).isEqualTo("Updated Feature");
    }

    @Test
    public void getAllFeatures_ReturnsFeaturesList() {
        // Arrange
        List<Feature> features = new ArrayList<>();
        features.add(feature);

        when(featureRepository.findAllByProject_ProjectId(project.getProjectId())).thenReturn(features);

        // Act
        List<Feature> retrievedFeatures = featureService.getAllFeatures(project.getProjectId());

        // Assert
        assertThat(retrievedFeatures).isNotEmpty();
        assertThat(retrievedFeatures.size()).isEqualTo(1);
        assertThat(retrievedFeatures.get(0).getTitle()).isEqualTo("Feature 1");
    }

    @Test
    public void getFeaturesByRelease_ReturnsFeaturesList() {
        // Arrange
        List<Feature> features = new ArrayList<>();
        features.add(feature);

        when(featureRepository.findAllByRelease_ReleaseId(release.getReleaseId())).thenReturn(features);

        // Act
        List<Feature> retrievedFeatures = featureService.getFeaturesByRelease(release.getReleaseId());

        // Assert
        assertThat(retrievedFeatures).isNotEmpty();
        assertThat(retrievedFeatures.size()).isEqualTo(1);
        assertThat(retrievedFeatures.get(0).getTitle()).isEqualTo("Feature 1");
    }

    @Test
    public void deleteFeature_RemovesFeature() {
        // Act
        featureService.deleteFeature(feature.getFeatureId());

        // Assert
        verify(featureRepository, times(1)).deleteById(feature.getFeatureId());
    }
}
