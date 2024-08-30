package lean.toc.manajerobackend;

import lean.toc.manajerobackend.models.dad_models.Project;
import lean.toc.manajerobackend.models.dad_models.User;
import lean.toc.manajerobackend.repositories.dad_repositories.ProjectRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.UserRepository;
import lean.toc.manajerobackend.repositories.toc_repositories.TocInstanceProjectRepository;
import lean.toc.manajerobackend.repositories.toc_repositories.TocTutorialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class ManajeroBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ManajeroBackendApplication.class, args);
    }
    // ****************  Méthodologie TOC ***************
    private final Logger logger = LoggerFactory.getLogger(ManajeroBackendApplication.class);
    @Autowired
    TocTutorialRepository tocTutorialRepository;
    @Autowired
    TocInstanceProjectRepository tocInstanceProjectRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;
    // **************** FIN Méthodologie TOC ***************

    @Override
    public void run(String... args) throws Exception {

        // ****************  Méthodologie TOC ***************
        // ****************************** Ajout d'un Tutoriel pour un admin ******************************
        /*
        -----> NB: ce code sera exécuté une et une seule fois lors de l'intégration de l'ERP pour le client,
        pour créer(instancier) un tutoriel de la méthode "Theory Of Constraint" pour ce client (dont il peut
        le personnaliser par le Manager par exemple ou par lui-même).
        */
/*
       TocTutorial tocTutorial1=new TocTutorial();
        tocTutorial1.setAdminName("Sami");
        tocTutorial1.setRefUserId("111222333444555666777");
        tocTutorialRepository.insert(tocTutorial1);


       TocTutorial tocTutorial2=new TocTutorial();
        tocTutorial2.setAdminName("Hela");
        tocTutorial2.setRefUserId("888999101010101010");
        tocTutorialRepository.insert(tocTutorial2);
*/
        // ****************************** FIN Ajout d'un Tutoriel pour un admin ******************************

        // ****************************** Ajout d'une instance TOC pour un projet ******************************
/*
           // Ajout d'une instance TOC pour un projet possédant l'id "88005566665"
       TocInstanceProject tocInstanceProject1=new TocInstanceProject();
       tocInstanceProject1.setProjectName("Cheese making");
       tocInstanceProject1.setDescription("Making Cheddar cheese with a special recipe for summer flavors, Product N435 in the factory");
        tocInstanceProject1.setRefProjectId("88005566665");
         tocInstanceProject1.setUsed(false);
         tocInstanceProjectRepository.insert(tocInstanceProject1);

        // Ajout d'une instance TOC pour un projet possédant l'id "33399911111"
        TocInstanceProject tocInstanceProject2=new TocInstanceProject();
        tocInstanceProject2.setProjectName("Chocolate making");
        tocInstanceProject2.setDescription(" Chocolate Product N852 in the factory");
        tocInstanceProject2.setRefProjectId("33399911111");
        tocInstanceProject2.setUsed(false);
        tocInstanceProjectRepository.insert(tocInstanceProject2);
*/
        // ****************************** FIN Ajout d'une instance TOC pour un projet ******************************
        // **************** FIN Méthodologie TOC ***************


                                // ****************  Méthodologie DAD ***************

        // ****************************** Ajout des Projets DAD ******************************
        Project project1 = Project.builder()
                .title("Project One")
                .status("Active")
                .statementWork("Statement of Work for Project One")
                .description("Description for Project One")
                .dateSubmitted(LocalDate.now())
                .archived(false)
                .build();

        Project project2 = Project.builder()
                .title("Project Two")
                .status("Active")
                .statementWork("Statement of Work for Project Two")
                .description("Description for Project Two")
                .dateSubmitted(LocalDate.now())
                .archived(false)
                .build();

        Project project3 = Project.builder()
                .title("Project Three")
                .status("Completed")
                .statementWork("Statement of Work for Project Three")
                .description("Description for Project Three")
                .dateSubmitted(LocalDate.now())
                .archived(true)
                .build();

        projectRepository.insert(project1);
        projectRepository.insert(project2);
        projectRepository.insert(project3);

        // ****************************** Ajout des Utilisateurs DAD ******************************
        User user1 = User.builder()
                .email("user1@example.com")
                .username("user1")
                .password("password1")
                .matricule("M001")
                .isArchived(false)
                .build();

        User user2 = User.builder()
                .email("user2@example.com")
                .username("user2")
                .password("password2")
                .matricule("M002")
                .isArchived(false)
                .build();

        User user3 = User.builder()
                .email("user3@example.com")
                .username("user3")
                .password("password3")
                .matricule("M003")
                .isArchived(true)
                .build();

        userRepository.insert(user1);
        userRepository.insert(user2);
        userRepository.insert(user3);





    }


}
