package lean.toc.manajerobackend.models.dad_models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutorial {
    @Id
    private String tutorialId;
    private String title;
    private String description;
    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdated;
    private Boolean isArchived;

}
