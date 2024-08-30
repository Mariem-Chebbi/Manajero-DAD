package lean.toc.manajerobackend.models.dad_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String _id;
    private String email;
    private String username;
    private String password;
    private String matricule;
    private Boolean isArchived;

}
