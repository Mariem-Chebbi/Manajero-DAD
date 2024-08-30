package lean.toc.manajerobackend.services.dad_services;



import lean.toc.manajerobackend.models.dad_models.Objective;

import java.util.List;

public interface IObjectiveService {
    Objective addObjectif(Objective objective, String idProject);
    Objective editObjectif(Objective objective);
    List<Objective> getAllObjectif(String idProject);
    void deleteObjectif(String id);
    void archiveObjective(String id);
    void restoreObjective(String id);
    Objective getObjectifById (String id);

}
