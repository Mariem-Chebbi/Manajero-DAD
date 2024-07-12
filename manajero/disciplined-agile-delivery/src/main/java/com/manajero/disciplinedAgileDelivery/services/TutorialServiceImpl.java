package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Tutorial;
import com.manajero.disciplinedAgileDelivery.repository.TutorialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TutorialServiceImpl implements ITutorialService {
    private TutorialRepository tutorialRepository;

    public Tutorial addTutorial(Tutorial tutorial) {
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
}
