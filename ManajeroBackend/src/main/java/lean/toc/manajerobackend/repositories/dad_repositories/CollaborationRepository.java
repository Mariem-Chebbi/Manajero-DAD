package lean.toc.manajerobackend.repositories.dad_repositories;

import lean.toc.manajerobackend.models.dad_models.Collaboration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CollaborationRepository extends MongoRepository<Collaboration,String> {
    List<Collaboration> findByTeam_Project_ProjectId(String projectId);
    Optional<Collaboration> findByTeam_Project_ProjectIdAndUser_Email(String projectId, String email);
}
