package lean.toc.manajerobackend.services.toc_services;

import lean.toc.manajerobackend.models.toc_models.TocTutorial;

public interface ITocTutorialService {

    public TocTutorial GetTocTutorialByAdminId(String AdminId);

}
