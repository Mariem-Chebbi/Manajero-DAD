package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Team;
import com.manajero.disciplinedAgileDelivery.repository.TeamRepository;
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
