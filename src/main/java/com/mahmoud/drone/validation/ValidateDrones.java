package com.mahmoud.drone.validation;

import com.mahmoud.drone.model.MedicationItems;
import com.mahmoud.drone.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class ValidateDrones {

    @Autowired
    DroneRepository droneRepository;
    public boolean validateMedicationListIds(List<MedicationItems> medicationItemsList) {

        medicationItemsList.stream()
                .map(drone -> droneRepository.findBySerialNumber(drone.getCode())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Drone Id not found to register medications on " + drone.getCode())));

        return true;
    }
}
