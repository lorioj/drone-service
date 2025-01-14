package com.sample.drone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.sample.drone.model.Drone;
import com.sample.drone.model.DroneState;
import com.sample.drone.serivce.DroneService;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableScheduling
public class DroneConfig {
	
	@Autowired
	private DroneService droneService;
	

//	Simulate state transitoin
//	@Scheduled
//	public void scheduler() {
//		List<Drone> drones = droneService.findByDroneState(DroneState.IDLE);
//		OTHER LOGIC
//	}
}
