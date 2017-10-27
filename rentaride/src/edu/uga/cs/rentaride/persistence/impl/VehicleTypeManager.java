
package edu.uga.cs.rentaride.persistence.impl

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;

//import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;

class VehicleTypeManager {
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public VehicleTypeManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}
	
	public void save(VehicleType type) 
		throws RARException {
		String insertLocationSql = "insert into vehicle type (name, priceID) values (?, ?)";
		
		String updateLocationSql = "update rental location set name = ?, priceID= ?, where id = ?";
		
		PreparedStatement stmt;
		int inscnt;
		long locationId;
		
		try {
			
			if (!type.isPersistent()) 
				stmt = (PreparedStatement) conn.prepareStatement(insertVehicleTypeSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateVehicleTypeSql);
			
			if (type.getName() != null)
				stmt.setString(1, type.getName());
			else 
				throw new RARException("VehicleTypeManager.save: can't save a Rental Location: name undefined");
			
			if (type.getHourlyPrice() != null) 
				stmt.setString(2, type.getHourlyPrice());
			else 
				throw new RARException("RentalLocationManager.save: can't save a Rental Location: address undefined");
			
			if (location.isPersistent())
				stmt.setLong(3, type.getId());
			
			inscnt = stmt.executeUpdate();
			
			if(!location.isPersistent() ) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						ResultSet r = stmt.getResultSet();
						while(r.next()) {
							typeId = r.getLong(1);
							if(typeId > 0)
								type.setId(typeId);
						}
					}
				}
				else {
					if(inscnt < 1)
						throw new RARException("VehicleType.save: failed to save a Vehicle Type");
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("VehicleType.save: failed to save a Vehicle Type: " + e);
		}
	}
	
	public List<VehicleType> restore(VehicleType modelVehicleType)
		throws RARException {
		String selectVehicleTypeSql = "select id, name, hourlyPrice from vehicle type";
		Statement stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<VehicleType> types = new ArrayList<VehicleType>();
		
		condition.setLength(0);
		
		query.append(selectVehicleTypeSql);
		
		if(modelVehicleType !=null) {
			if(modelVehicleType.getId() >= 0) 
				query.append(" where id = " + modelVehicleType.getId());
			else if(modelVehicleType.getName() != null) 
				query.append(" where name = '" + modelVehicleType.getName() + "'");
			else {
				if(condition.length() > 0) {
					query.append(" where ");
					query.append(condition);
				}
			}
		}
		
		try {
			
			stmt = conn.createStatement();
			
			if(stmt.execute(query.toString())) {
				ResultSet rs = stmt.getResultSet();
				long id;
				String name;
        HourlyPrice hourlyPrice;
				
				while(rs.next()) {
					id = rs.getLong(1);
					name = rs.getString(2);
					price = rs.getHourlyPrice(3);
					
					VehicleType type = objectLayer.createVehicleType(name);
					type.setId(id);
					
					type.add(type);
				}
				return types;
			}
		}
		catch(Exception e) {
			throw new RARException("VehicleTypeManager.restore: Could not restore persistent Vehicle Type object; Root cause: " + e);
		}
	}
	
	
	public void delete(VehicleType type ) 
		throws RARException {
		
		String deleteVehicleTypeSql = "delete from vehicle type where id = ?";
		PreparedStatement stmt = null;
		int inscnt;
		
		if(!location.isPersistent())
			return;
		try {
			stmt = (PreparedStatement) conn.prepareStatement(deleteVehicleTypeSql);
			stmt.setLong(1,type.getId());
			inscnt = stmt.executeUpdate();
			if(inscnt == 1) 
				return;
			else
				throw new RARException("VehicleTypeManager.delete: failed to delete a Vehicle Type");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("VehicleTypeManager.delete: failed to delete a Vehicle Type: " + e);
		}
		
		
	}
	
}
