package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;


class VehicleManager{
	
	private ObjectLayer objectLayer=null;
	private Connection conn=null;
	
	public VehicleManager(Connection conn, ObjectLayer objectLayer){
		this.conn=conn;
		this.objectLayer=objectLayer;
	}
	
	public void store(Vehicle vehicle) throws RARException{
		String insertVehicleSql="insert into vehicle( make, model, year, mileage, tag, lastServiced,"
				+ " vehicleStatus, vehicleCondition, rentalLocationID, vehicleTypeID ) values (?,?,?,?,?,?,?,?,?,?)";
		String updateVehicleSql="update vehicle set make=?,model=?,year=?,mileage=?,tag=?,lastServiced=?,vehicleStatus=?"
				+ ",vehiceCondition=?,rentalLocationID=?,vehicleTypeID=? where id=?";
		PreparedStatement stmt=null;
		int inscnt;
		long vehicleId;
		
		try{
			
		if(!vehicle.isPersistent()){
			stmt=(PreparedStatement) conn.prepareStatement(insertVehicleSql);
		}//if
		else{
			stmt=(PreparedStatement) conn.prepareStatement(updateVehicleSql);
		}
		
		
		if(vehicle.getMake()!=null){
			stmt.setString(1, vehicle.getMake());
		}
		else{
			stmt.setNull(1, java.sql.Types.VARCHAR);
		}
		
		
		if(vehicle.getModel()!=null){
			stmt.setString(2, vehicle.getModel());
		}
		else{
			stmt.setNull(2, java.sql.Types.VARCHAR);
		}
		
		
		if(vehicle.getYear()!=0){
			stmt.setInt(3, vehicle.getYear());
		}
		else{
			stmt.setNull(3, java.sql.Types.INTEGER);
		}
		
		
		if(vehicle.getMileage()!=0){
			stmt.setInt(4, vehicle.getMileage());
		}
		else{
			stmt.setNull(4, java.sql.Types.INTEGER);
		}
		
		
		if(vehicle.getRegistrationTag()!=null){
			stmt.setString(5, vehicle.getRegistrationTag());
		}
		else{
			throw new RARException("VehicleManager: can't save a vehicle: Tag undefined");
		}
		
		
		if(vehicle.getLastServiced()!=null){
			java.util.Date jDate = vehicle.getLastServiced();
			java.sql.Date sDate = new java.sql.Date( jDate.getTime() );
			stmt.setDate(6, sDate);
		}
		else{
			stmt.setNull(6, java.sql.Types.DATE);
		}
		
		
		if(vehicle.getStatus()!=null){
			stmt.setString(7, vehicle.getStatus().toString());
		}
		else{
			stmt.setNull(7, java.sql.Types.VARCHAR);
		}
		
		
		if(vehicle.getCondition()!=null){
			stmt.setString(8, vehicle.getCondition().toString());
		}
		else{
			stmt.setNull(8, java.sql.Types.VARCHAR);
		}
		
		
		if(vehicle.getRentalLocation()!=null){
			stmt.setString(9, vehicle.getRentalLocation().toString());
		}
		
		else{
			stmt.setNull(9, java.sql.Types.VARCHAR);
		}
		
		
		if(vehicle.getVehicleType()!=null){
			stmt.setString(10, vehicle.getVehicleType().toString());
		}
		else{
			stmt.setNull(10, java.sql.Types.VARCHAR);
		}
		
		if(vehicle.isPersistent()){
			stmt.setLong(11, vehicle.getId());
		}
		
		inscnt=stmt.executeUpdate();
		
		if(!vehicle.isPersistent()){
			//if it is being created for the first time we need to assign an id
			if(inscnt>=1){
				String sql="select last_insert_id()";
				if(stmt.execute(sql)){
					//retrieve result
					ResultSet r=stmt.getResultSet();
					//use only the first row
					while(r.next()){
						vehicleId=r.getLong(1);
						if(vehicleId>0){
							vehicle.setId(vehicleId);
						}//if
					}//while
				}//if
			}//if
		
			else{
				if(inscnt<1){
					throw new RARException("VehicleManager.save: failed to save a vehicle");
				}//if
			}//else
			
		}//if
		
		
		}//try
		catch(SQLException e){
			e.printStackTrace();
			throw new RARException("VehicleManager save failed. Failed to save person: "+e);
		}
		
	}
	
