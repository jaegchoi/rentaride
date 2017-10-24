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

public class PersistenceLayerImpl implements PersistenceLayer {

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


	@Override
	public List<Administrator> restoreAdministrator(Administrator modelAdministrator) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeAdministrator(Administrator administrator) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Customer> restoreCustomer(Customer modelCustomer) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeCustomer(Customer customer) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<RentalLocation> restoreRentalLocation(RentalLocation modelRentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Reservation> restoreReservation(Reservation modelReservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Rental> restoreRental(Rental modelRental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeRental(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<VehicleType> restoreVehicleType(VehicleType modelVehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Vehicle> restoreVehicle(Vehicle modelVehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeVehicle(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Comment> restoreComment(Comment modelComment) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeComment(Comment comment) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<HourlyPrice> restoreHourlyPrice(HourlyPrice modelHourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public RentARideParams restoreRentARideConfig() throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeRentARideConfig(RentARideParams rentARideConfig) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void storeCustomerReservation(Customer customer, Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Customer restoreCustomerReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Reservation> restoreCustomerReservation(Customer customer) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeReservationRentalLocation(Reservation reservation, RentalLocation rentalLocation)
			throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public RentalLocation restoreReservationRentalLocation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Reservation> restoreReservationRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeReservationVehicleType(Reservation reservation, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public VehicleType restoreReservationVehicleType(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Reservation> restoreReservationVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeVehicleRentalLocation(Vehicle vehicle, RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public RentalLocation restoreVehicleRentalLocation(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Vehicle> restoreVehicleRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeVehicleVehicleType(Vehicle vehicle, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public VehicleType restoreVehicleVehicleType(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Vehicle> restoreVehicleVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeVehicleTypeHourlyPrice(VehicleType vehicleType, HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public VehicleType restoreVehicleTypeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<HourlyPrice> restoreVehicleTypeHourlyPrice(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeRentalComment(Rental rental, Comment comment) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Rental restoreRentalComment(Comment comment) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Comment> restoreRentalComment(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeRentalReservation(Rental rental, Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Rental restoreRentalReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Reservation restoreRentalReservation(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void storeVehicleRental(Vehicle vehicle, Rental rental) throws RARException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Rental> restoreVehicleRental(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Vehicle restoreVehicleRental(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
