package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentARideParams;

public class RentARideParamsImpl 
	extends Persistent
	implements RentARideParams{

	private static RentARideParamsImpl params = null;
	
	private int membershipPrice;
	private int lateFee;
	
	private RentARideParamsImpl() {
		membershipPrice = -1;
		lateFee = -1;
	}
	
	public static RentARideParamsImpl getInstance() {
		if(params == null){ 
			params = new RentARideParamsImpl();
		}
		return params;
	}
	
	@Override
	public int getMembershipPrice() {
		return membershipPrice;
	}

	@Override
	public void setMembershipPrice(int membershipPrice) throws RARException {
		if(membershipPrice < 0) {
			throw new RARException("Membership Price must be positive");
		}
		else {
			this.membershipPrice = membershipPrice;
		}
	}

	@Override
	public int getLateFee() {
		return lateFee;
	}

	@Override
	public void setLateFee(int lateFee) throws RARException {
		if(lateFee < 0) {
			throw new RARException("Late fee must be positive");
		}
		else {
			this.lateFee = lateFee;
		}
		
	}

}
