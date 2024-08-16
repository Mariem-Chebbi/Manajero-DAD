package com.manajero.disciplinedAgileDelivery.controller;

import com.manajero.disciplinedAgileDelivery.models.Feature;
import com.manajero.disciplinedAgileDelivery.models.Objective;
import com.manajero.disciplinedAgileDelivery.services.IObjectiveService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/objectives")
public class ObjectiveController {
    private IObjectiveService iObjectiveService;

    @GetMapping("/getAll/{id}")
    public List<Objective> getAllObjectives(@PathVariable String id){
        return iObjectiveService.getAllObjectif(id);
    }

    @PostMapping("/add")
    public Objective addObjective (@RequestBody Objective objective,@RequestParam String idProject){
        return iObjectiveService.addObjectif(objective,idProject);
    }

    @PutMapping ("/edit")
    public Objective editObjective (@RequestBody Objective objective){
        return iObjectiveService.editObjectif(objective);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteObjectif(@PathVariable String id){
        iObjectiveService.deleteObjectif(id);
    }

    @PutMapping("/archive/{id}")
    public void archive(@PathVariable String id) {
        this.iObjectiveService.archiveObjective(id);
    }

    @PutMapping("/restore/{id}")
    public void restore(@PathVariable String id) {
        this.iObjectiveService.restoreObjective(id);
    }
}
