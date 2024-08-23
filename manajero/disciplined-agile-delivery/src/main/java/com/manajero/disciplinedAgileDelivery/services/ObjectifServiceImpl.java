package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Objective;
import com.manajero.disciplinedAgileDelivery.models.Release;
import com.manajero.disciplinedAgileDelivery.repository.ObjectiveRepository;
import com.manajero.disciplinedAgileDelivery.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.util.List;

@Service
@AllArgsConstructor
public class ObjectifServiceImpl implements IObjectiveService {
    private ObjectiveRepository objectiveRepository;
    private ProjectRepository projectRepository;

    @Override
    public Objective addObjectif(Objective objective, String idProject) {
        objective.setProject(projectRepository.findById(idProject).orElse(null));
        objective.setIsAchieved(false);
        objective.setIsArchived(false);
        return objectiveRepository.save(objective);
    }

    @Override
    public Objective editObjectif(Objective objective) {
        return objectiveRepository.save(objective);
    }

    @Override
    public List<Objective> getAllObjectif(String idProject) {
        return objectiveRepository.findAllByProject_ProjectId(idProject);
    }

    @Override
    public void deleteObjectif(String id) {
        objectiveRepository.deleteById(id);
    }

    @Override
    public void archiveObjective(String id) {
        Objective objective = objectiveRepository.findById(id).orElse(null);
        objective.setIsArchived(true);
        objectiveRepository.save(objective);
    }

    @Override
    public void restoreObjective(String id) {
        Objective objective = objectiveRepository.findById(id).orElse(null);
        objective.setIsArchived(false);
        objectiveRepository.save(objective);
    }

    @Override
    public Objective getObjectifById(String id) {
        return objectiveRepository.findById(id).orElse(null);
    }
}
