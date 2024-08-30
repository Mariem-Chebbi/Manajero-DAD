package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.dto.dad_dto.ProjectStatistics;
import lean.toc.manajerobackend.repositories.dad_repositories.FeatureRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.IterationRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ObjectiveRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ReleaseRepository;
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
