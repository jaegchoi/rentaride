package edu.uga.cs.rentaride.entity.impl;

import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;

public class RentalLocationImpl 
	extends Persistent
	implements RentalLocation {

	private String name;
	private String address;
	private int capacity;
	private List<Reservation> reservationList;
	private List<Vehicle> vehicleList;

	public RentalLocationImpl() {
		name = null;
		address = null;
		capacity = -1;
	}
	
	public RentalLocationImpl(String name, String address, int capacity) {
		this.name = name;
		this.address = address;
		this.capacity = capacity;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) throws RARException {
		if (name == null) {
			throw new RARException("Name must be unique and not null");
		}
		else {
			this.name = name;
		}
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public void setCapacity(int capacity) throws RARException {
		if(capacity < 0) {
			throw new RARException("Capacity must be positive value");
		}
		else {
			this.capacity = capacity;
		}
	}

	@Override
	public List<Reservation> getReservations() {
		if(reservationList == null) {
			if (isPersistent()) {
				Reservation reservation = new ReservationImpl();
				reservationList = getPersistenceLayer().restoreReservation(reservation);
			}
		}
		return reservationList;
	}

	@Override
	public List<Vehicle> getVehicles() {
		if(vehicleList == null) {
			if (isPersistent()) {
				Vehicle vehicle = new VehicleImpl();
				vehicleList = getPersistenceLayer().restoreVehicle(vehicle);
			}
		}
		return vehicleList;
	}

}
