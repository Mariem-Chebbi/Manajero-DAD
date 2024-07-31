package com.manajero.disciplinedAgileDelivery.controller;

import com.manajero.disciplinedAgileDelivery.models.Feature;
import com.manajero.disciplinedAgileDelivery.services.IFeatureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/features")
public class FeatureController {
    private IFeatureService iFeatureService;

    @GetMapping("/getAll/{id}")
    public List<Feature> getAllFeatures(@PathVariable String id){
        return iFeatureService.getAllFeatures(id);
    }

    @PostMapping("/add")
    public Feature addFeature (@RequestBody Feature feature,@RequestParam String idProject){
        return iFeatureService.addFeature(feature,idProject);
    }

    @PutMapping ("/edit")
    public Feature editFeature (@RequestBody Feature feature){
        return iFeatureService.editFeature(feature);
    }

    @DeleteMapping  ("/delete/{id}")
    public void deleteFeature (@PathVariable String id){
        iFeatureService.deleteFeature(id);
    }
}
