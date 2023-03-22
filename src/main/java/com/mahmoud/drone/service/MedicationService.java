package com.mahmoud.drone.service;

import com.mahmoud.drone.entity.Medication;
import com.mahmoud.drone.request.CreateMedicationReq;
import com.mahmoud.drone.request.MedicationRequestDto;
import com.mahmoud.drone.response.MedicationResponse;
import com.mahmoud.drone.response.SuccessResponse;
import org.springframework.http.ResponseEntity;

public interface MedicationService {

    ResponseEntity<MedicationResponse> create(CreateMedicationReq createMedicationReq);

    Medication getMedicationById(long id);

    SuccessResponse<Medication> delete(long id);

    SuccessResponse<Medication> update(long id, MedicationRequestDto dto);
}
