
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
		 
		 Administrator admin;
		 RentalLocation location;
		 VehicleType type;
		 HourlyPrice price;
		 Vehicle v;
		 Customer c;
		 Reservation r;
		 Rental rental;
		 Comment comment;
		 VehicleCondition vc = null;
		 VehicleStatus vs=null;
		 
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
 
		 
		 try{
			 
			 //admins
			 admin = objectLayer.createAdministrator("Jake", "Hilmandolar", "jhilm", "pass", "jhilm@gmail.com", "123 Elm Street", new Date());
			 persistence.storeAdministrator(admin);
			 
			 //locations
			 location = objectLayer.createRentalLocation("Target", "45 Atlanta Highway", 25);
			 persistence.storeRentalLocation(location);
			 
			 //vehicle types with hourly prices
			 type = objectLayer.createVehicleType("truck");
			 persistence.storeVehicleType(type);
			 price = objectLayer.createHourlyPrice(2,10, type);
			 persistence.storeHourlyPrice(price);
			 persistence.storeVehicleType(type);
			 
			 
			 //vehicles
			 v = objectLayer.createVehicle("Toyota", "Tacoma", 2013, "1234567", 10000, new Date(), type, location, vc, vs);
			 persistence.storeVehicle(v);
			 //customers
			 
			 c = objectLayer.createCustomer("Barney", "Stinson", "legen", "dairy", "highfive@gmail.com", "NY,NY", new Date(),
			 new Date(), "New York", "12300", "111-111-111-111", new Date());
			 persistence.storeCustomer(c);
			 //reservations
			 
			 r = objectLayer.createReservation(new Date(), 2, type, location, c);
			 persistence.storeReservation(r);
			 //rentals
			 
			 rental = objectLayer.createRental(new Date(), r, v);
			 persistence.storeRental(rental);

			 //comments
			 comment = objectLayer.createComment("AWESOME RIDE MANNNNNN", new Date(), rental);
			 persistence.storeComment(comment);

			 
			 //delete the data
			 persistence.deleteComment(comment);
			 objectLayer.deleteComment(comment);

			 persistence.deleteRental(rental);
			 objectLayer.deleteRental(rental);

			 persistence.deleteReservation(r);
			 objectLayer.deleteReservation(r);

			 persistence.deleteCustomer(c);
			 objectLayer.deleteCustomer(c);

			 persistence.deleteVehicle(v);
			 objectLayer.deleteVehicle(v);

			 persistence.deleteHourlyPrice(price);
			 objectLayer.deleteHourlyPrice(price);

			 persistence.deleteVehicleType(type);
			 objectLayer.deleteVehicleType(type);

			 persistence.deleteRentalLocation(location);
			 objectLayer.deleteRentalLocation(location);

			 persistence.deleteAdministrator(admin);
			 objectLayer.deleteAdministrator(admin);

			 
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
