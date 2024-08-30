package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.models.dad_models.Iteration;

import java.util.List;

public interface IIterationService {
    Iteration addIteration(Iteration iteration, String projectId);

    List<Iteration> getIterations(String projectId);

    Iteration editIteration(Iteration iteration);

    void deleteItertaion(String id);

    Iteration getIterationById(String id);

    Iteration startIteration(String id);

    Iteration finishIteration(String id);

    boolean checkStatus(String projectId);
}
