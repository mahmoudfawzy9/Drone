package com.mahmoud.drone.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DroneResponse {
    private String message;
    private long droneId;
    private int messageCode;
}
