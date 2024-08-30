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
@Document(collection = "TocResource")
public class TocResource {

    @Id
    private String ressourceId;
    private RessourceType type;
    private Boolean allocated;
    private String emailForHuman;
    private String materialDesignation;
    private String equipementDesignation;
    private Boolean archived;
    //Champs (relation)
    private String refUserId;
    private List<Long> tocTaskIds=new ArrayList<>(); //Relation TocRessource ---- TocTask


}
