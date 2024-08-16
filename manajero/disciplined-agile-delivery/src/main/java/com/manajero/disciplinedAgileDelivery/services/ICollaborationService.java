package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Collaboration;
import com.manajero.disciplinedAgileDelivery.models.Role;
import com.manajero.disciplinedAgileDelivery.models.Team;
import com.manajero.disciplinedAgileDelivery.models.User;

import java.util.List;

public interface ICollaborationService {
    List<Collaboration> getCollaborationsByProjectId(String projectId);
    Collaboration assignUserToTeam(String email, String projectId, Role role);
    void deleteCollaboration(String id);
}
