package com.mahmoud.drone.repository;

import com.mahmoud.drone.entity.Drone;
import com.mahmoud.drone.entity.Medication;
import com.mahmoud.drone.response.SuccessResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    Optional<Medication> findByName(String name);

    Optional<Medication> findByCode(String code);

    SuccessResponse<Medication> deleteById(long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Medication m WHERE m.droneId = ?1")
    void deleteByDroneId(Drone droneId);
}
