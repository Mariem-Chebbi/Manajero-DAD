package com.manajero.disciplinedAgileDelivery.repository;

import com.manajero.disciplinedAgileDelivery.models.Objective;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ObjectiveRepository extends MongoRepository<Objective,String> {
    List<Objective> findAllByProject_ProjectId(String id);
}
