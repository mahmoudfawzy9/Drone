package com.mahmoud.drone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MedicationItems {

    private String name;

    private Double weight;

    private String code;

    private String image;

    private boolean isAvailable;
}
