package com.manajero.disciplinedAgileDelivery.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Release {
    @Id
    private String releaseId;
    private String name;
    private String status;
    private float progres;
    private String startDate;
    private String releaseDate;
    private String description;
}
