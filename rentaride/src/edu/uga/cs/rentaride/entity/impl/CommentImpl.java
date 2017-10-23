package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;

public class CommentImpl 
	extends Persistent
	implements Comment{

	private String text;
	private Date date;
	private Rental rental;
	private Customer customer;
	
	public CommentImpl() {
		text = null;
		date = null;
	}
	
	public CommentImpl(String text, Date date) {
		this.text = text;
		this.date = date;
	}
	
	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Rental getRental() {
		return rental;
	}

	@Override
	public void setRental(Rental rental) throws RARException {
		if(rental == null) {
			throw new RARException("Rental cannot be null");
		}
		else {
			this.rental = rental;
		}
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}

}
