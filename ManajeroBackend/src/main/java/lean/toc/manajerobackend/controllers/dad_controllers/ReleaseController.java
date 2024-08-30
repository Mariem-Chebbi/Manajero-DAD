package lean.toc.manajerobackend.controllers.dad_controllers;


import lean.toc.manajerobackend.models.dad_models.Release;
import lean.toc.manajerobackend.services.dad_services.IReleaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dad/releases")
public class ReleaseController {
    private IReleaseService iReleaseService;

    @PostMapping("/add")
    public Release addRelease (@RequestBody Release release, @RequestParam String idProject){
        return iReleaseService.addRelease(release,idProject);
    }

    @PutMapping("/edit")
    public Release editRelease (@RequestBody Release release){
        return iReleaseService.editRelease(release);
    }

    @DeleteMapping ("/delete/{id}")
    public void deleteRelease (@PathVariable String id){
        iReleaseService.deleteRelease(id);
    }

    @GetMapping("/getAll/{id}")
    public List<Release> getAllReleases(@PathVariable String id){
        return iReleaseService.getAllReleases(id);
    }

    @GetMapping("/get/{id}")
    public Release getReleasesById(@PathVariable String id){
        return iReleaseService.getReleaseById(id);
    }

    @GetMapping("/predictability/{projectId}")
    public double getReleasePredictability(@PathVariable String projectId) {
        return iReleaseService.calculateReleasePredictability(projectId);
    }

    @PutMapping("/archive/{id}")
    public void archive(@PathVariable String id) {
        this.iReleaseService.archiveRelease(id);
    }

    @PutMapping("/restore/{id}")
    public void restore(@PathVariable String id) {
        this.iReleaseService.restoreRelease(id);
    }
}
