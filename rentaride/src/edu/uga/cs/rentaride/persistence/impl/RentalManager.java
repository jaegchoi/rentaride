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


class RentalManager
{
    private ObjectLayer objectLayer = null;
    private Connection   conn = null;

    public RentalManager( Connection conn, ObjectLayer objectLayer )
    {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }

    public void save(Rental rental)
            throws RARException
    {
        String               insertRentalSql = "insert into Rental ( pickup, returnDate, late, charges, reserveID"
        		+ ", customerID, vehicleID  ) values ( ?, ?, ?, ? )";
        String               updateRentalSql = "update Rental set pickup = ?, returnDate = ?, late = ?, "
        		+ "charges=?, reserveID=?, customerID=?, vehicleID=?, where id = ? ";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 rentalID;


        try {

            if( !rental.isPersistent() )
                stmt = (PreparedStatement) conn.prepareStatement( insertRentalSql );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updateRentalSql );

            if(rental.getPickupTime()!=null){
            	java.util.Date jDate = rental.getPickupTime();
    			java.sql.Date sDate = new java.sql.Date( jDate.getTime() );
    			stmt.setDate(1, sDate);
    		}
    		else{
    			throw new RARException( "Rental.save: can't save a rental: Pickup Time is not set or not persistent" );
    		}
    		
    		
    		if(rental.getReturnTime()!=null){
    			java.util.Date jDate = rental.getReturnTime();
    			java.sql.Date sDate = new java.sql.Date( jDate.getTime() );
    			stmt.setDate(6, sDate);
    		}
    		else{
    			throw new RARException( "Rental.save: can't save a rental: Return Time is not set or not persistent" );
    		}
    		
    		
    		if(rental.getLate()==false){
    			stmt.setBoolean(3, false);
    		}
    		else{
    			stmt.setBoolean(3, true);
    		}
    		
    		
    		if(rental.getCharges()!=0){
    			stmt.setInt(4, rental.getCharges());
    		}
    		else{
    			stmt.setNull(4, java.sql.Types.INTEGER);
    		}
    		
    		
    		if(rental.getReservation()!=null){
    			stmt.setString(5, rental.getReservation().toString());
    		}
    		else{
    			throw new RARException("RentalManager: can't save a rental: Reservation undefined");
    		}
    		
    		
    		if(rental.getCustomer()!=null){
    			stmt.setString(6, rental.getCustomer().toString());
    		}
    		else{
    			throw new RARException("RentalManager: can't save a rental: Customer undefined");
    		}
    		
    		
    		if(rental.getVehicle()!=null){
    			stmt.setString(7, rental.getVehicle().toString());
    		}
    		else{
    			throw new RARException("RentalManager: can't save a rental: Vehicle undefined");
    		}
            
                 
            if( rental.isPersistent() )
                stmt.setLong( 8, rental.getId() );

            inscnt = stmt.executeUpdate();

            if( !rental.isPersistent() ) {
                if( inscnt >= 1 ) {
                    String sql = "select last_insert_id()";
                    if( stmt.execute( sql ) ) { // statement returned a result

                        // retrieve the result
                        ResultSet r = stmt.getResultSet();

                        // we will use only the first row!
                        //
                        while( r.next() ) {

                            // retrieve the last insert auto_increment value
                            rentalID = r.getLong( 1 );
                            if(rentalID > 0 )
                            	rental.setId( rentalID ); // set this vehicleType's db id (proxy object)
                        }//while
                    }//if
                }//if
                else
                    throw new RARException( "RentalManager.save: failed to save a rental" );
            }//if
            else {
                if( inscnt < 1 )
                    throw new RARException( "RentalManager.save: failed to save a rental" );
            }//else
        }//try
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "RentalManager.save: failed to save a Rental: " + e );
        }//catch
    }//save
    
    
    public void delete(Rental rental)
            throws RARException
    {
        String               deleteRentalSql = "delete from Rental where id = ?";
        PreparedStatement    stmt = null;
        int                  inscnt;

        if( !rental.isPersistent() )
            return;

        else{
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteRentalSql );
            stmt.setLong( 1, rental.getId() );
            inscnt = stmt.executeUpdate();
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "RentalManager.delete: failed to delete a Rental" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "RentalManager.delete: failed to delete a Rental: " + e );        }
        }
    }
    
    
    public List<Rental> restore(Rental rental) 
            throws RARException
    {
        //String       selectClubSql = "select id, name, address, established, founderid from club";
        String       selectRentalSql = "select id, pickup, returnDate, charges, reserveID, customerID, vehicleID" +
                " from rental";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Rental>rentals=new ArrayList<Rental>();
        
        
        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectRentalSql );
        
        if( rental != null ) {
            if( rental.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " and id = " + rental.getId() );
            else {

            	if( rental.getPickupTime() != null ) // userName is unique, so it is sufficient to get a person
                    query.append( " and pickup = '" + rental.getPickupTime() + "'" );
            	
                if( rental.getReturnTime() != null )
                    query.append( " and returnDate = '" + rental.getReturnTime() + "'" );   
                
                if( rental.getCharges() != 0 )
                    query.append( " and charges = '" + rental.getCharges() + "'" );   
                
                if( rental.getReservation() != null )
                    query.append( " and reserveID = '" + rental.getReservation() + "'" );   
                
                if( rental.getCustomer() != null )
                    query.append( " and customerID = '" + rental.getCustomer() + "'" );   
                
                if( rental.getVehicle()!=null)
                	query.append(" and vehicleID = '" + rental.getVehicle() + "'" );     
                
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
                Date returnDate;
                int charges;
                Reservation reserve=null;
                Customer customer=null;
                Vehicle vehicle=null;
                String vMake;
                String vModel; 
                int year;
                String vTag;
                int vMileage;
                Date vLastServiced;
                RentalLocation vLocation = null;
                VehicleCondition vCondition = null;
                VehicleStatus vStatus = null;
                VehicleType resVehicleType = null;
                
            
                String r;
                String c;
                String v;
                
                while(rs.next()){
                	id = rs.getLong("1");
                    pickup = rs.getDate( "pickup" );
                    returnDate = rs.getDate( "returnDate" );
                    charges = rs.getInt( "charges" );
                    r = rs.getString( "reserveID" );
                    c = rs.getString( "customerID" );
                    v= rs.getString( "vehicleID" );
                    vMake = rs.getString("make");
                    vModel = rs.getString("model"); 
                    year = rs.getInt("year");
                    vTag = rs.getString("tag");
                    vMileage = rs.getInt("mileage");
                    vLastServiced = rs.getDate("lastServiced");
                    
                    
                    vehicle=objectLayer.createVehicle(vMake, vModel, year, vTag, vMileage, vLastServiced, resVehicleType, vLocation, vCondition, vStatus);
                    //reserve = objectLayer.createReservation(pickup, rentalLength, vehicleType, rentalLocation, customer);
                    
                    
                    Rental rental2 = objectLayer.createRental(pickup,reserve,vehicle);
                    rental2.setId(id);
                    rentals.add(rental2);
                }
                
                
              return rentals;  
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "VehicleManager.restore: Could not restore persistent Vehicle object; Root cause: " + e );
        }

        throw new RARException( "VehicleManager.restore: Could not restore persistent Vehicle object" );
    }
	
    
    
    
    
}//class

