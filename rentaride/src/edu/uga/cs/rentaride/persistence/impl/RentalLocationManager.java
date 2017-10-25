package edu.uga.cs.rentaride.persistence.impl

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;

class RentalLocationManager {
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public RentalLocationManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}
	
	public void save(RentalLocation location) 
		throws RARException {
		String insertLocationSql = "insert into rental location (name, address, capacity) values (?, ?, ?)";
		
		String updateLocationSql = "update rental location set name = ?, address = ?, capacity = ? where id = ?";
		
		PreparedStatement stmt;
		int inscnt;
		long locationId;
		
		try {
			
			if (!location.isPersistent()) 
				stmt = (PreparedStatement) conn.prepareStatement(insertLocationSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateLocationSql);
			
			if (location.getName() != null)
				stmt.setString(1, location.getName());
			else 
				throw new RARException("RentalLocationManager.save: can't save a Rental Location: name undefined");
			
			if (location.getAddress() != null) 
				stmt.setString(2, location.getAddress());
			else 
				throw new RARException("RentalLocationManager.save: can't save a Rental Location: address undefined");
			
			if (location.getCapacity() == -1) 
				stmt.setLong(3, location.getCapacity());
			else
				throw new RARException("RentalLocationManager.save: can't save a Rental Location: capacity undefined");
		
			if (location.isPersistent())
				stmt.setLong(4, location.getId());
			
			inscnt = stmt.executeUpdate();
			
			if(!location.isPersistent() ) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						ResultSet r = stmt.getResultSet();
						while(r.next()) {
							locationId = r.getLong(1);
							if(locationId > 0)
								location.setId(locationId);
						}
					}
				}
				else {
					if(inscnt < 1)
						throw new RARException("RentalLocation.save: failed to save a Rental Location");
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("RentalLocation.save: failed to save a Rental Location: " + e);
		}
	}
	
	public List<RentalLocation> restore(RentalLocation modelLocation)
		throws RARException {
		String selectLocationSql = "select id, name, address, capacity from rental location";
		Statement stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<RentalLocation> locations = new ArrayList<RentalLocation>();
		
		condition.setLength(0);
		
		query.append(selectLocationSql);
		
		if(modelLocation !=null) {
			if(modelLocation.getId() >= 0) 
				query.append(" where id = " + modelLocation.getId());
			else if(modelLocation.getName() != null) 
				query.append(" where name = '" + modelLocation.getName() + "'");
			else {
				if (modelLocation.getAddress() != null )
					 condition.append(" address = '" + modelLocation.getAddress() + "'");
				if(modelLocation.getCapacity() != -1) {
					if(condition.length() > 0)
						condition.append(" and");
					condition.append(" capacity ' '" + modelLocation.getCapacity() + "'");
				}
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
				String address;
				int capacity;
				
				while(rs.next()) {
					id = rs.getLong(1);
					name = rs.getString(2);
					capacity = rs.getInt(3);
					
					RentalLocation location = objectLayer.createRentalLocation(name, address, capacity);
					location.setId(id);
					
					locations.add(location);
				}
				return locations;
			}
		}
		catch(Exception e) {
			throw new RARException("RentalLocationManager.restore: Could not restore persistent Rental Location object; Root cause: " + e);
		}
	}
	
	public void delete(RentalLocation location ) 
		throws RARException {
		
		String deleteLocationSql = "delete from comment where id = ?";
		PreparedStatement stmt = null;
		int inscnt;
		
		if(!location.isPersistent())
			return;
		try {
			stmt = (PreparedStatement) conn.prepareStatement(deleteLocationSql);
			stmt.setLong(1,location.getId());
			inscnt = stmt.executeUpdate();
			if(inscnt == 1) 
				return;
			else
				throw new RARException("RentalLocationManager.delete: failed to delete a Rental Location");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("RentalLocationManager.delete: failed to delete a Rental Location: " + e);
		}
		
		
	}
	
}

