package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Iteration;

import java.util.List;

public interface IIterationService {
    Iteration addIteration (Iteration iteration,String projectId);
    List<Iteration> getIterations (String projectId);
}
