package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Objective;

import java.util.List;

public interface IObjectiveService {
    Objective addObjectif(Objective objective,String idProject);
    Objective editObjectif(Objective objective);
    List<Objective> getAllObjectif(String idProject);
    void deleteObjectif(String id);
    void archiveObjective(String id);
    void restoreObjective(String id);
    Objective getObjectifById (String id);

}
