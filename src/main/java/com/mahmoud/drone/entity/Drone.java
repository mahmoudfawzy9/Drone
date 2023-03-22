package com.mahmoud.drone.entity;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mahmoud.drone.enums.Model;
import com.mahmoud.drone.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@Jacksonized
@Table(name = "drone")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "drone_name")
    private String droneName;

    @Size(min = 8, max = 200, message = "Serial Number range exceeded")
    @Column(name = "serial_number")
    private String serialNumber;


    @DecimalMax("500.0") @DecimalMin("1.0")
    @Column(name = "weight")
    private Double weight;

    @Min(value = 0 , message = "Value should be greater then equal to 0")
    @Max(value = 100 , message = "Value should be less then equal to 100")
    @Column(name = "battery_capacity",precision = 5, scale = 4)
    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    State state;

//    @ElementCollection(targetClass = Model.class)
    @CollectionTable(name = "model_types_enum", joinColumns = @JoinColumn(name = "model_types_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "model_type")
    private Model modelTypes;

    @Column(name = "is_available")
    private boolean isAvailable;

//    @ElementCollection(targetClass = Medication.class)
//    @CollectionTable(name = "medication")

@OneToMany( mappedBy = "droneId"
        , cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY
            )
@JsonIgnoreProperties("droneId")
    @Column(name = "medication_items")
    private List<Medication> medicationItems;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    public Drone(){

    }
    public Drone(Long id){
        this.id = id;
    }



//    public List<Medication> getMedications() {
//        return medicationItems;
//    }
//
//    public void setMedications(List<Medication> medications) {
//        this.medicationItems = medications;
//    }
//    public void addMedication(Medication medication) {
//        medicationItems.add(medication);
//    }
//
//    public void removeMedication(Medication medication) {
//        medicationItems.remove(medication);
//    }
}
