package com.mahmoud.drone.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DroneMedicationsDto {

    private String name;
    private Double weight;

    private String code;

    private String image;

    private boolean isAvailable;
}
