package com.mahmoud.drone.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MedicationResponse {

    private String message;
    private long medicationId;

    private int droneId;

    private int messageCode;
}
