package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Iteration;
import com.manajero.disciplinedAgileDelivery.repository.IterationRepository;
import com.manajero.disciplinedAgileDelivery.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IterationServiceImpl implements IIterationService{
    private IterationRepository iterationRepository;
    private ProjectRepository projectRepository;

    @Override
    public Iteration addIteration(Iteration iteration, String projectId) {
        iteration.setProject(projectRepository.findById(projectId).orElse(null));
        return iterationRepository.save(iteration);
    }

    @Override
    public List<Iteration> getIterations(String projectId) {
        return iterationRepository.findAllByProject_ProjectId(projectId);
    }
}
