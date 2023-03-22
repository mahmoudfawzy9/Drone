package com.mahmoud.drone.controller;

import com.mahmoud.drone.entity.Medication;
import com.mahmoud.drone.request.CreateMedicationReq;
import com.mahmoud.drone.request.MedicationRequestDto;
import com.mahmoud.drone.response.MedicationResponse;
import com.mahmoud.drone.response.SuccessResponse;
import com.mahmoud.drone.service.MedicationService;
import com.mahmoud.drone.utils.ResponseIntegerKeys;
import com.mahmoud.drone.utils.ResponseStringKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/medication")
public class MedicationController {

    @Autowired
    MedicationService medicationService;
    @PostMapping(value = "/create")
    public ResponseEntity<MedicationResponse> createMedicationProfile(
            @RequestBody CreateMedicationReq createMedicationReq) {
        return medicationService.create(createMedicationReq);
    }


    @GetMapping(path = "/{medicationId}", produces = "application/json")
    public Medication getMedicationById(@PathVariable int medicationId) {
        return medicationService.getMedicationById(medicationId);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<SuccessResponse<Medication>> delete(@RequestParam("id") int id){
        return new ResponseEntity<>(medicationService.delete(id), HttpStatus.OK);
    }

    @PutMapping(value = "update")
    public SuccessResponse<Medication> updatePatientProfile(
            @RequestParam("medicationId") int medicationId, @RequestBody MedicationRequestDto dto
    ){
        return new SuccessResponse<Medication>(
                ResponseStringKeys.UPDATED,
                ResponseIntegerKeys.CREATED,
                medicationService.update(medicationId, dto).getData());

    }

}
