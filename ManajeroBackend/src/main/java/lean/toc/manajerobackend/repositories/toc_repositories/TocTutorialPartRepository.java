package lean.toc.manajerobackend.repositories.toc_repositories;

import lean.toc.manajerobackend.models.toc_models.TOCPartType;
import lean.toc.manajerobackend.models.toc_models.TocTutorialPart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TocTutorialPartRepository extends MongoRepository<TocTutorialPart,String> {

    public Optional<TocTutorialPart> findByRefTocTutorialIdAndTutorialPartType(String RefTocTutorialId, TOCPartType tocPartType);

    public List<TocTutorialPart> findAllByRefTocTutorialIdAndTutorialPartType(String RefTocTutorialId, TOCPartType tocPartType);


}
