package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.impl.Persistent;


public class HourlyPriceImpl
    extends Persistent implements HourlyPrice
{   
    public int maxHours;
    public int price;
    public VehicleType type;
    
    public HourlyPriceImpl(){
    
   }
   
   public HourlyPriceImpl(int maxHours, int price, VehicleType vehicle){
      this.maxHours = maxHours;
      this.price = price;
      this.type = vehicle;
   
   }
    
   @Override
    public int getMaxHours(){
      return maxHours;
    }
    
@Override
public void setMaxHours( int maxHours ) throws RARException{
    this.maxHours = maxHours;
}
 
 @Override
    public int getPrice(){
    return price;
    }
    @Override
    public void setPrice( int price ) throws RARException{
      this.price = price;
    }
    
    @Override
    public VehicleType getVehicleType(){
      return type;
    }

@Override
public void setVehicleType( VehicleType vehicleType ) throws RARException{
  this.type = vehicleType;
}
}
