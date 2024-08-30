package lean.toc.manajerobackend.repositories.dad_repositories;

import lean.toc.manajerobackend.models.dad_models.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team,String> {
    Team findTeamByProjectProjectId (String projectId);
}
