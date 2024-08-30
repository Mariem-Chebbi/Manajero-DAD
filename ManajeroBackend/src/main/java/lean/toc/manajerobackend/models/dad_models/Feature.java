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
public class Feature {
    @Id
    private String featureId;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Project project;
    private Release release;
    private User user;
    private Iteration iteration;
    private Objective objective;
    private Boolean isArchived;
}
