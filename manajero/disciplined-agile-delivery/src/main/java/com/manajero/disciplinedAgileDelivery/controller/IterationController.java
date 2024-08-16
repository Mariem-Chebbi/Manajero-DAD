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

}
