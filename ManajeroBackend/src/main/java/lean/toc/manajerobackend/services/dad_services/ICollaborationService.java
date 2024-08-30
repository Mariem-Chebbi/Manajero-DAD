package lean.toc.manajerobackend.services.dad_services;



import lean.toc.manajerobackend.models.dad_models.Collaboration;
import lean.toc.manajerobackend.models.dad_models.Role;

import java.util.List;

public interface ICollaborationService {
    List<Collaboration> getCollaborationsByProjectId(String projectId);
    Collaboration assignUserToTeam(String email, String projectId, Role role);
    void deleteCollaboration(String id);
}
