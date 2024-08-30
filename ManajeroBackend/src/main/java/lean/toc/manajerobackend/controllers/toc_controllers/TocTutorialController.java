package lean.toc.manajerobackend.controllers.toc_controllers;

import lean.toc.manajerobackend.models.toc_models.TocTutorial;
import lean.toc.manajerobackend.services.toc_services.ITocTutorialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/TocTutorial")
public class TocTutorialController {
    @Autowired
    ITocTutorialService iTocTutorialService;

    @GetMapping("/get_toc_tutorial_by_AdminId/{AdminId}")
    @ResponseBody
    public ResponseEntity<TocTutorial> get_toc_tutorial_by_AdminId(@PathVariable String AdminId)
    {
        TocTutorial tocTutorialForAdmin=iTocTutorialService.GetTocTutorialByAdminId(AdminId);
        if(tocTutorialForAdmin != null)
        { return new ResponseEntity<>(tocTutorialForAdmin, HttpStatus.OK);  }
        else
        { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

    }


}
