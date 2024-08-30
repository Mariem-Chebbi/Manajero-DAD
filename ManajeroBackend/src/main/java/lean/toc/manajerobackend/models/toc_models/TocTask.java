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
@Document(collection = "TocTask")
public class TocTask {
    @Id
    private Long tocTasklD;
    private String taskName;
    private String taskDescription;
    private Status taskStatus;
    private Date startDate;
    private Date endDate;
    private Long duration;
    private Boolean haveToStart;
    private Boolean late;
    private Long delivrableNumber;
    private Double taskThroughtput;
    private Boolean constraint;
    private Boolean buffer;
    //Champs (relation)
    private List<Long> tocTaskIds=new ArrayList<>(); //Relation TocTask --- TocTask
    private String refTocInstanceId;  //Relation TocTask --- TocInstanceProject
    private List<String> ressourceIds=new ArrayList<>(); //Relation TocTask --- TocRessource


}
