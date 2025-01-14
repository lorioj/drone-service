package com.sample.drone.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.drone.model.Drone;
import com.sample.drone.model.DroneState;
import com.sample.drone.model.Medication;
import com.sample.drone.repository.DroneRepository;
import com.sample.drone.repository.MedicationRepository;

import jakarta.transaction.Transactional;

@Service
public class DroneService {

	@Autowired
	private DroneRepository droneRepository;

	@Autowired
	private MedicationRepository medicationRepository;

	@Transactional
	public Drone registerDrone(Drone drone) {
		return droneRepository.save(drone);
	}

	@Transactional
	public Drone loadDroneWithMedication(String serialNumber, Medication medication) {
		Drone drone = droneRepository.findBySerialNumber(serialNumber);
		if (!drone.canStartLoading()) {
			throw new RuntimeException("Drone cannot load due to low battery or wrong state");
		}

		if (!drone.canLoadMedication(medication.getWeight())) {
			throw new RuntimeException("Medication exceeds drone weigth limit.");
		}

		int totalWeightDroneMedication = drone.getTotalWeightOfMedication() + medication.getWeight();
		if (drone.getWeightLimit() < totalWeightDroneMedication) {
			throw new RuntimeException("Medication exceeds drone weigth limit.");
		}

		drone.getLoadedMedications().add(medication);
		medicationRepository.save(medication);
		drone.setDroneState(DroneState.LOADED);

		return droneRepository.save(drone);

	}
	

	public List<Medication> getLoadeMedications(String serialNumber) {
		return medicationRepository.findByDroneSerialNumber(serialNumber);
	}

	public boolean isDroneAvailableForLoading(String serialNumber) {
		Drone drone = droneRepository.findBySerialNumber(serialNumber);
		return drone.canStartLoading();
	}

	public Drone completeDelivery(String serialNumber) {
		Drone drone = droneRepository.findBySerialNumber(serialNumber);
		
		if(!drone.getDroneState().equals(DroneState.LOADED)) {
			throw new RuntimeException("Drone must be loaded before completing delivery");
		}
		
		drone.reduceBattery(10); // Reduce battery by 10%
		drone.setDroneState(DroneState.DELIVERED);
		return drone;
	}
	
	
	public int getBatteryInformation(String serialNumber) {
		Drone drone = droneRepository.findBySerialNumber(serialNumber);
		return drone.getBatteryCapacity();
	}
	
	public Drone findBySerialNumber(String serialNumber) {
		return droneRepository.findBySerialNumber(serialNumber);
	}

	public List<Drone> findByDroneState(DroneState droneState) {
		return droneRepository.findByDroneState(droneState);
	}

	
}
