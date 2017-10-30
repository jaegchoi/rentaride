
package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.Persistable;
import edu.uga.cs.rentaride.RARException;

class HourlyPriceManager {
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public HourlyPriceManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}

	public void save(HourlyPrice hourlyPrice) 
		throws RARException {
		String insertHourlyPriceSql = "insert into hourlyPrice (maxHrs, price) values (?, ?)";
		
		String updateHourlyPriceSql = "update hourlyPrice set maxHrs = ?, price = ? where id = ?";
		
		PreparedStatement stmt;
		int inscnt;
		long hourlyPriceId;
		
		try {
			if (!hourlyPrice.isPersistent()) 
				stmt = (PreparedStatement) conn.prepareStatement(insertHourlyPriceSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateHourlyPriceSql);
			
			if (hourlyPrice.getMaxHours() != null)
				stmt.setInt(1, hourlyPrice.getMaxHours());
			else 
				throw new RARException("HourlyPriceManager.save: can't save a HourlyPrice: maxHrs undefined");
			
			if (hourlyPrice.getPrice() != null) {
				stmt.setInt(2, hourlyPrice.getPrice());
			}
			else 
				throw new RARException("HourlyPriceManager.save: can't save a HourlyPrice: text undefined");
			if (hourlyPrice.isPersistent())
				stmt.setLong(4, hourlyPrice.getId());
			
			inscnt = stmt.executeUpdate();
			
			if(!hourlyPrice.isPersistent() ) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						ResultSet r = stmt.getResultSet();
						while(r.next()) {
							hourlyPriceId = r.getLong(1);
							if(hourlyPriceId > 0)
								hourlyPrice.setId(hourlyPriceId);
						}
					}
				}
				else {
					if(inscnt < 1)
						throw new RARException("HourlyPriceManager.save: failed to save a HourlyPrice");
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("HourlyPrice.save: failed to save a Hourly Price: " + e);
		}
	}
	
	public List<HourlyPrice> restore(HourlyPrice modelHourlyPrice)
		throws RARException {
		String selectHourlyPriceSql = "select id, maxHrs, price from hourly price";
		Statement stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<HourlyPrice> comments = new ArrayList<HourlyPrice>();
		
		condition.setLength(0);
		
		query.append(selectHourlyPriceSql);
		
		if(modelHourlyPrice !=null) {
			if(modelHourlyPrice.getId() >= 0) 
				query.append(" where id = " + modelHourlyPrice.getId());
			else if(modelHourlyPrice.getMaxHours() != null) 
				query.append(" where max hours = '" + modelHourlyPrice.getMaxHours() + "'");
			else {
				query.append(" where price = '" + modelHourlyPrice.getPrice() + "'");
				}
		}
		try {
			
			stmt = conn.createStatement();
			
			if(stmt.execute(query.toString())) {
				ResultSet rs = stmt.getResultSet();
				long id;
				int maxHrs;
				int price;
				VehicleType vehicleType;
				
				while(rs.next()) {
					id = rs.getLong(1);
					maxHrs = rs.getInt(2);
					price = rs.getInt(3);
					//Not sure how to get Rental
					
					HourlyPrice price = objectLayer.createHourlyPrice(maxHrs, price, vehicleType);
					hourlyPrice.setId(id);
					
					hourlyPrice.add(hourlyPrice);
				}
				return hourlyPrice;
			}
		}
		catch(Exception e) {
			throw new RARException("HourlyPriceManager.restore: Could not restore persistent HourlyPrice object; Root cause: " + e);
		}
	}
	
	public VehicleType restoreVehicleType(HourlyPrice hourlyPrice) 
		throws RARException {
		
		String selectVehicleTypeSql = "select r.id, r.name, r.reservation, r.vehicle,"
				+ "where "+ "r.hourlyPriceid = c.id";
		Statement stmt = null;
		StringBuffer query  = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		
		condition.setLength(0);
		
		query.append(selectVehicleTypeSql);
		
		if(hourlyPrice != null) {
			if(hourlyPrice.getId() >= 0) {
				query.append(" and c.id = " + hourlyPrice.getId() + "'");
			}
			else {
				if(hourlyPrice.getMaxHours() != null) {
					condition.append(" and c.maxHrs = '" + hourlyPrice.getMaxHours() + "'");
				}
				if(hourlyPrice.getPrice() != null) {
					condition.append(" and c.price = '" + hourlyPrice.getPrice() + "'");
				}
				if(hourlyPrice.getVehicleType() != null) {
					condition.append(" and c.vehicleType = '" + hourlyPrice.getVehicleType() + "'");
				}
				if(condition.length() > 0) {
					query.append(condition);
				}
			}
		}
		try {
			
			stmt = conn.createStatement();
			
			if(stmt.execute(query.toString())) {
				
				ResultSet rs = stmt.getResultSet();
				
				long id;
				int maxHrs;
        int price;
        VehicleType vehicleType;
        String name;
			   
			    
			    while (rs.next()) {
			    	id = rs.getLong(1);
			    	maxHrs = rs.getInt(2);
			    	price = rs.getInt(3);
			    	
			    	
			    	vehicleType = objectLayer.createVehicleType();
			    	vehicleType.setId(id);
			    	vehicleType.setName(name);
			    }
			    return vehicleType;
			}
			else { 
				return null;
			}
		}
		catch(Exception e) {
			throw new RARException("HourlyPriceManager.restoreRental: Could not restore persistent "
					+ "hourlyPrice object; Root cause: " + e);
		}
		
	}
	
	public void delete(HourlyPrice hourlyPrice) 
		throws RARException {
		
		String deleteHourlyPriceSql = "delete from hourlyprice where id = ?";
		PreparedStatement stmt = null;
		int inscnt;
		
		if(!hourlyPrice.isPersistent())
			return;
		try {
			stmt = (PreparedStatement) conn.prepareStatement(deleteHourlyPriceSql);
			stmt.setLong(1, hourlyPrice.getId());
			inscnt = stmt.executeUpdate();
			if(inscnt == 1) 
				return;
			else
				throw new RARException("HourlyPriceManager.delete: failed to delete a HourlyPrice");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("HourlyPriceManager.delete: failed to delete a HourlyPrice: " + e);
		}
	}

	public VehicleType restoreVehicleTypeHourlyPrice(HourlyPrice hourlyPrice) {
		// TODO Auto-generated method stub
		return null;
	}
}
