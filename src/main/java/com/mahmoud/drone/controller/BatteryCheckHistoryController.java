package com.mahmoud.drone.controller;

import com.mahmoud.drone.entity.BatteryCheckHistory;
import com.mahmoud.drone.repository.BatteryCheckHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/battery-check-history")
public class BatteryCheckHistoryController {

    @Autowired
    private BatteryCheckHistoryRepository batteryCheckHistoryRepository;

    @GetMapping
    public List<BatteryCheckHistory> listBatteryCheckHistory() {
        return batteryCheckHistoryRepository.findAll();
    }
}
