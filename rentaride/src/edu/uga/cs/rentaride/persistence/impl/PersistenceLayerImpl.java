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
	

    public void deleteAdministrator(Administrator administrator) throws RARException{
    	administratorManager.delete(administrator);
    }

    public void deleteComment(Comment comment) throws RARException{
    	commentManager.delete(comment);
    }
    
    public void deleteCustomerReservation(Customer customer, Reservation reservation) throws RARException{
    	Rental rental =reservation.getRental();
    	reservation.setCustomer(null);
    	//rental.setCustomer(null);
    	reservationManager.save(reservation);
    	rentalManager.save(rental);
    }
    
    public void deleteHourlyPrice(HourlyPrice hourlyPrice) throws RARException{
    	hourlyPriceManager.delete(hourlyPrice);
    }
    
    public void deleteRental(Rental rental) throws RARException{
    	rentalManager.delete(rental);
    }
    
    public void deleteRentalComment(Rental rental, Comment comment) throws RARException{
    	//comment.setCustomer(null);
    	comment.setRental(null);
    	commentManager.save(comment);
    	
    	
    }
    
    public void deleteRentalReservation(Rental rental, Reservation reservation){
    	
    }
    
    public void deleteRentalLocation(RentalLocation rentalLocation) throws RARException{
    	rentalLocationManager.delete(rentalLocation);
    }
    
    public void deleteReservation(Reservation reservation) throws RARException{
    	reservationManager.delete(reservation);
    }
    
    public void deleteReservationRentalLocation(Reservation r, RentalLocation rl) throws RARException{
    	r.setRentalLocation(null);
    	reservationManager.save(r);
    }
    
    public void deleteReservationVehicleType(Reservation r, VehicleType vt) throws RARException{
    	r.setRentalLocation(null);
    	reservationManager.save(r);
    }
    
    public void deleteVehicle(Vehicle vehicle) throws RARException{
    	vehicleManager.delete(vehicle);
    }
    
    public void deleteVehicleRental(Vehicle v, Rental r){
    	
    }
    
    public void deleteVehicleRentalLocation(Vehicle v, RentalLocation rl) throws RARException{
    	v.setRentalLocation(null);
    	vehicleManager.store(v);
    }
    
    public void deleteVehicleType(VehicleType vt) throws RARException{
    	vehicleTypeManager.delete(vt);
    }
    
    public void deleteVehicleTypeHourlyPrice(VehicleType vt, HourlyPrice hp){
    	
    }
    
    public void deleteVehicleVehicleType(Vehicle v, VehicleType vt) throws RARException{
    	v.setVehicleType(null);
    	vehicleManager.store(v);
    }


	@Override
	public List<Administrator> restoreAdministrator(Administrator modelAdministrator) throws RARException {
		// TODO Auto-generated method stub
		return administratorManager.restore(modelAdministrator);
	}


	@Override
	public void storeAdministrator(Administrator administrator) throws RARException {
		// TODO Auto-generated method stub
		administratorManager.save(administrator);
		
	}


	@Override
	public List<Customer> restoreCustomer(Customer modelCustomer) throws RARException {
		// TODO Auto-generated method stub
		return customerManager.restore(modelCustomer);
	}


	@Override
	public void storeCustomer(Customer customer) throws RARException {
		// TODO Auto-generated method stub
		customerManager.save(customer);
		
	}


	@Override
	public List<RentalLocation> restoreRentalLocation(RentalLocation modelRentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return rentalLocationManager.restore(modelRentalLocation);
	}


	@Override
	public void storeRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		rentalLocationManager.save(rentalLocation);
		
	}


	@Override
	public List<Reservation> restoreReservation(Reservation modelReservation) throws RARException {
		// TODO Auto-generated method stub
		return reservationManager.restore( modelReservation );
	}


	@Override
	public void storeReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		reservationManager.save( reservation );
	}


	@Override
	public List<Rental> restoreRental(Rental modelRental) throws RARException {
		// TODO Auto-generated method stub
		return rentalManager.restore( modelRental );
	}


	@Override
	public void storeRental(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		rentalManager.save( rental );
	}


	@Override
	public List<VehicleType> restoreVehicleType(VehicleType modelVehicleType) throws RARException {
		// TODO Auto-generated method stub
		return vehicleTypeManager.restore( modelVehicleType );
	}


	@Override
	public void storeVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		vehicleTypeManager.save( vehicleType );
	}


	@Override
	public List<Vehicle> restoreVehicle(Vehicle modelVehicle) throws RARException {
		// TODO Auto-generated method stub
		return vehicleManager.restore( modelVehicle );
	}


	@Override
	public void storeVehicle(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		vehicleManager.store( vehicle );
	}


	@Override
	public List<Comment> restoreComment(Comment modelComment) throws RARException {
		// TODO Auto-generated method stub
		return commentManager.restore( modelComment );
	}


	@Override
	public void storeComment(Comment comment) throws RARException {
		// TODO Auto-generated method stub
		commentManager.save( comment );
	}


	@Override
	public List<HourlyPrice> restoreHourlyPrice(HourlyPrice modelHourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		return hourlyPriceManager.restore( modelHourlyPrice );
	}


	@Override
	public void storeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		hourlyPriceManager.save( hourlyPrice );
	}


	@Override
	public RentARideParams restoreRentARideConfig() throws RARException {
		// TODO Auto-generated method stub
		return rentARideParamsManager.restore( );
	}


	@Override
	public void storeRentARideConfig(RentARideParams rentARideConfig) throws RARException {
		// TODO Auto-generated method stub
		rentARideParamsManager.save( rentARideConfig );
	}


	@Override
	public void storeCustomerReservation(Customer customer, Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		if (customer == null)
            throw new RARException("The customer is null");
        if (!customer.isPersistent())
            throw new RARException("The customer is not persistent");
        
        Rental rental = reservation.getRental();
        rental.setCustomer(customer);
        reservation.setCustomer( customer );
        reservationManager.save( reservation );
        rentalManager.save( rental );
	}


	@Override
	public Customer restoreCustomerReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return reservationManager.restoreCustomerReservation( reservation );
	}


	@Override
	public List<Reservation> restoreCustomerReservation(Customer customer) throws RARException {
		// TODO Auto-generated method stub
		return customerManager.restoreCustomerReservation( customer );
	}


	@Override
	public void storeReservationRentalLocation(Reservation reservation, RentalLocation rentalLocation)
			throws RARException {
		// TODO Auto-generated method stub
		if (rentalLocation == null)
            throw new RARException("The rental location is null");
        if (!rentalLocation.isPersistent())
            throw new RARException("The rental location is not persistent");
        
        reservation.setRentalLocation( rentalLocation );
        reservationManager.save( reservation );
	}


	@Override
	public RentalLocation restoreReservationRentalLocation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return reservationManager.restoreReservationRentalLocation( reservation );
	}


	@Override
	public List<Reservation> restoreReservationRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return rentalLocationManager.restoreReservationRentalLocation( rentalLocation );
	}


	@Override
	public void storeReservationVehicleType(Reservation reservation, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		if (vehicleType == null)
            throw new RARException("The vehicle type is null");
        if (!vehicleType.isPersistent())
            throw new RARException("The vehicle type is not persistent");
        
        reservation.setRentalLocation(null);
        reservationManager.save( reservation );
	}


	@Override
	public VehicleType restoreReservationVehicleType(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return reservationManager.restoreReservationVehicleType(reservation );
	}


	@Override
	public List<Reservation> restoreReservationVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return vehicleManager.restoreReservationVehicleType( vehicleType );
	}


	@Override
	public void storeVehicleRentalLocation(Vehicle vehicle, RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		if (rentalLocation == null)
            throw new RARException("The rental location is null");
        if (!rentalLocation.isPersistent())
            throw new RARException("The rental location is not persistent");
        
        vehicle.setRentalLocation( rentalLocation );
        vehicleManager.store( vehicle );
	}


	@Override
	public RentalLocation restoreVehicleRentalLocation(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return vehicleManager.restoreVehicleRentalLocation( vehicle );
	}


	@Override
	public List<Vehicle> restoreVehicleRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return rentalLocationManager.restoreVehicleRentalLocation( rentalLocation );
	}


	@Override
	public void storeVehicleVehicleType(Vehicle vehicle, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		if (vehicleType == null)
            throw new RARException("The vehicleType is null");
        if (!vehicleType.isPersistent())
            throw new RARException("The vehicleType is not persistent");
        
        vehicle.setVehicleType( vehicleType );
        vehicleManager.store( vehicle );
	}


	@Override
	public VehicleType restoreVehicleVehicleType(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return vehicleManager.restoreVehicleVehicleType( vehicle );
	}


	@Override
	public List<Vehicle> restoreVehicleVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return vehicleTypeManager.restoreVehicleVehicleType( vehicleType );
	}


	@Override
	public void storeVehicleTypeHourlyPrice(VehicleType vehicleType, HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		if (vehicleType == null)
            throw new RARException("The vehicleType is null");
        if (!vehicleType.isPersistent())
            throw new RARException("The vehicleType is not persistent");
        
        hourlyPrice.setVehicleType( vehicleType );
        hourlyPriceManager.save( hourlyPrice );
	}


	@Override
	public VehicleType restoreVehicleTypeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		return hourlyPriceManager.restoreVehicleTypeHourlyPrice( hourlyPrice );
	}


	@Override
	public List<HourlyPrice> restoreVehicleTypeHourlyPrice(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return vehicleTypeManager.restoreVehicleTypeHourlyPrice( vehicleType );
	}


	@Override
	public void storeRentalComment(Rental rental, Comment comment) throws RARException {
		// TODO Auto-generated method stub
		if (rental == null)
            throw new RARException("The rental is null");
        if (!rental.isPersistent())
            throw new RARException("The rental is not persistent");
        
        Customer customer = rental.getCustomer();
        comment.setCustomer(customer);
        comment.setRental( rental );
        commentManager.save( comment );
	}


	@Override
	public Rental restoreRentalComment(Comment comment) throws RARException {
		// TODO Auto-generated method stub
		return commentManager.restoreRentalComment(comment);
	}


	@Override
	public List<Comment> restoreRentalComment(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		return rentalManager.restoreRentalComment(rental);
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


	@Override
	public void deleteCustomer(Customer c) {
		// TODO Auto-generated method stub
		
	}
    
    //type out restores and stores
    
    
    
    
    
    
    
    
    
    
    
    
}
