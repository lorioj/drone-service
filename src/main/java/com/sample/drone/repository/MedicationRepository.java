package com.sample.drone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sample.drone.model.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long>{
	
//	@Query("SELECT o FROM Medication o WHERE o.drone.serialNumber = :serialNumber")
//	List<Medication> findByDrone(String serialNumber);
	
	List<Medication> findByDroneSerialNumber(String serialNumber);
	

	
}
