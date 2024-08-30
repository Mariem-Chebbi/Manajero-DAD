package lean.toc.manajerobackend.services.toc_services;

import lean.toc.manajerobackend.models.toc_models.TOCPartType;
import lean.toc.manajerobackend.models.toc_models.TocTutorialPart;
import lean.toc.manajerobackend.models.toc_models.TocTutorialSubpart;
import lean.toc.manajerobackend.repositories.toc_repositories.TocTutorialPartRepository;
import lean.toc.manajerobackend.repositories.toc_repositories.TocTutorialSubpartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class TocTutorialPartService implements ITocTutorialPartService{
    @Autowired
    TocTutorialPartRepository tocTutorialPartRepository;
    @Autowired
    TocTutorialSubpartRepository tocTutorialSubpartRepository;

    @Override
    public TocTutorialPart GetTocTutorialPartByToctutorialIdAndPartType(String toctutorialId, TOCPartType tocPartType)
    {
       TocTutorialPart tocTutorialPart=tocTutorialPartRepository.findByRefTocTutorialIdAndTutorialPartType(toctutorialId,tocPartType).orElse(null);
       return tocTutorialPart;
    }

    @Override
    public List<TocTutorialPart> GetAllWhatIfTocTutorialPartByToctutorialId(String toctutorialId)
    {
        List<TocTutorialPart> WhatIfPartList=tocTutorialPartRepository.findAllByRefTocTutorialIdAndTutorialPartType(toctutorialId,TOCPartType.WHATIF);
       return WhatIfPartList;
    }

    @Override
    public TocTutorialPart UpdateTocTutorialPart_Summary_Why_What_Whatif(TocTutorialPart tocTutorialPart, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            tocTutorialPart.setImage(imageFile.getBytes());
        }
     return tocTutorialPartRepository.save(tocTutorialPart);
    }

    @Override
    public TocTutorialPart AddTocTutorialPart_Summary_Why_What_Whatif(TocTutorialPart tocTutorialPart, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            tocTutorialPart.setImage(imageFile.getBytes());
        }
        return tocTutorialPartRepository.insert(tocTutorialPart);
    }

    @Override
    public TocTutorialPart AddTocTutorialPart_How_Advantages_Limitations(List<TocTutorialSubpart> tocTutorialSubpartList, TocTutorialPart tocTutorialPart, MultipartFile imageFile) throws IOException {
       List<String> List_Ids=new ArrayList<>();
        if (imageFile != null && !imageFile.isEmpty()) {
            tocTutorialPart.setImage(imageFile.getBytes());
        }
        TocTutorialPart tutorialPart_added=tocTutorialPartRepository.insert(tocTutorialPart); //Ajout de la partie sans faire l'affectation de la liste des sous-parties
        //Affectation de l'id de la TutorialPart ajoutée aux éléments de la liste(des subparts)
        for (TocTutorialSubpart tocTutorialSubpart1 : tocTutorialSubpartList) {
            tocTutorialSubpart1.setRefTocTutorialPartId(tutorialPart_added.getTocTutorialPartId());
        }
        //Ajout de la liste des Subparts affectés
        List<TocTutorialSubpart> tocTutorialSubpartList_added=tocTutorialSubpartRepository.insert(tocTutorialSubpartList);
        // ----> Réccupération des ids
        for (TocTutorialSubpart tocTutorialSubpart2 : tocTutorialSubpartList_added) {
            List_Ids.add(tocTutorialSubpart2.getTocTutorialSubpartId());
        }
        //Affectation des Subparts à la tutorialPart déja ajoutée
        tutorialPart_added.setTocTutorialSubpartIds(List_Ids);
        tocTutorialPartRepository.save(tutorialPart_added); //Mise à jour de la TutorialPart
        return tutorialPart_added;
    }

    @Override
    public void DeleteTocTutorialPart_Summary_Why_What_Whatif_ByID(String TocTutorialPart_Id) {
        String TocTutorial_Id="";
        List<TocTutorialPart> WhatifPart_List=new ArrayList<>();
        TocTutorialPart tocTutorialPart=tocTutorialPartRepository.findById(TocTutorialPart_Id).orElse(null);
        if(tocTutorialPart.getTutorialPartType() != TOCPartType.WHATIF)
        {
            tocTutorialPartRepository.deleteById(TocTutorialPart_Id);
            log.info("111111111");
        }
        else
        {
         TocTutorial_Id=tocTutorialPart.getRefTocTutorialId(); //Réccupérer l'id du TocTutorial
            tocTutorialPartRepository.deleteById(TocTutorialPart_Id); //supprimer la partie WhatIf
            //Récupérer toutes les parties WhatIf du tutoriel
           WhatifPart_List=GetAllWhatIfTocTutorialPartByToctutorialId(TocTutorial_Id);
           WhatifPart_List.sort(Comparator.comparing(TocTutorialPart::getOrder));
            //Réordonner la liste après la suppression de l'élément
            int order=1;
            for (TocTutorialPart WhatifPart : WhatifPart_List)
            {
                WhatifPart.setOrder(order);
                tocTutorialPartRepository.save(WhatifPart);
                order++;
            }

        }

    }

    @Override
    public void DeleteTocTutorialPart_How_Advantages_Limitation_ByID(String TocTutorialPart_Id) {
        TocTutorialPart tocTutorialPartToDelete=tocTutorialPartRepository.findById(TocTutorialPart_Id).orElse(null);

        tocTutorialSubpartRepository.deleteAllById(tocTutorialPartToDelete.getTocTutorialSubpartIds());
        tocTutorialPartRepository.deleteById(TocTutorialPart_Id);
    }


}
