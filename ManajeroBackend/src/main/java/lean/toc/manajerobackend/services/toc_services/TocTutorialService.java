package lean.toc.manajerobackend.services.toc_services;

import lean.toc.manajerobackend.models.toc_models.TocTutorial;
import lean.toc.manajerobackend.repositories.toc_repositories.TocTutorialRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TocTutorialService implements ITocTutorialService{
    @Autowired
    TocTutorialRepository tocTutorialRepository;

    @Override
    public TocTutorial GetTocTutorialByAdminId(String AdminId)
    {
        TocTutorial tocTutorial1=tocTutorialRepository.findByRefUserId(AdminId);
        return tocTutorial1;
    }




}
