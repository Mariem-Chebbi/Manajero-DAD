package lean.toc.manajerobackend.models.toc_models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "TocTutorialSubpart")
public class TocTutorialSubpart {

    //Champs (relation)
    @Id
    private String tocTutorialSubpartId;
    private String title;
    private String content;
    private Integer orderInList;
    //Champs (relation)
    private String refTocTutorialPartId;  //Relation TocTutorialPart---TocTutorialSubpart



}
