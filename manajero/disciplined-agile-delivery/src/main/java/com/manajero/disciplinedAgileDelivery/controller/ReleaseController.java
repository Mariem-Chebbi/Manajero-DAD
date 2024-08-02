package com.manajero.disciplinedAgileDelivery.controller;

import com.manajero.disciplinedAgileDelivery.models.Feature;
import com.manajero.disciplinedAgileDelivery.models.Release;
import com.manajero.disciplinedAgileDelivery.services.IReleaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/releases")
@CrossOrigin("*")
public class ReleaseController {
    private IReleaseService iReleaseService;

    @PostMapping("/add")
    public Release addRelease (@RequestBody Release release,@RequestParam String idProject){
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
}
