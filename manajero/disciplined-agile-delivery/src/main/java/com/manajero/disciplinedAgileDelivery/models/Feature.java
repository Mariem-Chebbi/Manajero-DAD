package com.manajero.disciplinedAgileDelivery.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feature {
    @Id
    private String featureId;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Project project;



}
