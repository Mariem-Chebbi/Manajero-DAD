package lean.toc.manajerobackend.repositories.dad_repositories;

import lean.toc.manajerobackend.models.dad_models.Iteration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IterationRepository extends MongoRepository <Iteration,String> {
    List<Iteration> findAllByProject_ProjectId(String projectId);
    long countByProjectProjectId(String projectId);

}
