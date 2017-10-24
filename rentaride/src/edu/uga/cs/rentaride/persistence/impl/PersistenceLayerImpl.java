package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleType;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.*;
import edu.uga.cs.rentaride.persistence.impl.*;

public class PersistenceLayerImpl {

	private AdministratorManager administratorManager = null;
    private CommentManager commentManager = null;
    private CustomerManager customerManager = null;
    private HourlyPriceManager hourlyPriceManager = null;
    private RentARideParamsManager rentARideParamsManager = null;
    private RentalManager rentalManager = null;
    private RentalLocationManager rentalLocationManager = null;
    private ReservationManager reservationManager = null;
    private VehicleManager vehicleManager = null;
    private VehicleTypeManager vehicleTypeManager = null;
	

    public PersistenceLayerImpl( Connection conn, ObjectLayer objectLayer )
    {
        administratorManager = new AdministratorManager( conn, objectLayer);
        commentManager = new CommentManager( conn, objectLayer);
        customerManager = new CustomerManager( conn, objectLayer);
        hourlyPriceManager = new HourlyPriceManager( conn, objectLayer);
        rentARideParamsManager = new RentARideParamsManager( conn, objectLayer);
        rentalManager = new RentalManager( conn, objectLayer);
        rentalLocationManager = new RentalLocationManager( conn, objectLayer);
        reservationManager = new ReservationManager( conn, objectLayer);
        vehicleManager = new VehicleManager( conn, objectLayer);
        vehicleTypeManager = new VehicleTypeManager( conn, objectLayer);
        System.out.println("PersistenceLayerImpl.PersistenceLayerImpl(conn,objectLayer): initialized");
    }
	

    public void deleteAdministrator(Administrator administrator){
    	
    }

    public void deleteComment(Comment comment){
    	
    }
    
    public void deleteCustomerReservation(Customer customer, Reservation reservation){
    	
    }
    
    public void deleteHourlyPrice(HourlyPrice hourlyPrice){
    	
    }
    
    public void deleteRental(Rental rental){
    	
    }
    
    public void deleteRentalComment(Rental rental, Comment comment){
    	
    }
    
    public void deleteRentalReservation(Rental rental, Reservation reservation){
    	
    }
    
    public void deleteRentalLocation(RentalLocation rentalLocation){
    	
    }
    
    public void deleteReservation(Reservation reservation){
    	
    }
    
    public void deleteReservationRentalLocation(Reservation r, RentalLocation rl){
    	
    }
    
    public void deleteReservationVehicleType(Reservation r, VehicleType vt){
    	
    }
    
    public void deleteVehicle(Vehicle vehicle){
    	
    }
    
    public void deleteVehicleRental(Vehicle v, Rental r){
    	
    }
    
    public void deleteVehicleRentalLocation(Vehicle v, RentalLocation rl){
    	
    }
    
    public void deleteVehicleType(VehicleType vt){
    	
    }
    
    public void deleteVehicleTypeHourlyPrice(VehicleType vt, HourlyPrice hp){
    	
    }
    
    public void deleteVehicleVehicleType(Vehicle v, VehicleType vt){
    	
    }
    
    //type out restores and stores
    
    
    
    
    
    
    
    
    
    
    
    
}

