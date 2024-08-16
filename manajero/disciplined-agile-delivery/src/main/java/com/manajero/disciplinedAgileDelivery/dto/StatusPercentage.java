package com.manajero.disciplinedAgileDelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusPercentage {
    private String status;
    private double percentage;
}
