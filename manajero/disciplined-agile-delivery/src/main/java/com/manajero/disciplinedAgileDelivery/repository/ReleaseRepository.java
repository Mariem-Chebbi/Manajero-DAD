package com.manajero.disciplinedAgileDelivery.repository;

import com.manajero.disciplinedAgileDelivery.models.Release;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReleaseRepository extends MongoRepository<Release,String> {
}
