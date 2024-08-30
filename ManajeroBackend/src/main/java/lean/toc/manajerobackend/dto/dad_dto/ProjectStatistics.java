package lean.toc.manajerobackend.dto.dad_dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectStatistics {
    private String projectId;
    private long numberOfFeatures;
    private long numberOfReleases;
    private long numberOfIterations;
    private long numberOfObjectives;
}
