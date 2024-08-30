package lean.toc.manajerobackend.models.dad_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Iteration {
    @Id
    private String iterationId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Project project;

}
