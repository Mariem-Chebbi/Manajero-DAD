package lean.toc.manajerobackend.controllers.dad_controllers;


import lean.toc.manajerobackend.dto.dad_dto.ProjectStatistics;
import lean.toc.manajerobackend.services.dad_services.IDashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/dad/dashboard")
public class DashboardController {
    private IDashboardService dashboardService;

    @GetMapping("/statistics/{projectId}")
    public ProjectStatistics getDashboardStatistics(@PathVariable String projectId) {
        return dashboardService.getProjectStatistics(projectId);
    }
}
