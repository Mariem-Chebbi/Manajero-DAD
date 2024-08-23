package com.manajero.disciplinedAgileDelivery.repository;

import com.manajero.disciplinedAgileDelivery.models.Feature;
import com.manajero.disciplinedAgileDelivery.models.Release;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReleaseRepository extends MongoRepository<Release,String> {
    List<Release> findAllByProject_ProjectId(String id);
    long countByProjectProjectIdAndIsArchivedFalse(String projectId);

}
