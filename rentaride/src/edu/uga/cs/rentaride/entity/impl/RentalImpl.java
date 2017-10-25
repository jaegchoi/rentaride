package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.persistence.impl.Persistent;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;

public class RentalImpl extends Persistent implements Rental {

	
	public Date pickupTime;
    public Reservation reservation;
    public Vehicle vehicle;
    public Customer customer;
    public Date returnTime;
    public int charges;
    public Comment comment;
	
    public RentalImpl(Date pickupTime, Date returnTime, Reservation reservation, Vehicle vehicle, Customer customer, 
    		int charges,Comment comment){
        super(-1);
        this.pickupTime = pickupTime;
        this.returnTime = returnTime;
        this.reservation = reservation;
        this.vehicle = vehicle;
        this.customer = customer;
        this.charges = charges;
        
    }

	public RentalImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Date getPickupTime() {
		// TODO Auto-generated method stub
		return pickupTime;
	}

	@Override
	public void setPickupTime(Date pickupTime) {
		// TODO Auto-generated method stub
		this.pickupTime=pickupTime;
	}

	@Override
	public Date getReturnTime() {
		// TODO Auto-generated method stub
		return returnTime;
	}

	@Override
	public void setReturnTime(Date returnTime) throws RARException {
		// TODO Auto-generated method stub
		this.returnTime=returnTime;
	}

	@Override
	public boolean getLate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCharges() {
		// TODO Auto-generated method stub
		return charges;
	}

	@Override
	public void setCharges(int charges) throws RARException {
		// TODO Auto-generated method stub
		this.charges=charges;
	}

	@Override
	public Reservation getReservation() {
		// TODO Auto-generated method stub
		return reservation;
	}

	@Override
	public void setReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		this.reservation=reservation;
	}

	@Override
	public Vehicle getVehicle() {
		// TODO Auto-generated method stub
		return vehicle;
	}

	@Override
	public void setVehicle(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		this.vehicle=vehicle;
	}

	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return customer;
	}

	@Override
	public Comment getComment() {
		// TODO Auto-generated method stub
		return comment;
	}

	@Override
	public void setComment(Comment comment) {
		// TODO Auto-generated method stub
		this.comment=comment;
	}
	
	public void setCustomer(Customer customer){
		this.customer=customer;
	}
	
}

