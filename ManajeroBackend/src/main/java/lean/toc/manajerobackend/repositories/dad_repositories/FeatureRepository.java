package lean.toc.manajerobackend.repositories.dad_repositories;

import lean.toc.manajerobackend.models.dad_models.Feature;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeatureRepository extends MongoRepository<Feature,String> {
    List<Feature> findAllByProject_ProjectId(String id);
    List<Feature> findAllByRelease_ReleaseId(String id);
    List<Feature> findAllByIteration_IterationId(String id);
    long countByProjectProjectIdAndIsArchivedFalse(String projectId);

}
