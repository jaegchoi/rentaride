package edu.uga.cs.rentaride.entity.impl;

import java.util.List;

import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.impl.Persistent;



public class VehicleTypeImpl
    extends Persistent implements VehicleType
{
   public String name;
   public List<HourlyPrice> priceList;
   public List<Vehicle> vehicleList;
   public List<Reservation> reservationList;

public VheicleTypeImpl(){
}

public VehicleTypeImpl(String name, HourlyPrice price, Vehicle vehicle, Reservation reservation){
    this.name = name;
    
    
}

@Override
    public String getName(){
      return name;
    }
    
    @Override
    public void setName( String name ) throws RARException{
      this.name = name;
    }
    
    @Override
    public List<HourlyPrice> getHourlyPrices(){
        if(priceList == null){
          if(isPersistent()){
            HourlyPrice p = new HourlyPriceImpl();
            p.setVehickeType(this);
            priceList = getPersistenceLayer().restoreHourlyPrice(p);
          }
        }
        return priceList;
    }
   
   @Override
       public List<Vehicle> getVehicles(){
       if(vehicleList == null){
          if(isPersistent()){
            Vehicle v = new VehicleImpl();
            v.setVehickeType(this);
            vehicleList = getPersistenceLayer().restoreVehicle(v);
          }
        }
        return vehicleList;
       }
    
   @Override
    public List<Reservation> getReservations(){
      if(reservationList == null){
          if(isPersistent()){
            Reservation r = new ReservationImpl();
            r.setVehickeType(this);
            reservationList = getPersistenceLayer().restoreReservation(r);
          }
        }
        return reservationList;
    }
}

