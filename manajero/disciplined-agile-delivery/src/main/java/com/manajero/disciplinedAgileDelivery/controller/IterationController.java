package com.manajero.disciplinedAgileDelivery.controller;

import com.manajero.disciplinedAgileDelivery.models.Iteration;
import com.manajero.disciplinedAgileDelivery.repository.IterationRepository;
import com.manajero.disciplinedAgileDelivery.services.IIterationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/iterations")
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
