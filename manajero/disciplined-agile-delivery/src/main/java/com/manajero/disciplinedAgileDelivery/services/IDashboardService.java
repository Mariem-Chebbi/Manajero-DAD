package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.dto.ProjectStatistics;


public interface IDashboardService {
    ProjectStatistics getProjectStatistics(String projectId);
}
