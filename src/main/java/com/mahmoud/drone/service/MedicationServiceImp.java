package com.mahmoud.drone.service;

import com.mahmoud.drone.entity.Drone;
import com.mahmoud.drone.entity.Medication;
import com.mahmoud.drone.exception.CustomServiceException;
import com.mahmoud.drone.mapper.MedicationMapper;
import com.mahmoud.drone.repository.MedicationRepository;
import com.mahmoud.drone.request.CreateMedicationReq;
import com.mahmoud.drone.request.MedicationRequestDto;
import com.mahmoud.drone.response.MedicationResponse;
import com.mahmoud.drone.response.SuccessResponse;
import com.mahmoud.drone.utils.ResponseIntegerKeys;
import com.mahmoud.drone.utils.ResponseStringKeys;
import com.mahmoud.drone.utils.SharedUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.hibernate.internal.CoreLogging.logger;

@Slf4j
@Service
public class MedicationServiceImp implements MedicationService{

    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    MedicationMapper medicationMapper;
    @Override
    public ResponseEntity<MedicationResponse> create(CreateMedicationReq createMedicationReq) {

        Optional<Medication> findByName = medicationRepository
                .findByName(createMedicationReq.getName());

        Optional<Medication> findByCode = medicationRepository
                .findByCode(createMedicationReq.getCode());

        //This means I cannot add a medication twice identified by his Patient Unique code
        if (findByName.isPresent()
                && findByCode.get().getCode().equalsIgnoreCase(createMedicationReq.getCode()))
            throw new RuntimeException();

        Medication medicationDtoToEntity = medicationMapper.mapMedicationRequestToEntity(createMedicationReq);

        Medication medicationProfile = medicationRepository.save(medicationDtoToEntity);

        medicationProfile.setCode(SharedUtils.generateCode("MED", medicationProfile.getId()));
        medicationProfile.setDroneId(new Drone(createMedicationReq.getDroneId()));
        medicationRepository.save(medicationDtoToEntity);

        return new ResponseEntity<>(MedicationResponse.builder().message("Data Saved").messageCode(201)
                .medicationId(medicationProfile.getId()).build(), HttpStatus.CREATED);

    }

    @Override
    public Medication getMedicationById(long id) {

        return medicationRepository.findById(id).get();
    }

    @Override
    public SuccessResponse<Medication> delete(long id) {
        return medicationRepository.deleteById(id);
    }

    @Override
    public SuccessResponse<Medication> update(long id, MedicationRequestDto dto) {
        Medication entity = medicationRepository.findById(id)
                .orElseThrow(() -> new CustomServiceException(ResponseStringKeys.NOT_FOUND, ResponseIntegerKeys.NOT_FOUND));
        entity.setName(dto.getName());
        entity.setWeight(dto.getWeight());
        entity.setCode(dto.getCode());
        entity.setImage(dto.getImageUrl());
        entity.setId(dto.getId());

        logger(entity.toString());
        return new SuccessResponse<>(medicationRepository.save(entity));
    }
}
