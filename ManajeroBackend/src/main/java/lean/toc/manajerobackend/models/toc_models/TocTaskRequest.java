package lean.toc.manajerobackend.models.toc_models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TocTaskRequest {
    private TocTask tocTask;
    private List<String> tocResourceList_Ids;
}
