package lean.toc.manajerobackend.repositories.dad_repositories;

import lean.toc.manajerobackend.models.dad_models.Release;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReleaseRepository extends MongoRepository<Release,String> {
    List<Release> findAllByProject_ProjectId(String id);
    long countByProjectProjectIdAndIsArchivedFalse(String projectId);

}
