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
import edu.uga.cs.rentaride.entity.impl.ReservationImpl;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.Persistent;


public class ObjectLayerImpl implements ObjectLayer{

	PersistenceLayer persistence = null;
	
	public ObjectLayerImpl() {
		this.persistence = null;
		System.out.println("ObjectLayerImpl.ObjectLayerImpl(): initialized");
	}
	
	public ObjectLayerImpl(PersistenceLayer persistence) {
		this.persistence = persistence;
		System.out.println("ObjectLayerImpl.ObjectLayerImpl(persistence): initialized");
	}
	
	@Override
	public Administrator createAdministrator(String firstName, String lastName, String userName, String password,
			String email, String address, Date createDate) throws RARException {
		AdministratorImpl admin = new AdministratorImpl(firstName, lastName, userName, password, email, address, createDate);
		Persistent.setPersistenceLayer(persistence);
		return admin;
	}

	@Override
	public Administrator createAdministrator() {
		AdministratorImpl admin = new AdministratorImpl();
		admin.setId(-1);
		return admin;
	}

	@Override
	public List<Administrator> findAdministrator(Administrator modelAdministrator) throws RARException {
		return persistence.restoreAdministrator(modelAdministrator);
	}

	@Override
	public void storeAdministrator(Administrator administrator) throws RARException {
		persistence.storeAdministrator(administrator);
		
	}

	@Override
	public void deleteAdministrator(Administrator administrator) throws RARException {
		persistence.deleteAdministrator(administrator);
	}

	@Override
	public Customer createCustomer(String firstName, String lastName, String userName, String password, String email,
			String address, Date createDate, Date membershipExpiration, String licenseState, String licenseNumber,
			String cardNumber, Date cardExpiration) throws RARException {
		
		CustomerImpl customer = new CustomerImpl(membershipExpiration,
				licenseState, licenseNumber, cardNumber, cardExpiration);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setUserName(userName);
		customer.setPassword(password);
		customer.setEmail(email);
		customer.setAddress(address);
		customer.setCreateDate(createDate);
		Persistent.setPersistenceLayer(persistence);
		return customer;
	}

	@Override
	public Customer createCustomer() {
		CustomerImpl customer = new CustomerImpl();
		customer.setId(-1);
		Persistent.setPersistenceLayer(persistence);
		return customer;
	}

	@Override
	public List<Customer> findCustomer(Customer modelCustomer) throws RARException {
		return persistence.restoreCustomer(modelCustomer);
	}

	@Override
	public void storeCustomer(Customer customer) throws RARException {
		persistence.storeCustomer(customer);
	}

	@Override
	public RentalLocation createRentalLocation(String name, String address, int capacity) throws RARException {
		RentalLocationImpl rentalLocation = new RentalLocationImpl(name, address, capacity);
		Persistent.setPersistenceLayer(persistence);
		return rentalLocation;
	}

	@Override
	public RentalLocation createRentalLocation() {
		RentalLocationImpl rentalLocation = new RentalLocationImpl();
		Persistent.setPersistenceLayer(persistence);
		return rentalLocation;
	}

	@Override
	public List<RentalLocation> findRentalLocation(RentalLocation modelRentalLocation) throws RARException {
		return persistence.restoreRentalLocation(modelRentalLocation);
	}

	@Override
	public void storeRentalLocation(RentalLocation rentalLocation) throws RARException {
		persistence.storeRentalLocation(rentalLocation);
		
	}

	@Override
	public void deleteRentalLocation(RentalLocation rentalLocation) throws RARException {
		persistence.deleteRentalLocation(rentalLocation);
		
	}

	@Override
	public Reservation createReservation(Date pickupTime, int rentalLength, VehicleType vehicleType,
			RentalLocation rentalLocation, Customer customer) throws RARException {
		ReservationImpl reservation = new ReservationImpl(pickupTime, rentalLength, false, 
				rentalLocation, vehicleType, customer, null);
		Persistent.setPersistenceLayer(persistence);
		return reservation;
	}

	@Override
	public Reservation createReservation() {
		ReservationImpl reservation = new ReservationImpl();
		reservation.setId(-1);
		Persistent.setPersistenceLayer(persistence);
		return reservation;
	}

	@Override
	public List<Reservation> findReservation(Reservation modelReservation) throws RARException {
		return persistence.restoreReservation(modelReservation);
	}

	@Override
	public void storeReservation(Reservation reservation) throws RARException {
		persistence.storeReservation(reservation);
		
	}

	@Override
	public void deleteReservation(Reservation reservation) throws RARException {
		persistence.deleteReservation(reservation);
		
	}

	@Override
	public Rental createRental(Date pickupTime, Reservation reservation, Vehicle vehicle) throws RARException {
		RentalImpl rental = new RentalImpl();
		rental.setPickupTime(pickupTime);
		rental.setReservation(reservation);
		rental.setVehicle(vehicle);
		Persistent.setPersistenceLayer(persistence);
		return rental;
	}

	@Override
	public Rental createRental() {
		RentalImpl rental = new RentalImpl();
		rental.setId(-1);
		Persistent.setPersistenceLayer(persistence);
		return rental;
	}

