package lean.toc.manajerobackend.models.toc_models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "TocTutorial")
public class TocTutorial {
    // Champs
    @Id
    private String tocTutorialId;
    private String adminName;
    //Champs (relation)
    private String refUserId; //Relation User---TocTutorial
    private List<String> tocTutorialPartIds=new ArrayList<>(); //Relation TocTutorial---TocTutorialPart **** ---> à supprimer




}
