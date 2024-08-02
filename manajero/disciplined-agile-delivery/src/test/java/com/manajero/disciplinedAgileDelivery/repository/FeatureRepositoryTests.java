package com.manajero.disciplinedAgileDelivery.repository;

import com.manajero.disciplinedAgileDelivery.models.Feature;
import com.manajero.disciplinedAgileDelivery.models.Project;
import com.manajero.disciplinedAgileDelivery.models.Release;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class FeatureRepositoryTests {
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ReleaseRepository releaseRepository;


    @Test
    public void FeatureRepository_Save_ReturnSavedFeature() {
        // Arrange
        Project project = new Project();
        project.setTitle("New Project");
        project.setStatus("In Progress");
        project.setStatementWork("Work Statement");
        project.setDescription("Project Description");
        project.setDateSubmitted(LocalDate.now());
        project.setArchived(false);

        Project savedProject = projectRepository.save(project);

        Release release = new Release();
        release.setName("Release 1.0");
        release.setStatus("Pending");
        release.setProgres(0.0f);
        release.setStartDate("2024-01-01");
        release.setReleaseDate("2024-06-01");
        release.setDescription("Initial Release");
        release.setProject(savedProject);

        Release savedRelease = releaseRepository.save(release);

        Feature feature = Feature.builder()
                .title("gestion user")
                .status("To do")
                .description("fonctionnalite de gestion user")
                .priority("High priority")
                .project(savedProject)
                .release(savedRelease)
                .build();

        // Act
        Feature savedFeature = featureRepository.save(feature);

        // Assert
        assertThat(savedFeature).isNotNull();
        assertThat(savedFeature.getFeatureId()).isNotNull();
        assertThat(savedFeature.getTitle()).isEqualTo("gestion user");
        assertThat(savedFeature.getStatus()).isEqualTo("To do");
        assertThat(savedFeature.getDescription()).isEqualTo("fonctionnalite de gestion user");
        assertThat(savedFeature.getPriority()).isEqualTo("High priority");
        assertThat(savedFeature.getProject()).isEqualTo(savedProject);
        assertThat(savedFeature.getRelease()).isEqualTo(savedRelease);

        // Cleanup
        featureRepository.delete(savedFeature);
        releaseRepository.delete(savedRelease);
        projectRepository.delete(savedProject);
    }

    @Test
    public void FeatureRepository_GetAll_ReturnMoreThanOneFeature() {
        // Arrange
        Project project = new Project();
        project.setTitle("New Project");
        project.setStatus("In Progress");
        project.setStatementWork("Work Statement");
        project.setDescription("Project Description");
        project.setDateSubmitted(LocalDate.now());
        project.setArchived(false);

        Project savedProject = projectRepository.save(project);

        Release release = new Release();
        release.setName("Release 1.0");
        release.setStatus("Pending");
        release.setProgres(0.0f);
        release.setStartDate("2024-01-01");
        release.setReleaseDate("2024-06-01");
        release.setDescription("Initial Release");
        release.setProject(savedProject);

        Release savedRelease = releaseRepository.save(release);

        Feature feature1 = Feature.builder()
                .title("gestion user")
                .status("To do")
                .description("fonctionnalite de gestion user")
                .priority("High priority")
                .project(savedProject)
                .release(savedRelease)
                .build();

        Feature feature2 = Feature.builder()
                .title("gestion admin")
                .status("In Progress")
                .description("fonctionnalite de gestion admin")
                .priority("Medium priority")
                .project(savedProject)
                .release(savedRelease)
                .build();

        featureRepository.save(feature1);
        featureRepository.save(feature2);

        // Act
        List<Feature> features = featureRepository.findAllByProject_ProjectId(savedProject.getProjectId());

        // Assert
        assertThat(features).isNotEmpty();
        assertThat(features.size()).isGreaterThan(1);

        // Cleanup
        featureRepository.deleteAll(features);
        releaseRepository.delete(savedRelease);
        projectRepository.delete(savedProject);
    }

    @Test
    public void FeatureRepository_DeleteFeature_RemovesFeature() {
        // Arrange
        Project project = new Project();
        project.setTitle("New Project");
        project.setStatus("In Progress");
        project.setStatementWork("Work Statement");
        project.setDescription("Project Description");
        project.setDateSubmitted(LocalDate.now());
        project.setArchived(false);

        Project savedProject = projectRepository.save(project);

        Release release = new Release();
        release.setName("Release 1.0");
        release.setStatus("Pending");
        release.setProgres(0.0f);
        release.setStartDate("2024-01-01");
        release.setReleaseDate("2024-06-01");
        release.setDescription("Initial Release");
        release.setProject(savedProject);

        Release savedRelease = releaseRepository.save(release);

        Feature feature = Feature.builder()
                .title("gestion user")
                .status("To do")
                .description("fonctionnalite de gestion user")
                .priority("High priority")
                .project(savedProject)
                .release(savedRelease)
                .build();

        Feature savedFeature = featureRepository.save(feature);

        // Act
        featureRepository.delete(savedFeature);
        Optional<Feature> deletedFeature = featureRepository.findById(savedFeature.getFeatureId());

        // Assert
        assertThat(deletedFeature).isEmpty();

        // Cleanup
        releaseRepository.delete(savedRelease);
        projectRepository.delete(savedProject);
    }

    @Test
    public void FeatureRepository_EditFeature_UpdatesFeature() {
        // Arrange
        Project project = new Project();
        project.setTitle("New Project");
        project.setStatus("In Progress");
        project.setStatementWork("Work Statement");
        project.setDescription("Project Description");
        project.setDateSubmitted(LocalDate.now());
        project.setArchived(false);

        Project savedProject = projectRepository.save(project);

        Release release = new Release();
        release.setName("Release 1.0");
        release.setStatus("Pending");
        release.setProgres(0.0f);
        release.setStartDate("2024-01-01");
        release.setReleaseDate("2024-06-01");
        release.setDescription("Initial Release");
        release.setProject(savedProject);

        Release savedRelease = releaseRepository.save(release);

        Feature feature = Feature.builder()
                .title("gestion user")
                .status("To do")
                .description("fonctionnalite de gestion user")
                .priority("High priority")
                .project(savedProject)
                .release(savedRelease)
                .build();

        Feature savedFeature = featureRepository.save(feature);

        // Act
        savedFeature.setTitle("updated title");
        savedFeature.setStatus("In Progress");
        savedFeature.setDescription("updated description");
        savedFeature.setPriority("Low priority");

        Feature updatedFeature = featureRepository.save(savedFeature);

        // Assert
        assertThat(updatedFeature).isNotNull();
        assertThat(updatedFeature.getFeatureId()).isEqualTo(savedFeature.getFeatureId());
        assertThat(updatedFeature.getTitle()).isEqualTo("updated title");
        assertThat(updatedFeature.getStatus()).isEqualTo("In Progress");
        assertThat(updatedFeature.getDescription()).isEqualTo("updated description");
        assertThat(updatedFeature.getPriority()).isEqualTo("Low priority");

        // Cleanup
        featureRepository.delete(updatedFeature);
        releaseRepository.delete(savedRelease);
        projectRepository.delete(savedProject);
    }
}
