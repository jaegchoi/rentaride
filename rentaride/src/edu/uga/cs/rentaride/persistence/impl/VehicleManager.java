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
import edu.uga.cs.rentaride.entity.Vehicle;


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
	
	
}
