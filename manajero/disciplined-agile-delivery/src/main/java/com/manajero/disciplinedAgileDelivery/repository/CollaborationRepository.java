package com.manajero.disciplinedAgileDelivery.repository;

import com.manajero.disciplinedAgileDelivery.models.Collaboration;
import com.manajero.disciplinedAgileDelivery.models.Team;
import com.manajero.disciplinedAgileDelivery.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CollaborationRepository extends MongoRepository<Collaboration,String> {
    List<Collaboration> findByTeam_Project_ProjectId(String projectId);
    Optional<Collaboration> findByTeam_Project_ProjectIdAndUser_Email(String projectId, String email);
}
