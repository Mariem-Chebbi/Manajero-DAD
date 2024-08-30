package lean.toc.manajerobackend.services.dad_services;



import lean.toc.manajerobackend.dto.dad_dto.StatusPercentage;
import lean.toc.manajerobackend.models.dad_models.Feature;

import java.util.List;

public interface IFeatureService {
    Feature addFeature(Feature feature, String id);
    Feature editFeature(Feature feature);
    List<Feature> getAllFeatures(String id);
    List<Feature> getFeaturesByRelease(String id);
    void deleteFeature(String id);
    List<StatusPercentage> calculateStatusPercentages(String projectId);
    void assignFeaturesToIteration (List<Feature> features, String iterationId);
    void UnassignFeaturesToIteration (Feature feature);
    List<Feature> getFeaturesByIteration(String id);
    void archiveFeature(String id);
    void restoreFeature(String id);

}
