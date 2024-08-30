package lean.toc.manajerobackend.models.toc_models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "TocInstanceProject")
public class TocInstanceProject {

    // Champs
    @Id
    private String tocInstanceId;
    private String projectName;
    private String description;
    private Date criticalDeliveryTime;
    private Status status;
    private Double projectThroughput;
    private Date startDate;
    private Date endDate;
    private Long actualDuration;
    private Boolean used;
    //Champs (relation)
    private String refProjectId;  //Relation TocInstanceProject --- Project
    private List<Long> tocTaskIds=new ArrayList<>(); //Relation TocInstanceProject ---TocTask
    private TocConstraint tocConstraint; //Relation TocInstanceProject --- Constraint




}
