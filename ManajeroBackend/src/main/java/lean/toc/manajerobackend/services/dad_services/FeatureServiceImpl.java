package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.dto.dad_dto.StatusPercentage;
import lean.toc.manajerobackend.models.dad_models.Feature;
import lean.toc.manajerobackend.repositories.dad_repositories.FeatureRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.IterationRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeatureServiceImpl implements IFeatureService {
    private FeatureRepository featureRepository;
    private ProjectRepository projectRepository;
    private IterationRepository iterationRepository;

    @Override
    public Feature addFeature(Feature feature, String idProject) {
        feature.setIsArchived(false);
        feature.setProject(projectRepository.findById(idProject).orElse(null));
        return featureRepository.save(feature);
    }

    @Override
    public Feature editFeature(Feature feature) {
        return featureRepository.save(feature);
    }

    @Override
    public List<Feature> getAllFeatures(String id) {
        return featureRepository.findAllByProject_ProjectId(id);
    }

    @Override
    public List<Feature> getFeaturesByRelease(String id) {
        return featureRepository.findAllByRelease_ReleaseId(id);
    }

    @Override
    public void deleteFeature(String id) {
        featureRepository.deleteById(id);
    }

    public List<StatusPercentage> calculateStatusPercentages(String projectId) {
        List<Feature> features = featureRepository.findAllByProject_ProjectId(projectId);
        if (features.isEmpty()) {
            return Collections.emptyList();
        }

        Map<String, Long> statusCount = features.stream()
                .collect(Collectors.groupingBy(Feature::getStatus, Collectors.counting()));

        double totalFeatures = features.size();
        return statusCount.entrySet().stream()
                .map(entry -> new StatusPercentage(entry.getKey(), (entry.getValue() / totalFeatures) * 100))
                .collect(Collectors.toList());
    }

    @Override
    public void assignFeaturesToIteration(List<Feature> features, String iterationId) {
        for (Feature feature: features){
            feature.setIteration(iterationRepository.findById(iterationId).orElse(null));
            featureRepository.save(feature);
        }
    }

    @Override
    public void UnassignFeaturesToIteration(Feature feature) {
            feature.setIteration(null);
            featureRepository.save(feature);
    }

    @Override
    public List<Feature> getFeaturesByIteration(String id) {
        return featureRepository.findAllByIteration_IterationId(id);
    }

    @Override
    public void archiveFeature(String id) {
        Feature feature = featureRepository.findById(id).orElse(null);
        feature.setIsArchived(true);
        featureRepository.save(feature);
    }

    @Override
    public void restoreFeature(String id) {
        Feature feature = featureRepository.findById(id).orElse(null);
        feature.setIsArchived(false);
        featureRepository.save(feature);
    }
}
