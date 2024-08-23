package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.dto.ProjectStatistics;
import com.manajero.disciplinedAgileDelivery.repository.FeatureRepository;
import com.manajero.disciplinedAgileDelivery.repository.IterationRepository;
import com.manajero.disciplinedAgileDelivery.repository.ObjectiveRepository;
import com.manajero.disciplinedAgileDelivery.repository.ReleaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DashboardServiceImpl implements IDashboardService {
    private FeatureRepository featureRepository;
    private ReleaseRepository releaseRepository;
    private IterationRepository iterationRepository;
    private ObjectiveRepository objectiveRepository;

    @Override
    public ProjectStatistics getProjectStatistics(String projectId) {
        long numberOfFeatures = featureRepository.countByProjectProjectIdAndIsArchivedFalse(projectId);
        long numberOfReleases = releaseRepository.countByProjectProjectIdAndIsArchivedFalse(projectId);
        long numberOfObjectives = objectiveRepository.countByProjectProjectIdAndIsArchivedFalse(projectId);
        long numberOfIterations = iterationRepository.countByProjectProjectId(projectId);

        return new ProjectStatistics(projectId, numberOfFeatures, numberOfReleases, numberOfIterations, numberOfObjectives);
    }
}