	public void delete(Vehicle vehicle) throws RARException{
		String deleteVehicleSql="Delete from vehicle where id = ?";
		PreparedStatement stmt=null;
		int inscnt;
		
		if(!vehicle.isPersistent()){
			return;//not in db so no need to delete anyways
		}
		
		else{
			try{
				 stmt = (PreparedStatement) conn.prepareStatement( deleteVehicleSql );         
		            stmt.setLong( 1, vehicle.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "VehicleManager.delete: failed to delete a Vehicle" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "VehicleManager.delete: failed to delete a Vehicle: " + e );       
		        }
		}
	}
	
	public List<Vehicle> restore(Vehicle vehicle) 
            throws RARException
    {
        //String       selectClubSql = "select id, name, address, established, founderid from club";
        String       selectVSql = "select tag, lastServiced, make, mileage, model, rentalLocationID, " + 
        							 " vehicleStatus, vehicleTypeID, year, vehicleCondition, id from vehicle" ;
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Vehicle>vehicles=new ArrayList<Vehicle>();
        
        
        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectVSql );
        
        if( vehicle != null ) {
            if( vehicle.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " and id = " + vehicle.getId() );
            else if( vehicle.getRegistrationTag() != null ) // userName is unique, so it is sufficient to get a person
                query.append( " and tag = '" + vehicle.getRegistrationTag() + "'" );
            else {

                if( vehicle.getLastServiced() != null )
                    query.append( " and lastServiced = '" + vehicle.getLastServiced() + "'" );   
                
                if( vehicle.getMake() != null )
                    query.append( " and make = '" + vehicle.getMake() + "'" );   
                
                if( vehicle.getMileage() != 0 )
                    query.append( " and mileage = '" + vehicle.getMileage() + "'" );   
                
                if( vehicle.getModel() != null )
                    query.append( " and model = '" + vehicle.getModel() + "'" );   
                
                if( vehicle.getRentalLocation() != null )
                    query.append( " and rentalLocationID = '" + vehicle.getRentalLocation() + "'" );   
                
                if( vehicle.getStatus() != null )
                    query.append( " and status = '" + vehicle.getStatus() + "'" );   
                
                if( vehicle.getVehicleType() != null )
                    query.append( " and vehicleTypeID = '" + vehicle.getVehicleType() + "'" );   
                
                if( vehicle.getYear()!= 0 )
                    query.append( " and year = '" + vehicle.getYear() + "'" );   
                
                if( vehicle.getCondition() != null )
                    query.append( " and vehicleCondition = '" + vehicle.getCondition() + "'" );  
                
                /*
                if( club.getEstablishedOn() != null ) {
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append( " established = '" + club.getEstablishedOn() + "'" );
                }
                */
                /*
                if( condition.length() > 0 ) {
                    query.append(  " where " );
                    query.append( condition );
                }
                */
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
                String make;
                String model;
                int year;
                int mileage;
                String tag="";
                Date lastServiced;
                
            
                String rentalLocation;
                String vehicleStatus;
                String vehicleType;
                String vehicleCondition;
                RentalLocation rentalLoc = null; 
                VehicleStatus vehicleStat = null;
                VehicleType vehicleT = null;
                VehicleCondition vehicleCond = null;
                Vehicle vehicle1 = null;
                
                while(rs.next()){
                	id = rs.getLong("1");
                    tag = rs.getString( "tag" );
                    lastServiced = rs.getDate( "lastServiced" );
                    make = rs.getString( "make" );
                    mileage = rs.getInt( "mileage");
                    model = rs.getString( "model" );
                    rentalLocation = rs.getString( "rentalLocationID" );
                    vehicleStatus = rs.getString( "vehicleStatus" );
                    vehicleType= rs.getString( "vehicleTypeID" );
                    year = rs.getInt( "year" );
                    vehicleCondition = rs.getString("vehicleCondition");
                    
                    vehicleT = objectLayer.createVehicleType(vehicleType);
                    rentalLoc = objectLayer.createRentalLocation(rentalLocation, null, -1);
                    vehicleCond = VehicleCondition.valueOf(vehicleCondition);
                    vehicleStat = VehicleStatus.valueOf(vehicleStatus);
                    
                    
                    
                    Vehicle vehicle2 = objectLayer.createVehicle( make,  model, year, tag, mileage,  lastServiced, vehicleT, 
                			  rentalLoc, vehicleCond, vehicleStat);
                    vehicle2.setId(id);
                    vehicles.add(vehicle2);
                }
                
                
              return vehicles;  
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "VehicleManager.restore: Could not restore persistent Vehicle object; Root cause: " + e );
        }

        throw new RARException( "VehicleManager.restore: Could not restore persistent Vehicle object" );
    }
	
	
	
	
	
}
