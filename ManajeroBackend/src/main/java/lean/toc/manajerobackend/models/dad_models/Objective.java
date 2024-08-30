package lean.toc.manajerobackend.models.dad_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Objective {
    @Id
    private String objectiveId;
    private String description;
    private Boolean isAchieved;
    private Project project;
    private Boolean isArchived;

}
