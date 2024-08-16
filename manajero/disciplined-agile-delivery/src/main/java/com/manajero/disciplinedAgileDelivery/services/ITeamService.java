package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Team;

public interface ITeamService {
    Team addTeam (Team team,String projectId);
    Team getTeamByProjectId(String projectId);
}
