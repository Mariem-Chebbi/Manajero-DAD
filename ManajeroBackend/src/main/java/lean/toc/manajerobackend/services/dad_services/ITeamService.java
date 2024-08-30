package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.models.dad_models.Team;

public interface ITeamService {
    Team addTeam (Team team, String projectId);
    Team getTeamByProjectId(String projectId);
}
