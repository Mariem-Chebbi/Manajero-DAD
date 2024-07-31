package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Feature;
import com.manajero.disciplinedAgileDelivery.repository.FeatureRepository;
import com.manajero.disciplinedAgileDelivery.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeatureService implements IFeatureService{
    private FeatureRepository featureRepository;
    private ProjectRepository projectRepository;

    @Override
    public Feature addFeature(Feature feature, String idProject) {

        feature.setProject(projectRepository.findById(idProject).orElse(null));
        return featureRepository.save(feature);
    }

    @Override
    public Feature editFeature(Feature feature) {
        return featureRepository.save(feature);
    }

    @Override
    public List<Feature> getAllFeatures(String id) {
        return featureRepository.findAllByProject_ProjectId(id);
    }

    @Override
    public void deleteFeature(String id) {
        featureRepository.deleteById(id);
    }
}
