package lean.toc.manajerobackend.repositories.toc_repositories;

import lean.toc.manajerobackend.models.toc_models.TocTask;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TocTaskRepository extends MongoRepository<TocTask,Long> {

    public List<TocTask> findAllByRefTocInstanceId(String RefTocInstanceId);
}
