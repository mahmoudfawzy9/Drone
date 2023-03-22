package com.mahmoud.drone.service;

import com.mahmoud.drone.entity.Drone;
import com.mahmoud.drone.entity.Medication;
import com.mahmoud.drone.request.CreateDroneReq;
import com.mahmoud.drone.request.DroneAndMedicationsRequest;
import com.mahmoud.drone.response.DroneResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DroneService {

    List<Drone> getDrones();

    Drone getDroneById(long id);

    Drone updateDrone(DroneAndMedicationsRequest request);

    void deleteDroneWithItsMedications(long droneId);

    Drone registerDroneWithMedications(Drone drone, List<Medication> medications);
}
