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
public class Release {
    @Id
    private String releaseId;
    private String name;
    private String status;
    private float progres;
    private String startDate;
    private String releaseDate;
    private String description;
    private Project project;
    private Boolean isArchived;

}
