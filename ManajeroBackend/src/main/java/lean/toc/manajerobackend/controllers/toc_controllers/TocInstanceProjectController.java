package lean.toc.manajerobackend.controllers.toc_controllers;

import lean.toc.manajerobackend.models.toc_models.TocInstanceProject;
import lean.toc.manajerobackend.services.toc_services.ITocInstanceProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/TocInstanceProject")
public class TocInstanceProjectController {
    @Autowired
    ITocInstanceProjectService iTocInstanceProjectService;

    @GetMapping("/get_tocinstanceproject_by_project_id/{ProjectId}")
    @ResponseBody
    public ResponseEntity<TocInstanceProject> get_tocinstanceproject_by_project_id(@PathVariable String ProjectId)
    {
        TocInstanceProject tocInstanceProject_By_Project=iTocInstanceProjectService.GetTocInstanceProject_By_ProjectId(ProjectId);
        if(tocInstanceProject_By_Project != null)
        { return new ResponseEntity<>(tocInstanceProject_By_Project, HttpStatus.OK);  }
        else
        { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

    }

    @PutMapping("/update_tocinstanceproject")
    @ResponseBody
    public ResponseEntity<TocInstanceProject> update_tocinstanceproject(@RequestBody TocInstanceProject tocInstanceProject)
    {
        TocInstanceProject TocInstanceProject_Updated=iTocInstanceProjectService.UpdateTocInstanceProject(tocInstanceProject);
        return new ResponseEntity<TocInstanceProject>(TocInstanceProject_Updated,HttpStatus.OK);
    }


}
