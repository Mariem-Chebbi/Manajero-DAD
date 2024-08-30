package lean.toc.manajerobackend.repositories.toc_repositories;

import lean.toc.manajerobackend.models.toc_models.TocTutorialSubpart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TocTutorialSubpartRepository extends MongoRepository<TocTutorialSubpart,String> {

    public List<TocTutorialSubpart> findAllByRefTocTutorialPartId(String tocTutorialPartId);
}
