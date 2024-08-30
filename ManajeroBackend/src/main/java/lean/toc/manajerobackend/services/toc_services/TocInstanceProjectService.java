package lean.toc.manajerobackend.services.toc_services;

import lean.toc.manajerobackend.models.toc_models.Status;
import lean.toc.manajerobackend.models.toc_models.TocInstanceProject;
import lean.toc.manajerobackend.models.toc_models.TocTask;
import lean.toc.manajerobackend.repositories.toc_repositories.TocInstanceProjectRepository;
import lean.toc.manajerobackend.repositories.toc_repositories.TocTaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TocInstanceProjectService implements ITocInstanceProjectService{
    @Autowired
    TocInstanceProjectRepository tocInstanceProjectRepository;
    @Autowired
    TocTaskRepository tocTaskRepository;

    @Override
    public TocInstanceProject GetTocInstanceProject_By_ProjectId(String refProjectId1) {
        TocInstanceProject tocInstanceProject=tocInstanceProjectRepository.findByRefProjectId(refProjectId1);
        return tocInstanceProject;
    }

    @Override
    public TocInstanceProject UpdateTocInstanceProject(TocInstanceProject tocInstanceProject1) {
        TocInstanceProject tocInstanceProject_updated=tocInstanceProjectRepository.save(tocInstanceProject1);
        return tocInstanceProject_updated;
    }

    @Override
    public TocInstanceProject UpdateTocInstanceProjectAfterAddingTask(String TocInstanceProject_Id)
    {
        TocInstanceProject tocInstanceProject=tocInstanceProjectRepository.findById(TocInstanceProject_Id).orElse(null);
        List<TocTask> TocTaskProject_List=tocTaskRepository.findAllByRefTocInstanceId(TocInstanceProject_Id);
        //Mettre à jour StartDate : ---> (Explication : la date de début du projet est la date de début de la ou les premières tâches du projet)
        Date Startdate=TocTaskProject_List.stream().min(Comparator.comparing(TocTask::getStartDate)).map(TocTask::getStartDate).orElse(null);
        tocInstanceProject.setStartDate(Startdate);
        //Mettre à jour EndDate : --->(Explication : la date de fin du projet est la date de fin de la ou les dernières tâches du projet)
        Date EndDate=TocTaskProject_List.stream().max(Comparator.comparing(TocTask::getEndDate)).map(TocTask::getEndDate).orElse(null);
        tocInstanceProject.setEndDate(EndDate);
        //Mettre à jour ActualDuration = EndDate - StartDate
        Long DatediffInMillis = EndDate.getTime() - Startdate.getTime();
        Long Duration = DatediffInMillis / (1000 * 60 * 60 * 24);
        tocInstanceProject.setActualDuration(Duration);
        // ************** Mettre à jour le débit du projet : ---> (Explication : le nombre de livrable de la dernière ou les dernières taches du projet / l'ActualDuration (qui est jours) du projet )
        //Récupérer les dernières taches du projet
        List<TocTask> All_LastTasks=TocTaskProject_List.stream().filter(tocTask -> tocTask.getEndDate().equals(EndDate))
                        .collect(Collectors.toList());
        //Faire la somme des livrables
        Long Sum_DelivrableNumber=All_LastTasks.stream()
                .mapToLong(TocTask::getDelivrableNumber) // Transforme le Stream en LongStream basé sur l'attribut `delivrableNumber`
                .sum(); //Calculer la somme des valeurs
        //Calculer et ajouter le débit
        tocInstanceProject.setProjectThroughput((double)Sum_DelivrableNumber/Duration );
        // ************** FIN Mettre à jour le débit du projet

        // ************** Mettre à jour le status du projet
        TocTask tocTask_InProgress=TocTaskProject_List.stream().filter(tocTask -> tocTask.getTaskStatus().equals(Status.InProgress))
                .findFirst() // Retourne le premier résultat trouvé
                .orElse(null);
         if(tocTask_InProgress != null) //càd on a trouvé au moins une tache qui est InProgress
         { //Alors le projet est aussi InProgress
             tocInstanceProject.setStatus(Status.InProgress);
         }
         else
         {
             Boolean All_Tasks_Are_ToDo=TocTaskProject_List.stream().allMatch(tocTask -> tocTask.getTaskStatus().equals(Status.ToDo)); // Vérifie si toutes les taches ont un statut To do
             Boolean All_Tasks_Are_Complete=TocTaskProject_List.stream().allMatch(tocTask -> tocTask.getTaskStatus().equals(Status.Complete)); // Vérifie si toutes les taches ont un statut Complete
             if(All_Tasks_Are_ToDo)
             {//Alors le projet est aussi To Do
                 tocInstanceProject.setStatus(Status.ToDo);
             }
             else
             {
                 if (All_Tasks_Are_Complete)
                 {//Alors le projet est aussi Complete
                     tocInstanceProject.setStatus(Status.Complete);
                 }
                 else
                 {   tocInstanceProject.setStatus(Status.InProgress); }
             }
         }
        // ************** FIN Mettre à jour le status du projet
        //sauvegarder la TocInstanceProject
        return  tocInstanceProjectRepository.save(tocInstanceProject);
    }

}
