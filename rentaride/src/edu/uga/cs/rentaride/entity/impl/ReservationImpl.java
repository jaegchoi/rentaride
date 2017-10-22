package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.persistence.impl.Persistable;


public ReservationImpl extends Persistent implements Reservation {
    public Date pickup;
    public int length;
    public boolean cancelled;
    public RentalLocation location;
    public VehicleType type;
    public Customer customer;
    public Rental rental;
  
  public ReservationImpl(){
  
  }
  public ReservationImpl(Date pickup, int length, boolean cancelled, RentalLocation location, VehicleType type, Customer customer, Rental rental){
    
    this.pickup = pickup;
    this.length = length;
    this.cancelled = cancelled;
    this.localtion = location;
    this.type = type;
    this.customer = customer;
    this.rental = rental;
  }
  
  @Override
    public Date getPickupTime(){
        return pickup;
    }
  @Override
    public void setPickupTime( Date pickupTime ) throws RARException{
          this.pickup = pickup;
    }
  @Override
    public int getLength(){
     return length; 
    }
    
@Override
    public void setLength( int length ) throws RARException{
        this.length = length; 
    }
@Override    
    public Customer getCustomer(){
      return customer; 
    }
@Override    
    public void setCustomer( Customer customer ) throws RARException{
      this.customer = customer;
    }
@Override    
    public VehicleType getVehicleType(){
     return type; 
    }
    
@Override
    public void setVehicleType( VehicleType vehicleType ) throws RARException{
      this.type = type;
}
  @Override
    public RentalLocation getRentalLocation(){
      return location;
    
    }  
  @Override
    public void setRentalLocation( RentalLocation rentalLocation ) throws RARException{
      this.location = location; 
    }
    
  @Override
    public Rental getRental(){
     return rental; 
    }
@override
    public void setRental( Rental rental ){
      this.rental = rental;  
    }

}
