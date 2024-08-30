package lean.toc.manajerobackend.repositories.toc_repositories;

import lean.toc.manajerobackend.models.toc_models.TocInstanceProject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TocInstanceProjectRepository extends MongoRepository<TocInstanceProject,String> {
    public TocInstanceProject findByRefProjectId(String refProjectId);

}
