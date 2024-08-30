package lean.toc.manajerobackend.repositories.dad_repositories;

import lean.toc.manajerobackend.models.dad_models.Project;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProjectRepository extends MongoRepository<Project,String> {
}
