package com.mahmoud.drone.mapper;

import com.mahmoud.drone.entity.Drone;
import com.mahmoud.drone.entity.Medication;
import com.mahmoud.drone.request.CreateDroneReq;
import com.mahmoud.drone.request.CreateMedicationReq;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DroneMapper {

    public Drone mapDroneRegisteringRequestToEntity(CreateDroneReq createDroneReq) {
        Drone mappedDrone = Drone.builder().id(createDroneReq.getId())
                .droneName(createDroneReq.getDroneName())
                .serialNumber(createDroneReq.getSerialNumber())
                .weight(createDroneReq.getWeight())
                .batteryCapacity(createDroneReq.getBatteryCapacity())
                .modelTypes(createDroneReq.getModelTypes())
                .state(createDroneReq.getState())
                .build();

//        List<Medication> medications = new ArrayList<>();
//        medications.add(medication);
        return mappedDrone;
    }
}
