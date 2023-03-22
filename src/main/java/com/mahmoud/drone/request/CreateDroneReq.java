package com.mahmoud.drone.request;

import com.mahmoud.drone.enums.Model;
import com.mahmoud.drone.enums.State;
import com.mahmoud.drone.model.MedicationItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateDroneReq {

    private long id;

    private String droneName;

    private String serialNumber;

    private Double weight;

    private int batteryCapacity;

    private State state;

    private List<MedicationItems> medicationItemsList;

    private Model modelTypes;

    private boolean isAvailable;
}
