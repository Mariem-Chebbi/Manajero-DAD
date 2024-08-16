package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Tutorial;

import java.util.List;

public interface ITutorialService {
    Tutorial addTutorial(Tutorial tutorial);

    List<Tutorial> getTutorials();
    void deleteTutorial(String id);
    Tutorial editTutorial (Tutorial tutorial);
    void archiveTutorial(String id);
    void restoreTutorial(String id);
}
