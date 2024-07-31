package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Feature;
import com.manajero.disciplinedAgileDelivery.models.Project;

import java.util.List;

public interface IFeatureService {
    Feature addFeature(Feature feature,String id);
    Feature editFeature(Feature feature);
    List<Feature> getAllFeatures(String id);

    void deleteFeature(String id);
}
