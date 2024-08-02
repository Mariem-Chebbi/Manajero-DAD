package com.manajero.disciplinedAgileDelivery.repository;

import com.manajero.disciplinedAgileDelivery.models.Feature;
import com.manajero.disciplinedAgileDelivery.models.Release;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FeatureRepository extends MongoRepository<Feature,String> {
    List<Feature> findAllByProject_ProjectId(String id);
    List<Feature> findAllByRelease_ReleaseId(String id);

}
