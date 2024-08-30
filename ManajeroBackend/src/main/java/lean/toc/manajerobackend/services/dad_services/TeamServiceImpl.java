package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.models.dad_models.Team;
import lean.toc.manajerobackend.repositories.dad_repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements ITeamService {
    private TeamRepository teamRepository;

    @Override
    public Team addTeam(Team team, String projectId) {
        return teamRepository.save(team);
    }

    @Override
    public Team getTeamByProjectId(String projectId) {
        return teamRepository.findTeamByProjectProjectId(projectId);
    }
}
