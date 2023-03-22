package com.mahmoud.drone.repository;

import com.mahmoud.drone.entity.Drone;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@CrossOrigin("http://localhost:4200")
@Repository
public interface DroneRepository extends JpaRepository<Drone, Long>{

    //for future use if needed : )
    Optional<Drone> findBySerialNumber(String code);

    Optional<Drone> findById(long id);
}
