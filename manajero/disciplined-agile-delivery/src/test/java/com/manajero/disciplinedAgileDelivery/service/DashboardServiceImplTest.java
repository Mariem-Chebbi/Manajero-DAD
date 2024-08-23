package com.manajero.disciplinedAgileDelivery.service;

import com.manajero.disciplinedAgileDelivery.dto.ProjectStatistics;
import com.manajero.disciplinedAgileDelivery.repository.FeatureRepository;
import com.manajero.disciplinedAgileDelivery.repository.IterationRepository;
import com.manajero.disciplinedAgileDelivery.repository.ObjectiveRepository;
import com.manajero.disciplinedAgileDelivery.repository.ReleaseRepository;
import com.manajero.disciplinedAgileDelivery.services.DashboardServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DashboardServiceImplTest {

    @Mock
    private FeatureRepository featureRepository;

    @Mock
    private ReleaseRepository releaseRepository;

    @Mock
    private IterationRepository iterationRepository;

    @Mock
    private ObjectiveRepository objectiveRepository;

    @InjectMocks
    private DashboardServiceImpl dashboardService;

    public DashboardServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProjectStatistics() {
        // Arrange
        String projectId = "project123";
        long numberOfFeatures = 10;
        long numberOfReleases = 5;
        long numberOfObjectives = 7;
        long numberOfIterations = 3;

        when(featureRepository.countByProjectProjectIdAndIsArchivedFalse(projectId)).thenReturn(numberOfFeatures);
        when(releaseRepository.countByProjectProjectIdAndIsArchivedFalse(projectId)).thenReturn(numberOfReleases);
        when(objectiveRepository.countByProjectProjectIdAndIsArchivedFalse(projectId)).thenReturn(numberOfObjectives);
        when(iterationRepository.countByProjectProjectId(projectId)).thenReturn(numberOfIterations);

        // Act
        ProjectStatistics result = dashboardService.getProjectStatistics(projectId);

        // Assert
        assertEquals(projectId, result.getProjectId());
        assertEquals(numberOfFeatures, result.getNumberOfFeatures());
        assertEquals(numberOfReleases, result.getNumberOfReleases());
        assertEquals(numberOfObjectives, result.getNumberOfObjectives());
        assertEquals(numberOfIterations, result.getNumberOfIterations());
    }
}

