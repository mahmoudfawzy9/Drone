package com.mahmoud.drone.scheduler;

import com.mahmoud.drone.service.DroneBatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;;

@Component
public class DroneBatteryScheduler {

    @Autowired
    DroneBatteryService droneBatteryService;

    /** Ideally I schedule the drones battery capacity daily **/
    @Scheduled(fixedRate = 86400000)
    public void checkBatteryLevels(){
        droneBatteryService.checkBatteryCapacities();
    }
}
