package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.models.dad_models.Tutorial;
import lean.toc.manajerobackend.repositories.dad_repositories.TutorialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TutorialServiceImpl implements ITutorialService {
    private TutorialRepository tutorialRepository;

    public Tutorial addTutorial(Tutorial tutorial) {
        tutorial.setIsArchived(false);
        return tutorialRepository.save(tutorial);
    }

    public List<Tutorial> getTutorials() {
        return tutorialRepository.findAll();
    }

    @Override
    public void deleteTutorial(String id) {
        tutorialRepository.deleteById(id);
    }

    @Override
    public Tutorial editTutorial(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @Override
    public void archiveTutorial(String id) {
        Tutorial tutorial = tutorialRepository.findById(id).orElse(null);
        tutorial.setIsArchived(true);
        tutorialRepository.save(tutorial);
    }

    @Override
    public void restoreTutorial(String id) {
        Tutorial tutorial = tutorialRepository.findById(id).orElse(null);
        tutorial.setIsArchived(false);
        tutorialRepository.save(tutorial);
    }
}
