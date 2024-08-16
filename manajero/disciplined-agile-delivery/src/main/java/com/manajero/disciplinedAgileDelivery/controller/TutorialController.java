package com.manajero.disciplinedAgileDelivery.controller;

import com.manajero.disciplinedAgileDelivery.models.Tutorial;
import com.manajero.disciplinedAgileDelivery.services.ITutorialService;
import com.manajero.disciplinedAgileDelivery.services.TutorialServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/tutorials")
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