	@Override
	public List<Rental> findRental(Rental modelRental) throws RARException {
		return persistence.restoreRental(modelRental);
	}

	@Override
	public void storeRental(Rental rental) throws RARException {
		persistence.storeRental(rental);
	}

	@Override
	public void deleteRental(Rental rental) throws RARException {
		persistence.deleteRental(rental);
	}

	@Override
	public VehicleType createVehicleType(String name) throws RARException {
		VehicleTypeImpl vehicleType = new VehicleTypeImpl();
		vehicleType.setName(name);
		Persistent.setPersistenceLayer(persistence);
		return vehicleType;
	}

	@Override
	public VehicleType createVehicleType() {
		VehicleTypeImpl vehicleType = new VehicleTypeImpl();
		vehicleType.setId(-1);
		Persistent.setPersistenceLayer(persistence);
		return vehicleType;
	}

	@Override
	public List<VehicleType> findVehicleType(VehicleType modelVehicleType) throws RARException {
		return persistence.restoreVehicleType(modelVehicleType);
	}

	@Override
	public void storeVehicleType(VehicleType vehicleType) throws RARException {
		persistence.storeVehicleType(vehicleType);
	}

	@Override
	public void deleteVehicleType(VehicleType vehicleType) throws RARException {
		persistence.deleteVehicleType(vehicleType);
	}

	@Override
	public Vehicle createVehicle(String make, String model, int year, String registrationTag, int mileage,
			Date lastServiced, VehicleType vehicleType, RentalLocation rentalLocation,
			VehicleCondition vehicleCondition, VehicleStatus vehicleStatus) throws RARException {
		VehicleImpl vehicle = new VehicleImpl(vehicleType, make, model, year, mileage, registrationTag,
				lastServiced, vehicleStatus, vehicleCondition, rentalLocation);
		Persistent.setPersistenceLayer(persistence);
		return vehicle;
		
	}

	@Override
	public Vehicle createVehicle() {
		VehicleImpl vehicle = new VehicleImpl();
		vehicle.setId(-1);
		Persistent.setPersistenceLayer(persistence);
		return vehicle;
	}

	@Override
	public List<Vehicle> findVehicle(Vehicle modelVehicle) throws RARException {
		return persistence.restoreVehicle(modelVehicle);
	}

	@Override
	public void storeVehicle(Vehicle vehicle) throws RARException {
		persistence.storeVehicle(vehicle);
	}

	@Override
	public void deleteVehicle(Vehicle vehicle) throws RARException {
		persistence.deleteVehicle(vehicle);
	}

	@Override
	public Comment createComment(String text, Date date, Rental rental) throws RARException {
		CommentImpl comment = new CommentImpl(text, date);
		comment.setRental(rental);
		Persistent.setPersistenceLayer(persistence);
		return comment;
	}

	@Override
	public Comment createComment() {
		CommentImpl comment = new CommentImpl();
		comment.setId(-1);
		Persistent.setPersistenceLayer(persistence);
		return comment;
	}

	@Override
	public List<Comment> findComment(Comment modelComment) throws RARException {
		return persistence.restoreComment(modelComment);
	}

	@Override
	public void storeComment(Comment comment) throws RARException {
		persistence.storeComment(comment);
	}

	@Override
	public void deleteComment(Comment comment) throws RARException {
		persistence.deleteComment(comment);
	}

	@Override
	public HourlyPrice createHourlyPrice(int maxHrs, int price, VehicleType vehicleType) throws RARException {
		HourlyPriceImpl hourlyPrice = new HourlyPriceImpl(maxHrs, price, vehicleType);
		Persistent.setPersistenceLayer(persistence);
		return hourlyPrice;
	}

	@Override
	public HourlyPrice createHourlyPrice() {
		HourlyPriceImpl hourlyPrice = new HourlyPriceImpl();
		hourlyPrice.setId(-1);
		Persistent.setPersistenceLayer(persistence);
		return hourlyPrice;
	}

	@Override
	public List<HourlyPrice> findHourlyPrice(HourlyPrice modelHourlyPrice) throws RARException {
		return persistence.restoreHourlyPrice(modelHourlyPrice);
	}

	@Override
	public void storeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		persistence.storeHourlyPrice(hourlyPrice);
	}

	@Override
	public void deleteHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		persistence.deleteHourlyPrice(hourlyPrice);	
	}

	@Override
	public RentARideParams createRentARideParams() {
		RentARideParamsImpl params = new RentARideParamsImpl();
		params.setId(-1);
		Persistent.setPersistenceLayer(persistence);
		return params;
	}

	@Override
	public RentARideParams findRentARideParams() {
		return persistence.restoreRentARideConfig();
	}

	@Override
	public void storeRentARideParams(RentARideParams rentARideParams) throws RARException {
		persistence.storeRentARideConfig(rentARideParams);
	}

	
	@Override
	public void deleteCustomer(Customer c) {
		persistence.deleteCustomer(c);
	}


	public void setPersistence(PersistenceLayer persistence) {
		this.persistence = persistence;
		
	}
	
}
