package com.manajero.disciplinedAgileDelivery.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    private String projectId;
    private String title;
    private String status;
    private String statementWork;
    private String description;
    private LocalDate dateSubmitted;
    private boolean archived;
}
