package com.mahmoud.drone.mapper;

import com.mahmoud.drone.entity.Medication;
import com.mahmoud.drone.request.CreateMedicationReq;
import org.springframework.stereotype.Component;

@Component
public class MedicationMapper {

    public Medication mapMedicationRequestToEntity(CreateMedicationReq createMedicationReq) {
        Medication medicationItem = Medication.builder().name(createMedicationReq.getName())
                .code(createMedicationReq.getCode())
                .weight(createMedicationReq.getWeight())
                .image(createMedicationReq.getImage())
                .build();

        return medicationItem;
    }
}
