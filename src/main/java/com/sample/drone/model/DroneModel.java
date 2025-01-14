package com.sample.drone.model;

public enum DroneModel {

	LIGHTWEIGHT(100),
	MIDDLEWEIGTH(200),
	CRUISERWEIGTH(500),
	HEAVYWEIGTH(1000);
	
	private int maxCapacity;
	
	DroneModel(int maxCapacity){
		this.maxCapacity = maxCapacity;
	}

	public int getMaxCapacity() {
		return this.maxCapacity;
	}
}
