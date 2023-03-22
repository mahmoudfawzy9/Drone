package com.mahmoud.drone.controller;

import com.mahmoud.drone.entity.Drone;
import com.mahmoud.drone.entity.Medication;
import com.mahmoud.drone.request.DroneAndMedicationsRequest;
import com.mahmoud.drone.service.DroneService;
import com.mahmoud.drone.utils.ResponseStringKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drone")
public class DroneController {

    @Autowired
    DroneService droneService;

    /**The controller receives a POST request, processes it, creates a new Drone and saves it to the database,
    // and returns a resource link to the created drone. **/
    @PostMapping(value = {"/droneId"}, consumes = "application/json")
    public ResponseEntity<Drone> registerDroneWithMedications(@RequestBody DroneAndMedicationsRequest droneAndMedicationsRequest) {
        Drone drone = droneAndMedicationsRequest.getDrone();
        List<Medication> medications = droneAndMedicationsRequest.getMedications();

        Drone savedDrone = droneService.registerDroneWithMedications(drone, medications);
        return new ResponseEntity<>(savedDrone, HttpStatus.CREATED);
    }

    /**this controller returns a certain drone identified by its id
     and its associated medicationLists **/
    @GetMapping({"/{droneId}"})
    public ResponseEntity<Drone> getDrone(@PathVariable long droneId) {
        return new ResponseEntity<>(droneService.getDroneById(droneId), HttpStatus.OK);
    }

    /**Its function receives a PUT request, updates
     the Drone with the specified Id and returns the updated Drone **/
    @PutMapping(value = {"/{droneId}"}, consumes = "application/json")
    public ResponseEntity<Drone> updateDrone(@PathVariable long droneId,@RequestBody (required = false) DroneAndMedicationsRequest request) {


        if (request.getDrone() == null || request.getMedications() == null) {
            throw new IllegalArgumentException("Drone and medications are required");
        }
            Drone updatedDrone = droneService.updateDrone(request);
            return new ResponseEntity<>(updatedDrone, HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Drone with the specified Id and its associated medicationLists if any
        @DeleteMapping(value = {"/{droneId}"})
        public ResponseEntity<String> deleteDrone(@PathVariable("droneId") long droneId) {
        droneService.deleteDroneWithItsMedications(droneId);
        return new ResponseEntity<>(ResponseStringKeys.DELETED, HttpStatus.OK);
    }
}

