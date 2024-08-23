package com.manajero.disciplinedAgileDelivery.controller;

import com.manajero.disciplinedAgileDelivery.dto.ProjectStatistics;
import com.manajero.disciplinedAgileDelivery.services.IDashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {
    private IDashboardService dashboardService;

    @GetMapping("/statistics/{projectId}")
    public ProjectStatistics getDashboardStatistics(@PathVariable String projectId) {
        return dashboardService.getProjectStatistics(projectId);
    }
}
