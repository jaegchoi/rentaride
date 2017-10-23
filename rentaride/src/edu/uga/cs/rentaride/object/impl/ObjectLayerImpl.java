package edu.uga.cs.rentaride.object.impl;

import java.util.Date;
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
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class ObjectLayerImpl implements ObjectLayer{

	@Override
	public Administrator createAdministrator(String firstName, String lastName, String userName, String password,
			String email, String address, Date createDate) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Administrator createAdministrator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Administrator> findAdministrator(Administrator modelAdministrator) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeAdministrator(Administrator administrator) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAdministrator(Administrator administrator) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer createCustomer(String firstName, String lastName, String userName, String password, String email,
			String address, Date createDate, Date membershipExpiration, String licenseState, String licenseNumber,
			String cardNumber, Date cardExpiration) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer createCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findCustomer(Customer modelCustomer) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeCustomer(Customer customer) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RentalLocation createRentalLocation(String name, String address, int capacity) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RentalLocation createRentalLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RentalLocation> findRentalLocation(RentalLocation modelRentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reservation createReservation(Date pickupTime, int rentalLength, VehicleType vehicleType,
			RentalLocation rentalLocation, Customer customer) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation createReservation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findReservation(Reservation modelReservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rental createRental(Date pickupTime, Reservation reservation, Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rental createRental() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rental> findRental(Rental modelRental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeRental(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRental(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VehicleType createVehicleType(String name) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehicleType createVehicleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleType> findVehicleType(VehicleType modelVehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vehicle createVehicle(String make, String model, int year, String registrationTag, int mileage,
			Date lastServiced, VehicleType vehicleType, RentalLocation rentalLocation,
			VehicleCondition vehicleCondition, VehicleStatus vehicleStatus) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle createVehicle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> findVehicle(Vehicle modelVehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeVehicle(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVehicle(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comment createComment(String text, Date date, Rental rental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment createComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findComment(Comment modelComment) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeComment(Comment comment) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(Comment comment) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HourlyPrice createHourlyPrice(int maxHrs, int price, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HourlyPrice createHourlyPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HourlyPrice> findHourlyPrice(HourlyPrice modelHourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RentARideParams createRentARideParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RentARideParams findRentARideParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeRentARideParams(RentARideParams rentARideParams) throws RARException {
		// TODO Auto-generated method stub
		
	}
	
}
