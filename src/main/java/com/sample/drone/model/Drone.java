package com.sample.drone.model;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author John
 *
 */

@Getter
@Setter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Drone {

	@Id
	@Size(max = 100)
	private String serialNumber;

	@Enumerated(EnumType.STRING)
	private DroneModel droneModel;

	private int weightLimit;

	private int batteryCapacity;

	@Enumerated(EnumType.STRING)
	private DroneState droneState;
	
	@OneToMany
	private List<Medication> loadedMedications;
	

	public boolean canStartLoading() {
		return batteryCapacity >= 25 && droneState.equals(DroneState.IDLE);
 	}
	
	public boolean canLoadMedication(int medicationWeight) {
		return weightLimit >= medicationWeight;
	}
	
	public void reduceBattery(int percentage) {
		this.batteryCapacity = Math.max(this.batteryCapacity - percentage, 0);
	}
	
	public void resetState() {
		if(droneState.equals(DroneState.DELIVERED) || droneState.equals(DroneState.RETURNING)) {
			droneState = DroneState.IDLE;
		}
	}
		
	public int getTotalWeightOfMedication() {
		int total = 0;
		for(Medication m : loadedMedications) {
			total += m.getWeight();
		}
		return total;
	}
	
}
