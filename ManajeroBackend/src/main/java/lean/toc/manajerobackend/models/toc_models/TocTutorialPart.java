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
@Document(collection = "TocTutorialPart")
public class TocTutorialPart {

    //Champs (relation)
    @Id
    private String tocTutorialPartId;
    private TOCPartType tutorialPartType;
    private Integer order; // --> réservé uniquement pour la partie WHATIF
    private String text;
    private byte[] image;
    private String title;
    //Champs (relation)
    private String refTocTutorialId; //Relation TocTutorial---TocTutorialPart
    private List<String> tocTutorialSubpartIds=new ArrayList<>(); //Relation TocTutorialPart---TocTutorialSubpart



}
