package lean.toc.manajerobackend.models.toc_models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TocConstraint {

    private String idOfConstraintTask;
    private String solution;
    private List<TocBuffer> constraintBufferList=new ArrayList<>(); //Relation TocConstraint ---TocBuffer

}
