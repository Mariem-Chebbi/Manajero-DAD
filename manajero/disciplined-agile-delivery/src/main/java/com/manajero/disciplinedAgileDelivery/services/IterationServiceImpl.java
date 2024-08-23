package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Feature;
import com.manajero.disciplinedAgileDelivery.models.Iteration;
import com.manajero.disciplinedAgileDelivery.repository.FeatureRepository;
import com.manajero.disciplinedAgileDelivery.repository.IterationRepository;
import com.manajero.disciplinedAgileDelivery.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@AllArgsConstructor
public class IterationServiceImpl implements IIterationService{
    private IterationRepository iterationRepository;
    private ProjectRepository projectRepository;
    private FeatureRepository featureRepository;

    @Override
    public Iteration addIteration(Iteration iteration, String projectId) {
        iteration.setProject(projectRepository.findById(projectId).orElse(null));
        iteration.setStatus("to do");
        return iterationRepository.save(iteration);
    }

    @Override
    public List<Iteration> getIterations(String projectId) {
        return iterationRepository.findAllByProject_ProjectId(projectId);
    }

    @Override
    public Iteration editIteration(Iteration iteration) {
        return iterationRepository.save(iteration);
    }

    @Override
    public void deleteItertaion(String id) {
        for (Feature feature : featureRepository.findAllByIteration_IterationId(id)){
            feature.setIteration(null);
            featureRepository.save(feature);
        }
        iterationRepository.deleteById(id);
    }

    @Override
    public Iteration getIterationById(String id) {
        return iterationRepository.findById(id).orElse(null);
    }

    @Override
    public Iteration startIteration(String id) {
        Iteration iteration = iterationRepository.findById(id).orElse(null);
        iteration.setStatus("current");
        return iterationRepository.save(iteration);
    }

    @Override
    public Iteration finishIteration(String id) {
        Iteration iteration = iterationRepository.findById(id).orElse(null);
        iteration.setStatus("done");
        return iterationRepository.save(iteration);
    }

    @Override
    public boolean checkStatus(String projectId) {
        for (Iteration iteration : iterationRepository.findAllByProject_ProjectId(projectId)){
            if (iteration.getStatus().equals("current")){
                return true;
            }
        }
        return false;
    }


}
