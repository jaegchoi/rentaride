package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.object.ObjectLayer;


class AdministratorManager
{
    private ObjectLayer objectLayer = null;
    private Connection   conn = null;

    public AdministratorManager( Connection conn, ObjectLayer objectLayer )
    {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }

    public void save(Administrator administrator)
            throws RARException
    {
        String               insertASql = "insert into Administrator (createdDate, emailAddress, firstName, lastName, password, residenceAddress, userName, userStatus) values ( ?, ?, ?, ?, ?, ?, ?, ?)";
        String               updateASql = "update Administrator set createdDate = ?, emailAddress = ?, firstName = ?, lastName = ?, password = ?, residenceAddress = ?, userName = ?, userStatus = ? where id = ? ";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 CId;


        try {

            if( !administrator.isPersistent() )
                stmt = (PreparedStatement) conn.prepareStatement( insertASql );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updateASql );

            if( administrator.getCreatedDate() != null )
            	stmt.setDate( 1, (Date) administrator.getCreatedDate());
            else
                throw new RARException( "Administrator.save: can't save a Administrator: Created Date undefined" );

            if( administrator.getEmail() != null )
                stmt.setString( 2, administrator.getEmail() );
            else
                throw new RARException( "administrator.save: can't save a administrator: Email Address is not set or not persistent" );

            if( administrator.getFirstName() != null )
                stmt.setString( 3, administrator.getFirstName() );
            else
            	 throw new RARException( "administrator.save: can't save a administrator: First Name is not set or not persistent" );
       
            if( administrator.getLastName() != null )
                stmt.setString( 4, administrator.getLastName() );
            else
            	 throw new RARException( "administrator.save: can't save a administrator: Last Name is not set or not persistent" );
       
            if( administrator.getPassword() != null )
                stmt.setString( 5, administrator.getPassword() );
            else
            	 throw new RARException( "administrator.save: can't save a administrator: Password is not set or not persistent" );
       
            if( administrator.getAddress() != null ) // type is unique and non null
            	// This is me setting the value in col typeName in table VehicleType to the provided value
            	stmt.setString( 6, administrator.getAddress());
            else
                throw new RARException( "Administrator.save: can't save a Administrator: Residence undefined" );

            if( administrator.getUserName() != null )
                stmt.setString( 7, administrator.getUserName() );
            else
                throw new RARException( "administrator.save: can't save a administrator: UserName is not set or not persistent" );

            if( administrator.getUserStatus() != null )
                stmt.setString( 8, administrator.getUserStatus().toString() );
            else
            	 throw new RARException( "administrator.save: can't save a administrator: UserStatus is not set or not persistent" );
       
            if( administrator.isPersistent() )
                stmt.setLong( 9, administrator.getId() );

            inscnt = stmt.executeUpdate();

            if( !administrator.isPersistent() ) {
                if( inscnt >= 1 ) {
                    String sql = "select last_insert_id()";
                    if( stmt.execute( sql ) ) { // statement returned a result

                        // retrieve the result
                        ResultSet r = stmt.getResultSet();

                        // we will use only the first row!
                        //
                        while( r.next() ) {

                            // retrieve the last insert auto_increment value
                            CId = r.getLong( 1 );
                            if(CId > 0 )
                            	administrator.setId( CId ); // set this vehicleType's db id (proxy object)
                        }
                    }
                }
                else
                    throw new RARException( "AdministratorManager.save: failed to save a administrator" );
            }
            else {
                if( inscnt < 1 )
                    throw new RARException( "AdministratorManager.save: failed to save a administrator" );
            }
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "AdministratorManager.save: failed to save a Administrator: " + e );
        }
    }

    public List<Administrator> restore(Administrator administrator)
            throws RARException
    {
        String       selectASql = "select a.createdDate, a.emailAddress, a.firstName, a.lastName, a.password, a.residenceAddress, a.userName, a.userStatus " +
                                      " from Administrators a where ";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );

        // form the query based on the given Administrator object instance
        query.append( selectASql );

        if( administrator != null ) {
            if( administrator.getId() >= 0 ){ // id is unique, so it is sufficient to get a Administrator
                query.append( " and id = " + administrator.getId() );
            } else { 
            	if( administrator.getCreatedDate() != null ) // Type is unique, so it is sufficient to get a Administrator
            		condition.append( " and Created Date = '" + administrator.getCreatedDate().toString() + "'" );
                        
                if( administrator.getEmail() != null )
                    condition.append( " and Email Address = '" + administrator.getEmail() + "'" );

                if( administrator.getFirstName() != null )
                    condition.append( " and First Name = '" + administrator.getFirstName() + "'" );  
       
                if( administrator.getLastName() != null ) 
                    condition.append( " and Last Name = '" + administrator.getLastName() + "'" );
               
                if( administrator.getPassword() != null ) 
                    condition.append( " and Password = '" + administrator.getPassword() + "'" );
                
                if( administrator.getAddress() != null )
                    condition.append( " and Residence Address = '" + administrator.getAddress()  + "'" );

                if( administrator.getUserName() != null )
                    condition.append( " and User Name = '" + administrator.getUserName() + "'" );  
       
                if( administrator.getUserStatus() != null ) 
                    condition.append( " and User Status = '" + administrator.getUserStatus() + "'" );
               
                

            }
        }

        try {

            stmt = conn.createStatement();

            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet rs = stmt.getResultSet();
                long id;
            	String firstName;
            	String lastName;
            	String userName;
            	String emailAddress;
                String password;
                String address;
                Date createDate;
                Administrator admin = null;
                

                while( rs.next() ) {

                    try {
                    	id = rs.getLong("id");
                    	firstName = rs.getString("firstname");
                    	lastName = rs.getString("lastname");
                    	userName = rs.getString("username");
                    	password = rs.getString("passw");
                    	emailAddress = rs.getString("email");
                    	address=rs.getString("address");
                        createDate = rs.getDate("createdDate");
                        
                        
                    }
                    catch( Exception e ) {      // just in case...
                        throw new RARException( "AdministratorIterator: No next Administrator object; root cause: " + e );
                    }
                    
                    administrator = objectLayer.createAdministrator(firstName, lastName, userName,password 
                    		,emailAddress, address,createDate);
        			administrator.setId( id );

                    return (List<Administrator>) administrator;
                }
                
                
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "AdministratorManager.restore: Could not restore persistent Administrator object; Root cause: " + e );
        }

        throw new RARException( "AdministratorManager.restore: Could not restore persistent Administrator object" );
    }

    
    public void delete(Administrator administrator)
            throws RARException
    {
        String               deleteASql = "delete from Administrator where id = ?";
        PreparedStatement    stmt = null;
        int                  inscnt;

        if( !administrator.isPersistent() )
            return;

        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteASql );
            stmt.setLong( 1, administrator.getId() );
            inscnt = stmt.executeUpdate();
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "AdministratorManager.delete: failed to delete a Administrator" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "AdministratorManager.delete: failed to delete a Administrator: " + e );        }
    }
}
