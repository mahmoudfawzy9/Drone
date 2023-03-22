package com.mahmoud.drone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mahmoud.drone.enums.Model;
import com.mahmoud.drone.model.LightweightDrone;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "image")
    private String image;


    @JsonIgnoreProperties("medicationItems")
    @ManyToOne
//            (cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Drone.class)
    @JoinColumn(name = "drone_id", insertable = true)
    private Drone droneId;


    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private Timestamp updatedAt;
    public Medication(String code) {
        this.code = code;
    }


    @PrePersist
    public void prePersist() {
        this.createdAt = null;
        this.updatedAt = null;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = null;
    }
    public Medication updateFrom(Medication medication) {
        this.setName(medication.getName());
        this.setDroneId(medication.getDroneId());
        return this;
    }
//    public void setDrone(Drone droneId) {
//        this.droneId = droneId;
//    }
}
