package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.models.dad_models.Tutorial;

import java.util.List;

public interface ITutorialService {
    Tutorial addTutorial(Tutorial tutorial);

    List<Tutorial> getTutorials();
    void deleteTutorial(String id);
    Tutorial editTutorial (Tutorial tutorial);
    void archiveTutorial(String id);
    void restoreTutorial(String id);
}
