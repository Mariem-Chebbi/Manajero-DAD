package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.dto.dad_dto.ProjectStatistics;

public interface IDashboardService {
    ProjectStatistics getProjectStatistics(String projectId);
}
