package lean.toc.manajerobackend.controllers.dad_controllers;

import lean.toc.manajerobackend.models.dad_models.Tutorial;
import lean.toc.manajerobackend.services.dad_services.TutorialServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dad/tutorials")
public class TutorialController {
    private TutorialServiceImpl iTutorialService;

    @PostMapping("/add")
    public Tutorial addTutorial (@RequestBody Tutorial tutorial){
        return iTutorialService.addTutorial(tutorial);
    }

    @GetMapping("/getAll")
    public List<Tutorial> getTutorials(){
        return this.iTutorialService.getTutorials();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTutorial (@PathVariable String id){
        this.iTutorialService.deleteTutorial(id);
    }

    @PutMapping("/edit")
    public void editTutorial (@RequestBody Tutorial tutorial){
        this.iTutorialService.editTutorial(tutorial);
    }

    @PutMapping("/archive/{id}")
    public void archiveTutorial(@PathVariable String id) {
        this.iTutorialService.archiveTutorial(id);
    }

    @PutMapping("/restore/{id}")
    public void restoreTutorial(@PathVariable String id) {
        this.iTutorialService.restoreTutorial(id);
    }
}
