package com.mahmoud.drone.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicationRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private String code;

    private Double weight;

    private String imageUrl;


}