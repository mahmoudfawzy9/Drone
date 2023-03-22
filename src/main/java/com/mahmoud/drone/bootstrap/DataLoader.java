package com.mahmoud.drone.bootstrap;

import com.mahmoud.drone.entity.Drone;
import com.mahmoud.drone.entity.Medication;
import com.mahmoud.drone.enums.Model;
import com.mahmoud.drone.enums.State;
import com.mahmoud.drone.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**This is done to pre-populate data to stay consistent with the requirements but, actually I have manually used sql scripts for insertions**/
@Component
public class DataLoader implements CommandLineRunner {


    private final DroneRepository droneRepository;

    @Autowired
    public DataLoader(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public void run(String... args) throws Exception {



// Load reference data for Drone and Medication models
        Drone drone1 = new Drone(1L);
        drone1.setDroneName("Mahmoud");
        drone1.setWeight(150.0);
        drone1.setBatteryCapacity(100);
        drone1.setSerialNumber("SN-00000001");
        drone1.setModelTypes(Model.CRUISERWEIGHT);
        drone1.setAvailable(true);
        drone1.setState(State.IDLE);
        List<Medication> medications =  new ArrayList<>();
        //sample data medication 1
        Medication medication1 = new Medication("MED-00000001");
        medication1.setName("Medication 1");
        medication1.setWeight(120.0);
        medication1.setDroneId(drone1);
        medication1.setImage("someImageUrl 1");
        //sample data medication 2
        Medication medication2 = new Medication("MED-00000002");
        medication2.setName("Medication 2");
        medication2.setWeight(120.0);
        medication2.setImage("someImageUrl 2");
        medication2.setDroneId(drone1);

        medications.add(medication1);
        medications.add(medication2);

        drone1.setMedicationItems(medications);
        drone1.setState(State.LOADED);
        droneRepository.save(drone1);
    }
}

