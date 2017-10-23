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
			throw new RARException( "VehicleManager.save: can't save a Vehicle: make undefined" );
		}
		
		
		if(vehicle.getModel()!=null){
			
		}
		else{
			
		}
		
		
		if(vehicle.getYear()!=0){
			
		}
		else{
			
		}
		
		
		if(vehicle.getMileage()!=0){
			
		}
		else{
			
		}
		
		
		if(vehicle.getRegistrationTag()!=null){
			
		}
		else{
			
		}
		
		
		if(vehicle.getLastServiced()!=null){
			
		}
		else{
			
		}
		
		
		if(vehicle.getStatus()!=null){
			
		}
		else{
			
		}
		
		
		if(vehicle.getCondition()!=null){
			
		}
		else{
			
		}
		
		
		if(vehicle.getRentalLocation()!=null){
			
		}
		
		else{
			
		}
		
		
		if(vehicle.getVehicleType()!=null){
			
		}
		else{
			
		}
		
		}//try
		catch(SQLException e){
			e.printStackTrace();
			throw new RARException("VehicleManager save failed. Failed to save person: "+e);
		}
		
	}
	
}
