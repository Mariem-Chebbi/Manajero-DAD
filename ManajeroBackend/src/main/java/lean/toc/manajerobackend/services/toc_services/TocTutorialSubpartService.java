package lean.toc.manajerobackend.services.toc_services;

import lean.toc.manajerobackend.models.toc_models.TocTutorialPart;
import lean.toc.manajerobackend.models.toc_models.TocTutorialSubpart;
import lean.toc.manajerobackend.repositories.toc_repositories.TocTutorialPartRepository;
import lean.toc.manajerobackend.repositories.toc_repositories.TocTutorialSubpartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class TocTutorialSubpartService implements ITocTutorialSubpartService{
    @Autowired
    TocTutorialSubpartRepository tocTutorialSubpartRepository;
    @Autowired
    TocTutorialPartRepository tocTutorialPartRepository;


    @Override
    public List<TocTutorialSubpart> GetAllSubpartByToctutorialPartID(String toctutorialPartID)
    {
       List<TocTutorialSubpart> tocTutorialSubpartList=tocTutorialSubpartRepository.findAllByRefTocTutorialPartId(toctutorialPartID);
       return tocTutorialSubpartList;
    }

    @Override
    public List<TocTutorialSubpart> UpdateAllSubpartsOfToctutorialPart(List<TocTutorialSubpart> TutorialSubpartList, String toctutorialPartID) {
        List<TocTutorialSubpart> tocTutorialSubpartList=tocTutorialSubpartRepository.saveAll(TutorialSubpartList);
        return tocTutorialSubpartList;

    }

    @Override
    public TocTutorialSubpart UpdateSubpartOfToctutorialPart(TocTutorialSubpart tocTutorialSubpart1) {
        TocTutorialSubpart toctutorialSubpart_updated=tocTutorialSubpartRepository.save(tocTutorialSubpart1);
        return toctutorialSubpart_updated;
    }

    @Override
    public TocTutorialSubpart AddSubpartAndAssignItToTutorialpart(TocTutorialSubpart tocTutorialSubpart1, String toctutorialPartID) {
        //Ajout
        TocTutorialSubpart toctutorialSubpart_added=tocTutorialSubpartRepository.save(tocTutorialSubpart1);
        //Affectation
        String Subpart_added_ID=toctutorialSubpart_added.getTocTutorialSubpartId();
        TocTutorialPart tutorialPart=tocTutorialPartRepository.findById(toctutorialPartID).orElse(null);
        tutorialPart.getTocTutorialSubpartIds().add(Subpart_added_ID);
        tocTutorialPartRepository.save(tutorialPart);
        return toctutorialSubpart_added;
    }

    @Override
    public void DeleteTocTutorialSubpart_ByID(String TocTutorialSubpart_Id,String TocTutorialPart_Id) {
        //Désaffecter l'élément à sa partie associée
        TocTutorialPart tocTutorialPart=tocTutorialPartRepository.findById(TocTutorialPart_Id).orElse(null);
        tocTutorialPart.getTocTutorialSubpartIds().remove(TocTutorialSubpart_Id);
        tocTutorialPartRepository.save(tocTutorialPart);
        //Supprimer l'élément
        tocTutorialSubpartRepository.deleteById(TocTutorialSubpart_Id);
        //Réordonner la liste des subparts après la suppression de l'élément
        List<TocTutorialSubpart> tocTutorialSubpartList=tocTutorialSubpartRepository.findAllByRefTocTutorialPartId(TocTutorialPart_Id); //Récupérer la liste des subparts
        tocTutorialSubpartList.sort(Comparator.comparing(TocTutorialSubpart::getOrderInList)); //trier la liste selon l'attribut orderInList
        int order=1;
        for (TocTutorialSubpart tutorialSubpart: tocTutorialSubpartList)
        {
            tutorialSubpart.setOrderInList(order);
            tocTutorialSubpartRepository.save(tutorialSubpart);
            order++;
        }

    }


}
