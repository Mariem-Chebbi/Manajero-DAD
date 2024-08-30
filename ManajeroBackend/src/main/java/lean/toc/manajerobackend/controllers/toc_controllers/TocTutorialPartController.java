package lean.toc.manajerobackend.controllers.toc_controllers;

import lean.toc.manajerobackend.models.toc_models.TOCPartType;
import lean.toc.manajerobackend.models.toc_models.TocTutorialPart;
import lean.toc.manajerobackend.models.toc_models.TocTutorialSubpart;
import lean.toc.manajerobackend.services.toc_services.ITocTutorialPartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
//     /TocTutorialPart/get_toctutorialpart_by_toctutorial_id_and_type

@RestController
@RequestMapping("/TocTutorialPart")
@Slf4j
public class TocTutorialPartController {
    @Autowired
    ITocTutorialPartService iTocTutorialPartService;

    @GetMapping("/get_toctutorialpart_by_toctutorial_id_and_type")
    @ResponseBody
    public ResponseEntity<TocTutorialPart> get_toctutorialpart_by_toctutorial_id_and_type(@RequestParam String toctutorialId, @RequestParam TOCPartType tocPartType)
    {
        TocTutorialPart tocTutorialPartOfTutorial=iTocTutorialPartService.GetTocTutorialPartByToctutorialIdAndPartType(toctutorialId,tocPartType);
        if(tocTutorialPartOfTutorial != null)
        {
            return new ResponseEntity<>(tocTutorialPartOfTutorial, HttpStatus.OK);  }
        else
        { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

    }

    @GetMapping("/get_all_whatif_parts_by_toctutorial_id")
    @ResponseBody
    public ResponseEntity<List<TocTutorialPart>> get_all_whatif_parts_by_toctutorial_id(@RequestParam String toctutorialId)
    {
        List<TocTutorialPart> AllWhatIfParts=iTocTutorialPartService.GetAllWhatIfTocTutorialPartByToctutorialId(toctutorialId);
        if(AllWhatIfParts != null)
        {
            return new ResponseEntity<>(AllWhatIfParts, HttpStatus.OK);  }
        else
        { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

    }

    @PutMapping("/update_tutorialpart_summary_why_what_whatif")
    @ResponseBody
    public ResponseEntity<TocTutorialPart> update_tutorialpart_summary_why_what_whatif(@RequestPart TocTutorialPart tocTutorialPart,@RequestPart MultipartFile image_file)throws IOException
    {
    TocTutorialPart TutorialPart_Updated=iTocTutorialPartService.UpdateTocTutorialPart_Summary_Why_What_Whatif(tocTutorialPart,image_file);
    return new ResponseEntity<TocTutorialPart>(TutorialPart_Updated,HttpStatus.OK);
    }

    @PostMapping("/add_tutorialpart_summary_why_what_whatif")
    @ResponseBody
    public ResponseEntity<TocTutorialPart> add_tutorialpart_summary_why_what_whatif(@RequestPart TocTutorialPart tocTutorialPart,@RequestPart MultipartFile image_file)throws IOException
    {
        TocTutorialPart TutorialPart_Added=iTocTutorialPartService.AddTocTutorialPart_Summary_Why_What_Whatif(tocTutorialPart,image_file);
        return new ResponseEntity<TocTutorialPart>(TutorialPart_Added,HttpStatus.OK);
    }

    @PostMapping("/add_tutorialpart_how_advantages_limitation")
    @ResponseBody
    public ResponseEntity<TocTutorialPart> add_tutorialpart_how_advantages_limitation(@RequestPart List<TocTutorialSubpart> tocTutorialSubpartList, @RequestPart TocTutorialPart tocTutorialPart, @RequestPart MultipartFile image_file)throws IOException
    {
        TocTutorialPart TutorialPart_Added=iTocTutorialPartService.AddTocTutorialPart_How_Advantages_Limitations(tocTutorialSubpartList,tocTutorialPart,image_file);
        return new ResponseEntity<TocTutorialPart>(TutorialPart_Added,HttpStatus.OK);
    }

@DeleteMapping("/delete_tutorialpart_summary_why_what_whatif/{TocTutorialPart_Id}")
@ResponseBody
public ResponseEntity<HttpStatus> delete_tutorialpart_summary_why_what_whatif(@PathVariable String TocTutorialPart_Id)
{
    try {
        iTocTutorialPartService.DeleteTocTutorialPart_Summary_Why_What_Whatif_ByID(TocTutorialPart_Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    catch (Exception e)
    { return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  }
}

    @DeleteMapping("/delete_tutorialpart_How_Advantages_limitation/{TocTutorialPart_Id}")
    @ResponseBody
    public ResponseEntity<HttpStatus> delete_tutorialpart_How_Advantages_limitation(@PathVariable String TocTutorialPart_Id)
    {
        try {
            iTocTutorialPartService.DeleteTocTutorialPart_How_Advantages_Limitation_ByID(TocTutorialPart_Id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        { return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  }
    }




}
