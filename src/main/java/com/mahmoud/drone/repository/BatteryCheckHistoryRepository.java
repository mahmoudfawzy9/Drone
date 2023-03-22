package com.mahmoud.drone.repository;

import com.mahmoud.drone.entity.BatteryCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryCheckHistoryRepository extends JpaRepository<BatteryCheckHistory, Integer> {
}
