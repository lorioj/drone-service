package com.sample.drone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.drone.model.Drone;
import com.sample.drone.model.DroneState;

public interface DroneRepository extends JpaRepository<Drone, String> {
	Drone findBySerialNumber(String serialNumber);
	
	List<Drone> findByDroneState(DroneState droneState);
	
}
