package edu.uga.cs.rentaride.persistence.impl;

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
import edu.uga.cs.rentaride.entity.Customer;

public class CustomerManager {
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public CustomerManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}
	
	public void save(Customer customer) 
		throws RARException {
		String insertCustomerSql = "insert into customer (firstName, lastName, customerName, email, password, createDate, address, userStatus, memberUntil, state, licenseNumber, cardNumber, cardExpiration) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		String updateCustomerSql = "update customer set firstName = ?, lastName = ?, customerName = ?, email = ?, password = ?, createDate = ?, address = ?, customerStatus = ?, id = ?";
		
		PreparedStatement stmt;
		int inscnt;
		long customerId;
		
		try {
			
			if (!customer.isPersistent()) 
				stmt = (PreparedStatement) conn.prepareStatement(insertCustomerSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateCustomerSql);
			
			if (customer.getFirstName() != null)
				stmt.setString(1, customer.getFirstName());
			else 
				throw new RARException("CustomerManager.save: can't save a Customer: firstName undefined");
            
            if (customer.getLastName() != null)
				stmt.setString(1, customer.getLastName());
			else 
				throw new RARException("CustomerManager.save: can't save a Customer: lastName undefined");
			
            if (customer.getCustomerName() != null)
				stmt.setString(1, customer.getCustomerName());
			else 
				throw new RARException("CustomerManager.save: can't save a Customer: customerName undefined");
            
            if (customer.getEmail() != null)
				stmt.setString(1, customer.getEmail());
			else 
				throw new RARException("CustomerManager.save: can't save a Customer: email undefined");
            
            
            if (customer.getPassword() != null)
				stmt.setString(1, customer.getPassword());
			else 
				throw new RARException("CustomerManager.save: can't save a Customer: password undefined");
            
            if (customer.getCreateDate() != null)
				stmt.setDate(1, customer.getCreateDate());
			else 
				throw new RARException("CustomerManager.save: can't save a Customer: createDate undefined");
            
            
			if (customer.getAddress() != null) 
				stmt.setString(2, customer.getAddress());
			else 
				throw new RARException("CustomerManager.save: can't save a Customer: address undefined");
			
			if (customer.getCustomerStatus() == -1) 
				stmt.setUsterStatus(3, customer.getCustomerStatus());
			else
				throw new RARException("CustomerManager.save: can't save a Customer: userStatus undefined");
		
			if (customer.isPersistent())
				stmt.setLong(4, customer.getId());
			
			inscnt = stmt.executeUpdate();
			
			if(!customer.isPersistent() ) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						ResultSet r = stmt.getResultSet();
						while(r.next()) {
							customerId = r.getLong(1);
							if(customerId > 0)
								customer.setId(customerId);
						}
					}
				}
				else {
					if(inscnt < 1)
						throw new RARException("Customer.save: failed to save a Customer");
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("Customer.save: failed to save a Customer: " + e);
		}
	}
	
	public List<Customer> restore(Customer modelCustomer)
		throws RARException {
		String selectCustomerSql = "select id, firstName, lastName, customerName, email,  password, createDate, address, userStatus from customer";
		Statement stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<Customer> customers = new ArrayList<Customer>();
		
		condition.setLength(0);
		
		query.append(selectCustomerSql);
		
		if(modelCustomer !=null) {
			if(modelCustomer.getId() >= 0) 
				query.append(" where id = " + modelCustomer.getId());
			
            else if(modelCustomer.getFirstName() != null) 
				query.append(" where first name = '" + modelCustomer.getFirstName() + "'");
            
            else if(modelCustomer.getLastName() != null) 
				query.append(" where last name = '" + modelCustomer.getLastName() + "'");
            
            else if(modelCustomer.getCustomerName() != null) 
				query.append(" where customer name = '" + modelCustomer.getCustomerName() + "'");
            
            else if(modelCustomer.getEmail() != null) 
				query.append(" where email = '" + modelCustomer.getEmail() + "'");
            
            else if(modelCustomer.getPassword() != null) 
				query.append(" where password = '" + modelCustomer.getPassword() + "'");
            
            else if(modelCustomer.getAddress() != null) 
				query.append(" where address = '" + modelCustomer.getAddress() + "'");
            
            else if(modelCustomer.getDateCreated() != null) 
				query.append(" where date created = '" + modelCustomer.getDateCreated().toString() + "'");
            
            //else if(modelCustomer.getCustomerStatus() != null) 
				//query.append(" where customer status = '" + modelCustomer.getCustomerStatus() + "'");
			
            else (condition.length() > 0) {
					query.append(" where ");
					query.append(condition);
				}
			}
		
		
		try {
			
			stmt = conn.createStatement();
			
			if(stmt.execute(query.toString())) {
				ResultSet rs = stmt.getResultSet();
				long id;
				String firstName;
                String lastName;
                String customerName;
                String email;
                String password;
                String address;
                String dateCreated;
				
				while(rs.next()) {
					id = rs.getLong(1);
					firstName = rs.getString(2);
					lastName = rs.getString(3);
                    customerName = rs.getString(4);
                    email = rs.getString(5);
                    password = rs.getString(6);
                    address = rs.getString(7);
                    dateCreated = rs.getString(8);
                    
					
					Customer customer = objectLayer.createCustomer(firstName, lastName, customerName, email, password, address, dateCreated);
					customer.setId(id);
					
					customers.add(Customer);
				}
				return customers;
			}
		}
		catch(Exception e) {
			throw new RARException("CustomerManager.restore: Could not restore persistent Customer object; Root cause: " + e);
		}
	}
	
	public void delete(Customer customer ) 
		throws RARException {
		
		String deleteCustomerSql = "delete from comment where id = ?";
		PreparedStatement stmt = null;
		int inscnt;
		
		if(!customer.isPersistent())
			return;
		try {
			stmt = (PreparedStatement) conn.prepareStatement(deleteCustomerSql);
			stmt.setLong(1,customer.getId());
			inscnt = stmt.executeUpdate();
			if(inscnt == 1) 
				return;
			else
				throw new RARException("CustomerManager.delete: failed to delete a Customer");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("CustomerManager.delete: failed to delete a Customer: " + e);
		}
		
		
	}
	
}
