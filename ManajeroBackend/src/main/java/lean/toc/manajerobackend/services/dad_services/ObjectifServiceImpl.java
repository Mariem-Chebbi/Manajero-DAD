package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.models.dad_models.Objective;
import lean.toc.manajerobackend.repositories.dad_repositories.ObjectiveRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
