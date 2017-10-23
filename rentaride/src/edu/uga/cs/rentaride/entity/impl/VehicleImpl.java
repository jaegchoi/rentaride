package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.impl.Persistent;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;

public class VehicleImpl extends Persistent implements Vehicle {

	public VehicleType type;
	public String make;
	public String model;
	public int year;
	public int mileage;
	public String tag;
	public Date lastServiced;
	public VehicleStatus status;
	public VehicleCondition condition;
	public RentalLocation location;
	public List<Rental> rentalList;
	
	public VehicleImpl(){
		
	}
	
	public VehicleImpl( VehicleType type, String make, String model, int year, int mileage, String tag, Date lastServiced, 
			VehicleStatus status, VehicleCondition condition, RentalLocation location){
		
		this.type=type;
		this.make=make;
		this.model=model;
		this.year=year;
		this.mileage=mileage;
		this.tag=tag;
		this.lastServiced=lastServiced;
		this.status=status;
		this.condition=condition;
		this.location=location;
		
	}

	public VehicleType getVehicleType() {
		return type;
	}

	public void setVehicleType(VehicleType type) {
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) throws RARException{
		this.year = year;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getRegistrationTag() {
		return tag;
	}

	public void setRegistrationTag(String tag) {
		this.tag = tag;
	}

	public Date getLastServiced() {
		return lastServiced;
	}

	public void setLastServiced(Date lastServiced) {
		this.lastServiced = lastServiced;
	}

	public VehicleStatus getStatus() {
		return status;
	}

	public void setStatus(VehicleStatus status) {
		this.status = status;
	}

	public VehicleCondition getCondition() {
		return condition;
	}

	public void setCondition(VehicleCondition condition) {
		this.condition = condition;
	}

	public RentalLocation getRentalLocation() {
		return location;
	}

	public void setRentalLocation(RentalLocation location) {
		this.location = location;
	}


	@Override
	public List<Rental> getRentals() throws RARException{
		// TODO Auto-generated method stub
		if(rentalList==null){
			if(isPersistent()){
				Rental r=new RentalImpl();
				r.setVehicle(this);
				rentalList=getPersistenceLayer().restoreRental(r);
			}
		}
		return rentalList;
	}
	
	
	
	
}


