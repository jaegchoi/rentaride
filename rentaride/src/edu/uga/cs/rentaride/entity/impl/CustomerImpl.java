package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.RARException;

public class CustomerImpl extends UserImpl implements Customer {
    private Date memberUntil;
    private String state;
    private String licenseNumber;
    private String cardNumber;
    private Date cardExpiration;
    private List<Reservation> reservations;
    private List<Comment> comments;
    private List<Rental> rentals;
    
    
    public CustomerImpl() {
        this.setFirstName(null);
        this.setLastName(null);
        this.setUserName(null);
		this.setPassword(null);
		this.setEmail(null);
		this.setAddress(null);
		this.setCreateDate(null);
        this.setUserStatus(null);
    	memberUntil = null;
    	state = null;
    	licenseNumber = null;
    	cardNumber = null;
    	cardExpiration = null;
    	reservations = null;
    	comments = null;
    	rentals = null;
    }
    
    public CustomerImpl (String firstName, String lastName, String userName, String password, String email, String address, Date createDate, Date membershipExpiration, String licenseState, String licenseNumber,String cardNumber, Date cardExpiration) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUserName(userName);
        this.setPassword(password);
        this.setEmail(email);
        this.setAddress(address);
        this.setCreateDate(createDate);
        this.setUserStatus(userStatus);
    	memberUntil = membershipExpiration;
    	state = licenseState;
    	this.licenseNumber = licenseNumber;
    	this.cardNumber = cardNumber;
    	this.cardExpiration = cardExpiration;
    	
    }
    
    
    public Date getMemberUntil(){
        return this.memberUntil;
    }
    
    public void setMemberUntil(Date memberUntil) throws RARException{
        this.memberUntil = memberUntil;    
    }
    
    public String getLicenseState(){
        return this.state;
    }
    
    public void setLicenseState(String state){
        this.state = state;
    }
    
    public String getLicenseNumber(){
        return this.licenseNumber;
    }
    
    public void setLicenseNumber(String licenseNumber){
        this.licenseNumber = licenseNumber;
    }
    
    public String getCreditCardNumber(){
        return this.cardNumber;
    }
    
    public void setCreditCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }
    
    public Date getCreditCardExpiration(){
        return this.cardExpiration;
    }
    
    public List<Reservation> getReservations(){
        return this.reservations;
    }
    
    public List<Comment> getComments(){
        return this.comments;
    }
    
    public List<Rental> getRentals(){
        return this.rentals;
    }


}
