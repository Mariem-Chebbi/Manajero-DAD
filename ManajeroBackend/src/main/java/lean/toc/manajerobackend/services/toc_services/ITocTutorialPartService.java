package lean.toc.manajerobackend.services.toc_services;

import lean.toc.manajerobackend.models.toc_models.TOCPartType;
import lean.toc.manajerobackend.models.toc_models.TocTutorialPart;
import lean.toc.manajerobackend.models.toc_models.TocTutorialSubpart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ITocTutorialPartService {

    public TocTutorialPart GetTocTutorialPartByToctutorialIdAndPartType(String toctutorialId, TOCPartType tocPartType);

    public List<TocTutorialPart> GetAllWhatIfTocTutorialPartByToctutorialId(String toctutorialId);

    public TocTutorialPart UpdateTocTutorialPart_Summary_Why_What_Whatif(TocTutorialPart tocTutorialPart, MultipartFile imageFile) throws IOException;

    public TocTutorialPart AddTocTutorialPart_Summary_Why_What_Whatif(TocTutorialPart tocTutorialPart, MultipartFile imageFile) throws IOException;

    public TocTutorialPart AddTocTutorialPart_How_Advantages_Limitations(List<TocTutorialSubpart> tocTutorialSubpartList, TocTutorialPart tocTutorialPart, MultipartFile imageFile) throws IOException;

    public  void DeleteTocTutorialPart_Summary_Why_What_Whatif_ByID(String TocTutorialPart_Id);

    public  void DeleteTocTutorialPart_How_Advantages_Limitation_ByID(String TocTutorialPart_Id);
}
