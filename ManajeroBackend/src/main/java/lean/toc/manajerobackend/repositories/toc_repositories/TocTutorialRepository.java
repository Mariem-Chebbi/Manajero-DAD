package lean.toc.manajerobackend.repositories.toc_repositories;

import lean.toc.manajerobackend.models.toc_models.TocTutorial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TocTutorialRepository extends MongoRepository<TocTutorial,String> {

    public TocTutorial findByRefUserId(String UserId);
}
