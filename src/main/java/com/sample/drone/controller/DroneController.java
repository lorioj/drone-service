package com.sample.drone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.drone.model.Drone;
import com.sample.drone.model.Medication;
import com.sample.drone.serivce.DroneService;

@RestController
@RequestMapping(path = "/api")
public class DroneController {
	
	@Autowired
	private DroneService droneService;
	
	
	
	@PostMapping("/register")
	public Drone saveTimeEntry(@RequestBody Drone drone) {
		return droneService.registerDrone(drone);
	}
	
	@PostMapping("/load/{serialNumber}")
	public Drone loadDroneWithMedicaiton(@PathVariable String serialNumber, @RequestBody Medication medication) {
		return droneService.loadDroneWithMedication(serialNumber, medication);
	}
	
	@GetMapping("/loaded/{serialNumber}")
	public List<Medication> getLoadedMedications(@PathVariable String serialNumber){
		return droneService.getLoadeMedications(serialNumber);
	}
	@GetMapping("/available/{serialNumber}")
	public boolean isDroneAvailableForLoading(@PathVariable String serialNumber) {
		return droneService.isDroneAvailableForLoading(serialNumber);
	}
	
	@PostMapping
	public Drone completeDelivery(@PathVariable String serialNumber) {
		return droneService.completeDelivery(serialNumber);
	}
	
	@GetMapping("/battery-information/{serialNumber}")
	public int checkBatteryInformation(@PathVariable String serialNumber) {
		return droneService.getBatteryInformation(serialNumber);
	}
	
	
	
	
	@GetMapping("/test")
	public String test() {
		return "Hello word";
	}
}
