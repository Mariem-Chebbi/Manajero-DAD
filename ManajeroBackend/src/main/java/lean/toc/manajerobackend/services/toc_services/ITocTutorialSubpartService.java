package lean.toc.manajerobackend.services.toc_services;

import lean.toc.manajerobackend.models.toc_models.TocTutorialSubpart;

import java.util.List;

public interface ITocTutorialSubpartService {

    public List<TocTutorialSubpart> GetAllSubpartByToctutorialPartID(String toctutorialPartID);

    public List<TocTutorialSubpart> UpdateAllSubpartsOfToctutorialPart(List<TocTutorialSubpart> TutorialSubpartList, String toctutorialPartID);

    public TocTutorialSubpart UpdateSubpartOfToctutorialPart(TocTutorialSubpart tocTutorialSubpart1);

    public TocTutorialSubpart AddSubpartAndAssignItToTutorialpart(TocTutorialSubpart tocTutorialSubpart1,String toctutorialPartID);

    public  void DeleteTocTutorialSubpart_ByID(String TocTutorialSubpart_Id,String TocTutorialPart_Id);

}
