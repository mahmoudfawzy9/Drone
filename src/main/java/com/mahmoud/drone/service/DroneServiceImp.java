package com.mahmoud.drone.service;

import com.mahmoud.drone.entity.Drone;
import com.mahmoud.drone.entity.Medication;
import com.mahmoud.drone.enums.Model;
import com.mahmoud.drone.enums.State;
import com.mahmoud.drone.exception.CustomException;

import com.mahmoud.drone.repository.DroneRepository;
import com.mahmoud.drone.repository.MedicationRepository;
import com.mahmoud.drone.request.DroneAndMedicationsRequest;

import com.mahmoud.drone.utils.ResponseIntegerKeys;
import com.mahmoud.drone.utils.SharedUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class DroneServiceImp implements DroneService {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRepository medicationRepository;


    //listing all drones just for testing purpose
    @Override
    public List<Drone> getDrones() {
        List<Drone> drones = new ArrayList<>();
        droneRepository.findAll().forEach(drones::add);
        return drones;
    }

    @Override
    public Drone getDroneById(long id) {
        return droneRepository.findById(id).get();

    }

    @Transactional
    public Drone registerDroneWithMedications(Drone drone, List<Medication> medications) {



        Drone savedDrone = null;
        for (Medication medication : medications) {
            medication.setCode(SharedUtils.generateCode("MED", medication.getId()));
            if (medication.getWeight() >= 400) {
                drone.setAvailable(false);
                drone.setState(State.RETURNING);
            }
            if (medication.getWeight() >= 1 && medication.getWeight() <= 1000 && drone.getBatteryCapacity() >= 25 && drone.getWeight() > 400 && drone.getWeight() <= 500) {
                drone.setAvailable(true);
                drone.setState(State.LOADING);
                drone.setModelTypes(Model.HEAVYWEIGHT);
                if (medication.getWeight() + drone.getWeight() > 500) {
                    throw new CustomException("Drone Weight is exceeded", ResponseIntegerKeys.EXC);
                }
            }
            if (medication.getWeight() >= 1 && medication.getWeight() <= 1000 && drone.getBatteryCapacity() >= 25 && drone.getWeight() > 300 && drone.getWeight() <= 400) {
                drone.setAvailable(true);
                drone.setState(State.LOADING);
                drone.setModelTypes(Model.CRUISERWEIGHT);
                if (medication.getWeight() + drone.getWeight() > 500) {
                    throw new CustomException("Drone Weight is exceeded", ResponseIntegerKeys.EXC);
                }
            }
            if (medication.getWeight() >= 1 && medication.getWeight() <= 1000 && drone.getBatteryCapacity() >= 25 && drone.getWeight() > 200 && drone.getWeight() <= 300) {
                drone.setAvailable(true);
                drone.setState(State.LOADING);
                drone.setModelTypes(Model.MIDDLEWEIGHT);
                if (medication.getWeight() + drone.getWeight() > 500) {
                    throw new CustomException("Drone Weight is exceeded", ResponseIntegerKeys.EXC);
                }
            }
            if (medication.getWeight() >= 1 && medication.getWeight() <= 1000 && drone.getBatteryCapacity() >= 25 && drone.getWeight() >= 100 && drone.getWeight() <= 200) {
                drone.setAvailable(true);
                drone.setState(State.LOADING);
                drone.setModelTypes(Model.LIGHTWEIGHT);
                if (medication.getWeight() + drone.getWeight() > 500) {
                    throw new CustomException("Drone Weight is exceeded", ResponseIntegerKeys.EXC);
                }
            }
            if (medication.getWeight() == 0) {
                if (medication.getId() == 0)
                    medication.setId(null);
                medication.setWeight(1.0);
                drone.setAvailable(true);
                drone.setState(State.IDLE);
            }

            if (drone.getId() == 0) {
                drone.setId(null);
            }
            if (drone.getId() != null)
                drone.setSerialNumber(SharedUtils.generateCode("SN", drone.getId()));
            drone.setState(State.LOADED);
            savedDrone = droneRepository.save(drone);
            medication.setDroneId(savedDrone);
            medicationRepository.save(medication);
        }

        return savedDrone;
    }

    @Transactional
    @Override
    public Drone updateDrone(DroneAndMedicationsRequest request) {
        Drone existingDrone = droneRepository.findById(request.getDrone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found with id " + request.getDrone().getId()));

        List<Medication> existingMedications = existingDrone.getMedicationItems();
        List<Medication> reqFromJson = request.getMedications();
        for (Medication medication : reqFromJson) {
            if (medication != null) {
                if (medication.getId() != null) {
                    Medication existingMedication = medicationRepository.findById(medication.getId()).get();

                    existingMedication.updateFrom(medication);
                }
                medication.setDroneId(existingDrone);
                medication = medicationRepository.save(medication);
                existingMedications.add(medication);
            }
        }

        return droneRepository.save(existingDrone);
    }

    //delete drone with its associated medications
    @Override
    public void deleteDroneWithItsMedications(long id) {
        // Find the parent drone by ID
        Optional<Drone> optionalParentDrone = droneRepository.findById(id);

        // If the parent drone exists, delete it and its medications
        if (optionalParentDrone.isPresent()) {
            Drone parentDrone = optionalParentDrone.get();
            medicationRepository.deleteByDroneId(parentDrone);
            droneRepository.delete(parentDrone);

        }
    }
}