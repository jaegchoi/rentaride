package edu.uga.cs.rentaride.persistence.impl

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.Persistable;
import edu.uga.cs.rentaride.RARException;

class RentARideParamsManager {
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public RentARideParamsManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}

	public void save(RentARideParams param) 
		throws RARException {
		String insertRentARideParamsSql = "insert into param (membershipPrice, lateFee) values (?, ?)";
		
		String updateRentARideParamsSql = "update param set membershipPrice = ?, lateFee = ? where id = ?";
		
		PreparedStatement stmt;
		int inscnt;
		long paramId;
		
		try {
			if (!param.isPersistent()) 
				stmt = (PreparedStatement) conn.prepareStatement(insertRentARideParamsSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateRentARideParamsSql);
			
		if (param.getMembershipPrice() != -1)
				stmt.setInt(1, param.getMembershipPrice());
			else 
				throw new RARException("RentARideParamsManager.save: can't save a RentARideParams: membership price undefined");
			
			if (param.getLateFee() != -1) {
				stmt.setInt(2, param.getLateFee());
			}
			else 
				throw new RARException("RentARideParamsManager.save: can't save a RentARideParams: text undefined");
			if (param.isPersistent())
				stmt.setLong(4, param.getId());
			
			inscnt = stmt.executeUpdate();
			
			if(!param.isPersistent() ) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						ResultSet r = stmt.getResultSet();
						while(r.next()) {
							paramId = r.getLong(1);
							if(paramId > 0)
								param.setId(paramId);
						}
					}
				}
				else {
					if(inscnt < 1)
						throw new RARException("RentARideParamsManager.save: failed to save a RentARideParams");
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("RentalLocation.save: failed to save a Rental Location: " + e);
		}
	}
	
	public List<RentARideParams> restore(RentARideParams modelRentARideParams)
		throws RARException {
		String selectRentARideParamsSql = "select id, membership price, late fee from param";
		Statement stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<RentARideParams> params = new ArrayList<RentARideParams>();
		
		condition.setLength(0);
		
		query.append(selectRentARideParamsSql);
		
		if(modelRentARideParams !=null) {
			if(modelRentARideParams.getId() >= 0) 
				query.append(" where id = " + modelRentARideParams.getId());
			else if(modelRentARideParams.getMembershipPrice() != -1) 
				query.append(" where membership price = '" + modelRentARideParams.getMembershipPrice() + "'");
			else {
				if (modelRentARideParams.getLateFee() != -1 )
					if(condition.length() > 0)
						condition.append(" and");
					 condition.append(" date = '" + modelRentARideParams.getMembershipPrice() + "'");
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
				
				while(rs.next()) {
					id = rs.getLong(1);
					
					RentARideParams param = objectLayer.createRentARideParams();
					param.setId(id);
					
					params.add(param);
				}
				return params;
			}
		}
		catch(Exception e) {
			throw new RARException("RentARideParamsManager.restore: Could not restore persistent RentARideParams object; Root cause: " + e);
		}
	}
	
	public void delete(RentARideParams param) 
		throws RARException {
		
		String deleteRentARideParamsSql = "delete from param where id = ?";
		PreparedStatement stmt = null;
		int inscnt;
		
		if(!param.isPersistent())
			return;
		try {
			stmt = (PreparedStatement) conn.prepareStatement(deleteRentARideParamsSql);
			stmt.setLong(1, param.getId());
			inscnt = stmt.executeUpdate();
			if(inscnt == 1) 
				return;
			else
				throw new RARException("RentARideParamsManager.delete: failed to delete a RentARideParams");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("RentARideParamsManager.delete: failed to delete a RentARideParams: " + e);
		}
	}
}
