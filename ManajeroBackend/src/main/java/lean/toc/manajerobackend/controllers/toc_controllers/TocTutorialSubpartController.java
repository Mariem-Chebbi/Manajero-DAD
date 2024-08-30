package lean.toc.manajerobackend.controllers.toc_controllers;

import lean.toc.manajerobackend.models.toc_models.TocTutorialSubpart;
import lean.toc.manajerobackend.services.toc_services.ITocTutorialSubpartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TocTutorialSubpart")
@Slf4j
public class TocTutorialSubpartController {
    @Autowired
    ITocTutorialSubpartService iTocTutorialSubpartService;

    @GetMapping("/get_all_subparts_by_toctutorial_part_id")
    @ResponseBody
    public ResponseEntity<List<TocTutorialSubpart>> get_all_subparts_by_toctutorial_part_id(@RequestParam String tocTutorialPartID)
    {
        List<TocTutorialSubpart> AllSubpartsOfTutorialPart=iTocTutorialSubpartService.GetAllSubpartByToctutorialPartID(tocTutorialPartID);
        if(AllSubpartsOfTutorialPart != null)
        {
            return new ResponseEntity<>(AllSubpartsOfTutorialPart, HttpStatus.OK);  }
        else
        { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

    }

    @PutMapping("/update_all_subparts_of_toctutorial_part")
    @ResponseBody
    public ResponseEntity<List<TocTutorialSubpart>> update_all_subparts_of_toctutorial_part(@RequestBody List<TocTutorialSubpart> Subparts_List,@RequestParam String tocTutorialPartID)
    {
        List<TocTutorialSubpart> AllSubpartsUpdates=iTocTutorialSubpartService.UpdateAllSubpartsOfToctutorialPart(Subparts_List,tocTutorialPartID);
            return new ResponseEntity<>(AllSubpartsUpdates, HttpStatus.OK);
    }

    @PutMapping("/update_one_subpart_of_toctutorial_part")
    @ResponseBody
    public ResponseEntity<TocTutorialSubpart> update_one_subpart_of_toctutorial_part(@RequestBody TocTutorialSubpart subpart)
    {
        TocTutorialSubpart SubpartUpdated=iTocTutorialSubpartService.UpdateSubpartOfToctutorialPart(subpart);
      
      return new ResponseEntity<>(SubpartUpdated, HttpStatus.OK);
    }

    @PostMapping("/add_subpart_and_assign_it_to_tutorialpart")
    @ResponseBody
    public ResponseEntity<TocTutorialSubpart> add_subpart_and_assign_it_to_tutorialpart(@RequestBody TocTutorialSubpart subpart,@RequestParam String TocTutorialPart_ID)
    {
        TocTutorialSubpart SubpartAdded=iTocTutorialSubpartService.AddSubpartAndAssignItToTutorialpart(subpart,TocTutorialPart_ID);
        return new ResponseEntity<>(SubpartAdded, HttpStatus.OK);
    }

    @DeleteMapping("/delete_tutorial_subpart/{TocTutorialSubpart_Id}")
    @ResponseBody
    public ResponseEntity<HttpStatus> delete_tutorialpart_summary_why_what_whatif(@PathVariable String TocTutorialSubpart_Id,@RequestParam String TocTutorialPart_Id)
    {
        try {
            iTocTutorialSubpartService.DeleteTocTutorialSubpart_ByID(TocTutorialSubpart_Id,TocTutorialPart_Id);
            log.info("Deleted subpart");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        { return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  }
    }


}
