package lean.toc.manajerobackend.repositories.dad_repositories;

import lean.toc.manajerobackend.models.dad_models.Objective;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ObjectiveRepository extends MongoRepository<Objective,String> {
    List<Objective> findAllByProject_ProjectId(String id);
    long countByProjectProjectIdAndIsArchivedFalse(String projectId);
}
