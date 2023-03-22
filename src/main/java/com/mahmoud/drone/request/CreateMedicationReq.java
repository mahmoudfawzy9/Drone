package com.mahmoud.drone.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.LinkedList;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateMedicationReq {

   private long droneId;

   private String name;

   private Double weight;

    private String code;

    private String image;

    }






