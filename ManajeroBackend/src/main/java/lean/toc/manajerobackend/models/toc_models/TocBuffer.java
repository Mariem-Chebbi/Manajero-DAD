package lean.toc.manajerobackend.models.toc_models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TocBuffer {
   // @Id
   // private String bufferId;
    private BufferType type;
    private boolean applied;
    private String idOfBufferTask;

    // NB : La relation TocConstraint ---- TocBuffer peut être elle sera changé après(en termes de modélisation)


}
