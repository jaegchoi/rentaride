
package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;


public class ReservationManager
{
    private ObjectLayer objectLayer = null;
    private Connection   conn = null;

    public ReservationManager( Connection conn, ObjectLayer objectLayer )
    {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }

    public void save(Reservation reservation)
            throws RARException
    {
        String insertReservationSql = "insert into Reservation ( pickup, length, cancelled, rentalID, customerID, vehicleTypeID, rentalLocationID) values ( ?, ?, ?, ?, ?, ?, ? )";
        String updateReservationSql = "update Reservation set pickup = ?, length = ?, cancelled = ?, rentalID=?, customerID=?, vehicleTypeID=?, rentalLocationID=?, where id = ? ";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 reservationID;


        try {

            if( !reservation.isPersistent() )
                stmt = (PreparedStatement) conn.prepareStatement( insertReservationSql );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updateReservationSql );

            if(reservation.getPickupTime()!=null){
            	java.util.Date jDate = reservation.getPickupTime();
    			java.sql.Date sDate = new java.sql.Date( jDate.getTime() );
    			stmt.setDate(1, sDate);
    		}
    		else{
    			throw new RARException( "Reservation.save: can't save a reseration: Pickup Time is not set or not persistent" );
    		}
    		
    		
    		if(reservation.getLength()!=null){
          stmt.setInt(2, reservation.getLength());
    		}
    		else{
    			throw new RARException( "Reservation.save: can't save a reservation: Length is not set or not persistent" );
    		}
    		
    		
    		if(reservation.getCancelled()==false){
    			stmt.setBoolean(3, false);
    		}
    		else{
    			stmt.setBoolean(3, true);
    		}
    		
    		
    		if(rental.getCustomer()!=null){
    			stmt.setString(6, reservation.getCustomer().toString());
    		}
    		else{
    			throw new RARException("ReservationManager: can't save a reservation: Customer undefined");
    		}
    		
    		
    		if(rental.getVehicleType()!=null){
    			stmt.setString(7, reservation.getVehicleType().toString());
    		}
    		else{
    			throw new RARException("ReservationManager: can't save a reservation: VehicleType undefined");
    		}
        if(reservation.getRentalLocation()!=null){
    			stmt.setString(7, reservation.getRentalLocation().toString());
    		}
    		else{
    			throw new RARException("ReservationManager: can't save a reservation: RentalLocation undefined");
}
            
                 
            if( reservation.isPersistent() )
                stmt.setLong( 8, reservation.getId() );

            inscnt = stmt.executeUpdate();

            if( !reservation.isPersistent() ) {
                if( inscnt >= 1 ) {
                    String sql = "select last_insert_id()";
                    if( stmt.execute( sql ) ) { // statement returned a result

                        // retrieve the result
                        ResultSet r = stmt.getResultSet();

                        // we will use only the first row!
                        //
                        while( r.next() ) {

                            // retrieve the last insert auto_increment value
                            reservationID = r.getLong( 1 );
                            if(reservationID > 0 )
                            	reservation.setId( reservationID ); // set this vehicleType's db id (proxy object)
                        }//while
                    }//if
                }//if
                else
                    throw new RARException( "ReservationManager.save: failed to save a reservation" );
            }//if
            else {
                if( inscnt < 1 )
                    throw new RARException( "ReservationManager.save: failed to save a reservation" );
            }//else
        }//try
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "ReservationManager.save: failed to save a Reservation: " + e );
        }//catch
    }//save
    
    
    public void delete(Reservation reservation)
            throws RARException
    {
        String               deleteReservationSql = "delete from Reservation where id = ?";
        PreparedStatement    stmt = null;
        int                  inscnt;

        if( !reservation.isPersistent() )
            return;

        else{
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteReservationSql );
            stmt.setLong( 1, reservation.getId() );
            inscnt = stmt.executeUpdate();
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "ReservationManager.delete: failed to delete a Reservation" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "ReservationManager.delete: failed to delete a Reservation: " + e );        }
        }
    }
    
    
    public List<Reservation> restore(Reservation reservation) 
            throws RARException
    {
        //String       selectClubSql = "select id, name, address, established, founderid from club";
        String       selectReservationSql = "select id, pickup, length, cancelled, rentalID, customerID, vehicleTypeID, rentalLocationID" +
                " from reservation";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Reservation>reservations=new ArrayList<Reservation>();
        
        
        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectReservationSql );
        
        if( reservation != null ) {
            if( reservation.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " and id = " + reservation.getId() );
            else {

            	if( reservation.getPickupTime() != null ) // userName is unique, so it is sufficient to get a person
                    query.append( " and pickup = '" + reservation.getPickupTime() + "'" );
            	
                if( reservation.getLength() != null )
                    query.append( " and length = '" + reservation.getLength() + "'" );   
                
                if( reservation.getCancelled() != 0 )
                    query.append( " and charges = '" + reservation.getCancelled() + "'" );   
                
                if( reservation.getRental() != null )
                    query.append( " and rentalID = '" + reservation.getRental() + "'" );   
                
                if( reservation.getCustomer() != null )
                    query.append( " and customerID = '" + reservation.getCustomer() + "'" );   
                
                if( reservation.getVehicleType()!=null)
                	query.append(" and vehicleTyeID = '" + reservation.getVehicleType() + "'" );     
                
            }
        }
        
        try {

            stmt = conn.createStatement();
            //System.out.println("stmt: " + query);
            // retrieve the persistent Person object
            //
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet rs = stmt.getResultSet();
                long id;
                Date pickup;
                int length;
                boolean cancelled;
                Rental rental=null;
                Customer customer=null;
                VehicleType vehicleType=null;
                RentalLocation rentalLocation = null;
                
            
                String r;
                String c;
                String v;
                String l;
                
                while(rs.next()){
                	id = rs.getLong("1");
                    pickup = rs.getDate( "pickup" );
                    returnDate = rs.getInt( "length" );
                    charges = rs.getBoolean( "cancelled" );
                    r = rs.getString( "rentalID" );
                    c = rs.getString( "customerID" );
                    v= rs.getString( "vehicleTypeID" );
                    l= rs.getString("rentalLocationID");
                   
                    
                    
                    //vehicle=objectLayer.createVehicle(vMake, vModel, year, vTag, vMileage, vLastServiced, resVehicleType, vLocation, vCondition, vStatus);
                    reservation = objectLayer.createReservation(pickup, length, vehicleType, rentalLocation, customer);
                    
                    
                    Rental rental2 = objectLayer.createRental(pickup,reserve,vehicle);
                    rental2.setId(id);
                    rentals.add(rental2);
                }
                
                
              return reservations;  
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "ReservationManager.restore: Could not restore persistent Reservation object; Root cause: " + e );
        }

        throw new RARException( "ReservationManager.restore: Could not restore persistent Reservation object" );
    }

	public Customer restoreCustomerReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return null;
	}

	public RentalLocation restoreReservationRentalLocation(Reservation reservation) {
		// TODO Auto-generated method stub
		return null;
	}

	public VehicleType restoreReservationVehicleType(Reservation reservation) {
		// TODO Auto-generated method stub
		return null;
	}
	
    
    
    
    
}//class
