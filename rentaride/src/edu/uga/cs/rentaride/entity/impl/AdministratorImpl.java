package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.UserStatus;

public class AdministratorImpl
	extends UserImpl
	implements Administrator{

	public AdministratorImpl() throws RARException {
		this.setFirstName(null);
		this.setLastName(null);
		this.setUserName(null);
		this.setPassword(null);
		this.setEmail(null);
		this.setAddress(null);
		this.setCreateDate(null);
	}
	
	public AdministratorImpl(String firstName, String lastName, String userName, String password,
			String email, String address, Date createDate) throws RARException {
			this.setFirstName(firstName);
			this.setLastName(lastName);
			this.setUserName(userName);
			this.setPassword(password);
			this.setEmail(email);
			this.setAddress(address);
			this.setCreateDate(createDate);
	}
	
	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserName(String userName) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreateDate(Date createDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAddress(String address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserStatus getUserStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserStatus(UserStatus userStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPersistent() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
