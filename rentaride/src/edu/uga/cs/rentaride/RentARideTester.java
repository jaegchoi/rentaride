package edu.uga.cs.rentaride.test;

import java.sql.Connection;
import java.util.Date;
import edu.uga.cs.rentaride.*;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.object.impl.ObjectLayerImpl;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.DbUtils;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;

public class RentARideTester
{
    public static void main(String[] args)
    {
         Connection conn = null;
         ObjectLayer objectLayer = null;
         PersistenceLayer persistence = null;
		 
		 Administrator admin,admin2;
		 
		 RentalLocation location,locat2;
		 
		 VehicleType type,type2;
		 
		 HourlyPrice price,price2,price3,price4;
		 
		 Vehicle v,v2,v3,v4;
		 
		 Customer c,c2;
		 
		 Reservation r,r2,r3,r4;
		 
		 Rental rental,rental2;
		 
		 Comment comment,comment2;
		 
		 final VehicleCondition vc = VehicleCondition.GOOD;
		 final VehicleStatus vs = VehicleStatus.INLOCATION;
		 
		 // get a database connection
         try {
             conn = DbUtils.connect();
         } 
         catch (Exception seq) {
             System.err.println( "WriteTest: Unable to obtain a database connection" );
         }
         
         if( conn == null ) {
             System.out.println( "WriteTest: failed to connect to the database" );
             return;
         }
		 
     
         objectLayer = new ObjectLayerImpl();
      
         persistence = new PersistenceLayerImpl( conn, objectLayer ); 
 
         objectLayer.setPersistence( persistence );
		 
		 try{
			 
			 //initialize Admins
			 admin = objectLayer.createAdministrator("Jake", "Hilmandolar", "jhilm", "pass", "jhilm@gmail.com", "123 Elm Street", new Date());
			 persistence.storeAdministrator(admin);
			 admin2 = objectLayer.createAdministrator("Bilbo", "Baggins", "1ring", "pass", "torule@gmail.com", "Them All Court", new Date());
			 persistence.storeAdministrator(admin2);
			 
			 //init locations
			 location = objectLayer.createRentalLocation("Target", "45 Atlanta Highway", 25);
			 persistence.storeRentalLocation(location);
			 locat2 = objectLayer.createRentalLocation("Zaxby's", "1900 Epps Bridge", 22);
			 persistence.storeRentalLocation(locat2);
			 
			 // initialize vehicle types with hourly prices
			 type = objectLayer.createVehicleType("truck");
			 persistence.storeVehicleType(type);
			 type2=objectLayer.createVehicleType("car");
			 persistence.storeVehicleType(type2);
			 
			 price = objectLayer.createHourlyPrice(3,15, type);
			 price2=objectLayer.createHourlyPrice(6,17,type);
			 price3=objectLayer.createHourlyPrice(1,8,type2);
			 price4=objectLayer.createHourlyPrice(2,9,type2);
			 
			 persistence.storeHourlyPrice(price);
			 persistence.storeHourlyPrice(price2);
			 persistence.storeHourlyPrice(price3);
			 persistence.storeHourlyPrice(price4);
			 
			 
			 
			 
			 //initialize vehicles
			 v = objectLayer.createVehicle("Toyota", "Tacoma", 2006, "1234567", 90000, new Date(), type, location, vc, vs);
			 persistence.storeVehicle(v);
			 
			 v2 = objectLayer.createVehicle("Toyota", "Tundra", 2008, "1234567", 85000, new Date(), type, location, vc, vs);
			 persistence.storeVehicle(v2);
			 
			 v3 = objectLayer.createVehicle("Toyota", "Corolla", 2015, "1234567", 30000, new Date(), type2, location, vc, vs);
			 persistence.storeVehicle(v3);
			 
			 v4 = objectLayer.createVehicle("Honda", "Civic", 2017, "1234567", 3500, new Date(), type2, location, vc, vs);
			 persistence.storeVehicle(v4);
			 
			 
			 
			 //initialize customer
			 c = objectLayer.createCustomer("Barney", "Stinson", "legen", "dary", "highfive@gmail.com", "NY,NY", new Date(),
			 new Date(), "NY", "12300", "111-111-111-111", new Date());
			 persistence.storeCustomer(c);
			 
			 c2 = objectLayer.createCustomer("Todd", "Gurley", "GoDawgs1", "LARAMS", "tgIII@gmail.com", "Athens,GA", new Date(),
					 new Date(), "GA", "12356", "222-222-222-222", new Date());
					 persistence.storeCustomer(c2);
			 
			//initialize reservation
			r = objectLayer.createReservation(new Date(), 2, type, location, c);
			r2 = objectLayer.createReservation(new Date(), 1, type2, locat2, c);
			r3 = objectLayer.createReservation(new Date(), 1, type2, location, c2);
			r4 = objectLayer.createReservation(new Date(), 2, type, locat2, c2);
			persistence.storeReservation(r);
			persistence.storeReservation(r2);
			persistence.storeReservation(r3);
			persistence.storeReservation(r4);
			 
			 //initialize rentals
			 rental = objectLayer.createRental(new Date(), r, v);
			 rental2=objectLayer.createRental(new Date(),r2,v4);
			 persistence.storeRental(rental);
			 persistence.storeRental(rental2);

			 //initialize comments
			 comment = objectLayer.createComment("AWESOME RIDE MANNNNNN", new Date(), rental);
			 comment2 = objectLayer.createComment("Car smells like smoke.", new Date(), rental2);
			 persistence.storeComment(comment);
			 persistence.storeComment(comment2);
			 
			 //initialize delete the data
			 persistence.deleteComment(comment);
			 objectLayer.deleteComment(comment);
			 persistence.deleteComment(comment2);
			 objectLayer.deleteComment(comment2);
			 
			 
			 persistence.deleteRental(rental);
			 objectLayer.deleteRental(rental);
			 persistence.deleteRental(rental2);
			 objectLayer.deleteRental(rental2);

			 persistence.deleteReservation(r);
			 objectLayer.deleteReservation(r);
			 persistence.deleteReservation(r2);
			 objectLayer.deleteReservation(r2);
			 persistence.deleteReservation(r3);
			 objectLayer.deleteReservation(r3);
			 persistence.deleteReservation(r4);
			 objectLayer.deleteReservation(r4);

			 persistence.deleteCustomer(c);
			 objectLayer.deleteCustomer(c);
			 persistence.deleteCustomer(c2);
			 objectLayer.deleteCustomer(c2);

			 persistence.deleteVehicle(v);
			 objectLayer.deleteVehicle(v);
			 persistence.deleteVehicle(v2);
			 objectLayer.deleteVehicle(v2);
			 persistence.deleteVehicle(v3);
			 objectLayer.deleteVehicle(v3);
			 persistence.deleteVehicle(v4);
			 objectLayer.deleteVehicle(v4);

			 persistence.deleteHourlyPrice(price);
			 objectLayer.deleteHourlyPrice(price);
			 persistence.deleteHourlyPrice(price2);
			 objectLayer.deleteHourlyPrice(price2);
			 persistence.deleteHourlyPrice(price3);
			 objectLayer.deleteHourlyPrice(price3);
			 persistence.deleteHourlyPrice(price4);
			 objectLayer.deleteHourlyPrice(price4);

			 persistence.deleteVehicleType(type);
			 objectLayer.deleteVehicleType(type);
			 persistence.deleteVehicleType(type2);
			 objectLayer.deleteVehicleType(type2);

			 persistence.deleteRentalLocation(location);
			 objectLayer.deleteRentalLocation(location);
			 persistence.deleteRentalLocation(locat2);
			 objectLayer.deleteRentalLocation(locat2);

			 persistence.deleteAdministrator(admin);
			 objectLayer.deleteAdministrator(admin);
			 persistence.deleteAdministrator(admin2);
			 objectLayer.deleteAdministrator(admin2);

			 
		 }
		 catch (RARException rare)
		 {
			 System.err.println( "Exception: " + rare );
             rare.printStackTrace();
		 }
		 catch( Exception e ) {
             e.printStackTrace();
         }
         finally {
             // close the connection
             try {
                 conn.close();
             }
             catch( Exception e ) {
                 System.err.println( "Exception: " + e );
             }
         }
		 
		 
	}
}
