package com.mahmoud.drone.service;

import com.mahmoud.drone.entity.BatteryCheckHistory;
import com.mahmoud.drone.entity.Drone;
import com.mahmoud.drone.repository.BatteryCheckHistoryRepository;
import com.mahmoud.drone.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DroneBatteryService {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    BatteryCheckHistoryRepository batteryCheckHistoryRepository;

    public void checkBatteryCapacities(){
        List<Drone> drones = droneRepository.findAll();

        for(Drone drone: drones){
            int batteryCapacity = drone.getBatteryCapacity();
            String message = "Battery capacity for drone " + drone.getSerialNumber()+
                    " is " + drone.getBatteryCapacity() + "%";

            //Log the battery to check history
            BatteryCheckHistory history = new BatteryCheckHistory();
            history.setDroneName(drone.getSerialNumber());
            history.setBatteryLevel(batteryCapacity);
            history.setTimeStamp(LocalDateTime.now());
            history.setMessage(message);
            batteryCheckHistoryRepository.save(history);
        }

    }
}
