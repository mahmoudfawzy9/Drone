package com.mahmoud.drone;

import com.mahmoud.drone.entity.Drone;
import com.mahmoud.drone.entity.Medication;
import com.mahmoud.drone.enums.State;
import com.mahmoud.drone.repository.DroneRepository;
import com.mahmoud.drone.repository.MedicationRepository;
import com.mahmoud.drone.request.DroneAndMedicationsRequest;
import com.mahmoud.drone.service.DroneService;
import com.mahmoud.drone.service.DroneServiceImp;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CreateDronewithMedicationsTest {

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private DroneServiceImp droneService;

    @Mock
    DroneAndMedicationsRequest request;

    @Test
    public void testCreateDroneWithMedication() throws JSONException {
        Medication medication1 = new Medication();
        medication1.setId(1L);
        medication1.setName("Medication 1");
        medication1.setWeight(100.0);
        medication1.setImage("someImageURL");

        Medication medication2 = new Medication();
        medication2.setId(2L);
        medication2.setName("Medication 2");
        medication2.setWeight(150.0);
        medication2.setImage("someImageURL");
        List<Medication> medications = new ArrayList<>();
        medications.add(medication1);
        medications.add(medication2);

        Drone drone = new Drone();
        drone.setId(1L);
        drone.setDroneName("Drone 1");
        drone.setMedicationItems(medications);
        drone.setWeight(200.0);
        drone.setBatteryCapacity(80);

        Mockito.when(medicationRepository.save(medication1)).thenReturn(medication1);
        Mockito.when(medicationRepository.save(medication2)).thenReturn(medication2);

        Mockito.when(droneRepository.save(drone)).thenReturn(drone);

        Drone savedDrone = droneService.registerDroneWithMedications(drone, medications);

        Mockito.verify(medicationRepository, times(1)).save(medication1);
        Mockito.verify(medicationRepository, times(1)).save(medication2);
        Mockito.verify(droneRepository, times(2)).save(drone);

        assertNotNull(savedDrone);
        assertNotNull(savedDrone.getId());
        assertNotNull(savedDrone.getMedicationItems());
        assertEquals("Medication 1", savedDrone.getMedicationItems().get(0).getName(), true);
        assertEquals("Medication 2", savedDrone.getMedicationItems().get(1).getName(), true);

    }

    @Test
    public void testGetDroneByIdWithMedications() throws JSONException {
        Medication medication1 = new Medication();
        medication1.setId(1L);
        medication1.setName("Medication 1");

        Medication medication2 = new Medication();
        medication2.setId(2L);
        medication2.setName("Medication 2");

        List<Medication> medications = new ArrayList<>();
        medications.add(medication1);
        medications.add(medication2);

        Drone drone = new Drone();
        drone.setId(1L);
        drone.setDroneName("Drone 1");
        drone.setMedicationItems(medications);

        Mockito.when(droneRepository.findById(1L)).thenReturn(Optional.of(drone));

        Drone foundDrone = droneService.getDroneById(1L);

        Mockito.verify(droneRepository, times(1)).findById(1L);

        assertNotNull(foundDrone);
        assertEquals("Medication 1", foundDrone.getMedicationItems().get(0).getName(), true);
        assertEquals("Medication 2", foundDrone.getMedicationItems().get(1).getName(), true);
    }

    @Test
    public void testUpdateDroneWithMedications() throws JSONException {
        Medication medication1 = new Medication();
        medication1.setId(1L);
        medication1.setName("Medication 1");
        medication1.setImage("SomeImageUrl1");

        Medication medication2 = new Medication();
        medication2.setId(2L);
        medication2.setName("Medication 2");
        medication1.setImage("SomeImageUrl2");

        List<Medication> medications = new ArrayList<>();
        medications.add(medication1);
        medications.add(medication2);

        Drone drone = new Drone();
        drone.setId(1L);
        drone.setDroneName("Drone 1");
        drone.setState(State.IDLE);
        drone.setWeight(100.0);
        drone.setBatteryCapacity(80);
        drone.setSerialNumber("SSN-00000001");
        drone.setMedicationItems(medications);

        Mockito.when(droneRepository.findById(1L)).thenReturn(Optional.of(drone));
        Mockito.when(medicationRepository.findById(1L)).thenReturn(Optional.of(medication1));
        Mockito.when(medicationRepository.findById(2L)).thenReturn(Optional.of(medication2));
        Mockito.when(medicationRepository.save(medication1)).thenReturn(medication1);
        Mockito.when(medicationRepository.save(medication2)).thenReturn(medication2);
        Mockito.when(droneRepository.save(drone)).thenReturn(drone);

        if (request == null) {
            request.setDrone(drone);
            request.setMedications(medications);
        }
        Mockito.verify(droneRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(medicationRepository, times(1)).findById(1L);
        Mockito.verify(medicationRepository, times(1)).findById(2L);
        Mockito.verify(medicationRepository, times(1)).save(medication1);
        Mockito.verify(medicationRepository, times(1)).save(medication2);
        Mockito.verify(droneRepository, times(1)).save(drone);
        Drone updatedDrone = droneService.updateDrone(request);

        assertNotNull(updatedDrone);
        assertEquals("Drone 1", updatedDrone.getDroneName(), true);
        assertEquals("Medication 1", updatedDrone.getMedicationItems().get(0).getName(), true);
        assertEquals("Medication 2", updatedDrone.getMedicationItems().get(1).getName(), true);
    }

    @Test
    public void testDeleteParentDroneWithMedications() {
        // Create a parent drone with medications
        Drone parentDrone = new Drone();
        parentDrone.setId(1L);
        parentDrone.setDroneName("Parent Drone");
        Medication medication1 = new Medication();
        medication1.setId(1L);
        medication1.setName("Medication 1");
        medication1.setWeight(100.0);
        medication1.setImage("someImageURL");

        Medication medication2 = new Medication();
        medication2.setId(2L);
        medication2.setName("Medication 2");
        medication2.setWeight(150.0);
        medication2.setImage("someImageURL2");
        List<Medication> medications = new ArrayList<>();
        medications.add(medication1);
        medications.add(medication2);

        parentDrone.setMedicationItems(medications);

        // Mock the droneRepository to return the parent drone when findById is called
        Mockito.when(droneRepository.findById(1L)).thenReturn(Optional.of(parentDrone));

        // Call the deleteParentDroneWithMedications method with the parent drone ID
        droneService.deleteDroneWithItsMedications(1L);

        // Verify that the delete method was called on the droneRepository for the parent drone
        Mockito.verify(droneRepository, times(1)).delete(parentDrone);

        // Verify that the delete method was called on the medicationRepository for each medication in the parent drone
        for (Medication medication : medications) {
            Mockito.verify(medicationRepository, times(1)).delete(medication);
        }
    }
}


