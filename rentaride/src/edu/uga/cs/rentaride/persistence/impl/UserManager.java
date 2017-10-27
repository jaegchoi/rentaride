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


import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;

class UserManager {
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public UserManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}
	
	public void save(UserManager user) 
		throws RARException {
		String insertUserSql = "insert into user (firstName, lastName, userName, email, password, createDate, address, userStatus) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		String updateUserSql = "update user set firstName = ?, lastName = ?, userName = ?, email = ?, password = ?, createDate = ?, address = ?, userStatus = ?, id = ?";
		
		PreparedStatement stmt;
		int inscnt;
		long userId;
		
		try {
			
			if (!user.isPersistent()) 
				stmt = (PreparedStatement) conn.prepareStatement(insertUserSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateUserSql);
			
			if (user.getFirstName() != null)
				stmt.setString(1, user.getFirstName());
			else 
				throw new RARException("UserManager.save: can't save a User: firstName undefined");
            
            if (user.getLastName() != null)
				stmt.setString(1, user.getLastName());
			else 
				throw new RARException("UserManager.save: can't save a User: lastName undefined");
			
            if (user.getUserName() != null)
				stmt.setString(1, user.getUserName());
			else 
				throw new RARException("UserManager.save: can't save a User: userName undefined");
            
            if (user.getEmail() != null)
				stmt.setString(1, user.getEmail());
			else 
				throw new RARException("UserManager.save: can't save a User: email undefined");
            
            
            if (user.getPassword() != null)
				stmt.setString(1, user.getPassword());
			else 
				throw new RARException("UserManager.save: can't save a User: password undefined");
            
            if (user.getCreateDate() != null)
				stmt.setDate(1, user.getCreateDate());
			else 
				throw new RARException("UserManager.save: can't save a User: createDate undefined");
            
            
			if (user.getAddress() != null) 
				stmt.setString(2, user.getAddress());
			else 
				throw new RARException("UserManager.save: can't save a User: address undefined");
			
			if (user.getUserStatus() == -1) 
				stmt.setUsterStatus(3, user.getUserStatus());
			else
				throw new RARException("UserManager.save: can't save a User: userStatus undefined");
		
			if (user.isPersistent())
				stmt.setLong(4, user.getId());
			
			inscnt = stmt.executeUpdate();
			
			if(!user.isPersistent() ) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						ResultSet r = stmt.getResultSet();
						while(r.next()) {
							userId = r.getLong(1);
							if(userId > 0)
								user.setId(userId);
						}
					}
				}
				else {
					if(inscnt < 1)
						throw new RARException("User.save: failed to save a User");
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("User.save: failed to save a User: " + e);
		}
	}
	
	public List<User> restore(User modelUser)
		throws RARException {
		String selectUserSql = "select id, firstName, lastName, userName, email,  password, createDate, address, userStatus from user";
		Statement stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<User> users = new ArrayList<User>();
		
		condition.setLength(0);
		
		query.append(selectUserSql);
		
		if(modelUser !=null) {
			if(modelUser.getId() >= 0) 
				query.append(" where id = " + modelUser.getId());
			
            else if(modelUser.getFirstName() != null) 
				query.append(" where first name = '" + modelUser.getFirstName() + "'");
            
            else if(modelUser.getLastName() != null) 
				query.append(" where last name = '" + modelUser.getLastName() + "'");
            
            else if(modelUser.getUserName() != null) 
				query.append(" where user name = '" + modelUser.getUserName() + "'");
            
            else if(modelUser.getEmail() != null) 
				query.append(" where email = '" + modelUser.getEmail() + "'");
            
            else if(modelUser.getPassword() != null) 
				query.append(" where password = '" + modelUser.getPassword() + "'");
            
            else if(modelUser.getAddress() != null) 
				query.append(" where address = '" + modelUser.getAddress() + "'");
            
            else if(modelUser.getDateCreated() != null) 
				query.append(" where date created = '" + modelUser.getDateCreated().toString() + "'");
            
            //else if(modelUser.getUserStatus() != null) 
				//query.append(" where user status = '" + modelUser.getUserStatus() + "'");
			
            else (condition.length() > 0) {
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
				String firstName;
                String lastName;
                String userName;
                String email;
                String password;
                String address;
                String dateCreated;
				
				while(rs.next()) {
					id = rs.getLong(1);
					firstName = rs.getString(2);
					lastName = rs.getString(3);
                    userName = rs.getString(4);
                    email = rs.getString(5);
                    password = rs.getString(6);
                    address = rs.getString(7);
                    dateCreated = rs.getString(8);
                    
					
					User user = objectLayer.createUser(firstName, lastName, userName, email, password, address, dateCreated);
					user.setId(id);
					
					users.add(User);
				}
				return users;
			}
		}
		catch(Exception e) {
			throw new RARException("UserManager.restore: Could not restore persistent User object; Root cause: " + e);
		}
	}
	
	public void delete(User user ) 
		throws RARException {
		
		String deleteUserSql = "delete from comment where id = ?";
		PreparedStatement stmt = null;
		int inscnt;
		
		if(!user.isPersistent())
			return;
		try {
			stmt = (PreparedStatement) conn.prepareStatement(deleteUserSql);
			stmt.setLong(1,user.getId());
			inscnt = stmt.executeUpdate();
			if(inscnt == 1) 
				return;
			else
				throw new RARException("UserManager.delete: failed to delete a User");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("UserManager.delete: failed to delete a User: " + e);
		}
		
		
	}
	
}
