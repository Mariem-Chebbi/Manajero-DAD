package lean.toc.manajerobackend.controllers.dad_controllers;


import lean.toc.manajerobackend.models.dad_models.Iteration;
import lean.toc.manajerobackend.services.dad_services.IIterationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dad/iterations")
public class IterationController {
    private IIterationService iIterationService;

    @GetMapping("/getAll/{id}")
    public List<Iteration> getAllIteration(@PathVariable String id){
        return iIterationService.getIterations(id);
    }

    @PostMapping("/add")
    public Iteration addIteration (@RequestBody Iteration iteration, @RequestParam String idProject){
        return iIterationService.addIteration(iteration,idProject);
    }
    @PutMapping("/edit")
    public Iteration editIteration (@RequestBody Iteration iteration){
        return iIterationService.editIteration(iteration);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteIteration(@PathVariable String id){
         iIterationService.deleteItertaion(id);
    }

    @GetMapping("/get/{id}")
    public Iteration getIterationById(@PathVariable String id){
        return iIterationService.getIterationById(id);
    }

    @PutMapping("/start/{id}")
    public void startIteration(@PathVariable String id){
        iIterationService.startIteration(id);
    }

    @PutMapping("/finish/{id}")
    public void finishIteration(@PathVariable String id){
        iIterationService.finishIteration(id);
    }

    @GetMapping("/checkStatus/{id}")
    public boolean checkStatus(@PathVariable String id){
        return iIterationService.checkStatus(id);
    }
}
