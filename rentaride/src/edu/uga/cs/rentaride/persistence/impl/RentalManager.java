
package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.sql.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Rental;
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
    
}//class

