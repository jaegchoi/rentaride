import java.io.IOException;
import java.io.PrintWriter;

import java.io.*;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import freemarker.template.*;

@WebServlet("/RentARide")
    public class RentARideServlet extends HttpServlet{
	
	//need to implement logoff button (not hard)
	
	//need to use link from admin successful logon create homepage.ftl

	//allow admin to create a rental location, then display rental location to verify.
	//this is a simple create form. then once they create you can obtain all RL and put in a table, push to ftl.

	private String templatePath=null;
	private String templateDir="WEB-INF/templates";
	private String templateName="";
	private Configuration cfg;
	private Connection con=null;
	private Statement stmt=null;
	private String dbURL="jdbc:mysql://uml.cs.uga.edu:3306/team7";
	private String dbUname="team7";
	private String dbPassw="overload";
	private String activeUser="";

	public void init(){
	    cfg=new Configuration();
	    cfg.setServletContextForTemplateLoading(getServletContext(),templateDir);
	}//init




	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	    
	    	    
	    PrintWriter out=null;
	    res.setContentType("type/html");

	    String loginPage=req.getParameter("requestLogin");
	    String signUpPage=req.getParameter("requestSignUp");
	    String adminLoginPage=req.getParameter("requestAdminLogin");
	    String adminLoggedIn=req.getParameter("adminLoggedIn");
	    String customerLoggedIn=req.getParameter("customerLoggedIn");
	    String customerSignedUp=req.getParameter("customerSignedUp");
	    String toCustomerHomePage=req.getParameter("toCustomerHomePage");
	    String toAdminHomePage=req.getParameter("toAdminHomePage");
	    String clickedLogOff=req.getParameter("clickedLogOff");
	    String toCreateRentalLocation=req.getParameter("toCreateRentalLocation");
	    String createRentalLocation=req.getParameter("createRentalLocation");
	    String clickedAdminHomePage=req.getParameter("clickedAdminHomePage");
	    String toCreateVehicle=req.getParameter("toCreateVehicle");
	    String toCreateVehicleType=req.getParameter("toCreateVehicleType");

	    if(loginPage!=null){
		//call some sort of login method that will provide the proper ftl
		showLoginPage(req,res);
	    }//if

	    else if(signUpPage!=null){
		//call some sort of signUp method that will provide the proper ftl
		showSignUpPage(req,res);
	    }//else if
	    
	    else if(adminLoginPage!=null){
		//call some method to display an admin login page.
		showAdminLoginPage(req,res);
	    }//else if

	    else if(adminLoggedIn!=null){
		adminLoggedInPage(req,res);
	    }//else if

	    else if(customerLoggedIn!=null){
		customerLoggedInPage(req,res);
	    }//else if

	    else if(customerSignedUp!=null){
		customerSignedUpPage(req,res);
	    }//else if

	    else if(toCustomerHomePage!=null){
		toCustomerHomePage(req,res);
	    }//else if

	    else if(toAdminHomePage!=null){
		toAdminHomePage(req,res);
	    }//else if

	    else if(clickedLogOff!=null){
		logOff(req,res);
	    }//else if
	    
	    else if(toCreateRentalLocation!=null){
		loadCreateRentalLocationPage(req,res);
	    }//else if
	    
	    else if(createRentalLocation!=null){
		createRentalLocation(req,res);
	    }//else if
	    
	    else if(clickedAdminHomePage!=null){
		toAdminHomePage(req,res);
	    }//else if

	    else if(toCreateVehicle!=null){
		loadCreateVehiclePage(req,res);
	    }//else if

	    else if(toCreateVehicleType!=null){
		loadCreateVehicleTypePage(req,res);
	    }//else if

	    out.close();

	}//doPost


	public void showLoginPage(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	    //will display that correct ftl file
	    Template template;
	    templateName="customerLoginPage.ftl";
	    templatePath=templateDir+"/"+templateName;
	    Map<String,String>root=new HashMap<String,String>();
	    
	    
	    try{
		template=cfg.getTemplate("customerLoginPage.ftl");
	    }//try
	    catch(IOException e){
		throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
	    }//catch
	    
	    res.setContentType("text/html; charset="+template.getEncoding());
	    Writer out=res.getWriter();
	    
	    //merge the data model and template
	    try{
		template.process(root,out);
	    }catch(TemplateException e){
		throw new ServletException("Error while processing Freemarker template",e);
	    }

	     out.close();

	}//showLoginPage

	
	public void showSignUpPage(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	    //will display that correct ftl file
	    Template template;
	    templateName="customerSignUpPage.ftl";
	    templatePath=templateDir+"/"+templateName;
	    Map<String,String>root=new HashMap<String,String>();
	    
	    
	    try{
		template=cfg.getTemplate("customerSignUpPage.ftl");
	    }//try
	    catch(IOException e){
		throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
	    }//catch
	    
	    res.setContentType("text/html; charset="+template.getEncoding());
	    Writer out=res.getWriter();
	    
	    //merge the data model and template
	    try{
		template.process(root,out);
	    }catch(TemplateException e){
		throw new ServletException("Error while processing Freemarker template",e);
	    }
	     out.close();
	    
	}//showSignUpPage

	public void showAdminLoginPage(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	    //will display that correct ftl file
	    Template template;
	    templateName="adminLoginPage.ftl";
	    templatePath=templateDir+"/"+templateName;
	    Map<String,String>root=new HashMap<String,String>();
	    
	    
	    try{
		template=cfg.getTemplate("adminLoginPage.ftl");
	    }//try
	    catch(IOException e){
		throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
	    }//catch
	    
	    res.setContentType("text/html; charset="+template.getEncoding());
	    Writer out=res.getWriter();
	    
	    //merge the data model and template
	    try{
		template.process(root,out);
	    }catch(TemplateException e){
		throw new ServletException("Error while processing Freemarker template",e);
	    }

	     out.close();
	}//showSignUpPage

	public void adminLoggedInPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	    //check to see if user name exists in administrator table in database
	    //if it does check to see if password = password from database where username='username'
	    //if it does take them to admin home page ftl
	    //if not then load an error message or ftl page
	    String username=req.getParameter("username");
	    String password=req.getParameter("password");
	    Template template;
	    
	    try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con=DriverManager.getConnection(dbURL,dbUname,dbPassw);
		stmt=con.createStatement();
	    }
	    catch(InstantiationException e1){
		e1.printStackTrace();
	    }
	    catch(SQLException e2){
		e2.printStackTrace();
	    }
	    catch(ClassNotFoundException e3){
		e3.printStackTrace();
	    }
	    catch(IllegalAccessException e4){
		e4.printStackTrace();
	    }

	    String findUserQuery="SELECT username, passw FROM administratorHierarchy WHERE username='"+username+"';";
	    String databaseUname="";
	    String databasePassw="";
	    
	    try{
		if(stmt.execute(findUserQuery)){
		    ResultSet rs1=stmt.getResultSet();
		    while(rs1.next()){
			databaseUname=rs1.getString("username");
			databasePassw=rs1.getString("passw");
		    }//while
		}//if
	    
	    else{
		//out.println("error in execute statement");
	    }
	    }//try
	    catch(Exception e){
		e.printStackTrace();
	    }
	    
	    if(!databaseUname.equals(username)){
		// they are not an admin in the database deny access
		//send to an error ftl
		templateName="notAnAdminPage.ftl";
		templatePath=templateDir+"/"+templateName;
		Map<String,String>root=new HashMap<String,String>();
		
		try{
		    template=cfg.getTemplate("notAnAdminPage.ftl");
		}//try                                                                                                                           
		catch(IOException e){
		    throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
		}//catch                                                                                                                         
		
		res.setContentType("text/html; charset="+template.getEncoding());
		Writer out=res.getWriter();
		
		//merge the data model and template                                                                                              
		try{
		    template.process(root,out);
		}catch(TemplateException e){
		    throw new ServletException("Error while processing Freemarker template",e);
		}
		out.close();
		 

	    }
		
	    //their username is right. check to see if the password is same
	    else{
		if(!databasePassw.equals(password)){
		    //there password does not match what we have in the db
		    //produce error.ftl
		    templateName="incorrectPassword.ftl";
		    templatePath=templateDir+"/"+templateName;
		    Map<String,String>root=new HashMap<String,String>();

		    try{
			template=cfg.getTemplate("incorrectPassword.ftl");
		    }//try                                                                                                                  
		    catch(IOException e){
			throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
		    }//catch
		    res.setContentType("text/html; charset="+template.getEncoding());
		    Writer out=res.getWriter();

		    //merge the data model and template
		    try{
			template.process(root,out);
		    }catch(TemplateException e){
			throw new ServletException("Error while processing Freemarker template",e);
		    }
		    out.close();



		}//if
		else{
		    //they should login successful. display admin home page.
		    //set active user
		    //tell them login successful 
		    activeUser=username;
		    templateName="adminHomePage.ftl";
		    templatePath=templateDir+"/"+templateName;
		    Map<String,String>root=new HashMap<String,String>();

		    try{
			template=cfg.getTemplate("adminHomePage.ftl");
		    }//try                                                                                                                  
		    catch(IOException e){
			throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
		    }//catch
		    res.setContentType("text/html; charset="+template.getEncoding());
		    Writer out=res.getWriter();
		    root.put("username",username);
		    //merge the data model and template
		    try{
			template.process(root,out);
		    }catch(TemplateException e){
			throw new ServletException("Error while processing Freemarker template",e);
		    }
		    out.close();
		}//else
		
	    }//else


	    //out.close();
	    try{
		if(con!=null){
		    con.close();
		}
		if(stmt!=null){
		    stmt.close();
		}
	    }
	    catch(SQLException e){
		e.printStackTrace();
	    }

	}//adminLoggedInPage


	public void customerLoggedInPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	    //String userName=req.getParameter("userName");
	    //String password=req.getParameter("password");
	    //run sql query to see if user name exists
	    //if it exists, check to see if password = SELECT password FROM customer WHERE username='username';
	    //if it does then allow the log in and show the home page for customer
	    //if it doesn't then display an error ftl and don't allow the log in.
	    // Class.forName("com.mysql.jdbc.Driver").newInstance();
	    //con=DriverManager.getConnection(dbURL,dbUname,dbPassw);
	    //stmt=con.createStatement();

	    String username=req.getParameter("username");
	    String password=req.getParameter("password");
	    Template template;
	    
	    try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con=DriverManager.getConnection(dbURL,dbUname,dbPassw);
		stmt=con.createStatement();
	    }
	    catch(InstantiationException e1){
		e1.printStackTrace();
	    }
	    catch(SQLException e2){
		e2.printStackTrace();
	    }
	    catch(ClassNotFoundException e3){
		e3.printStackTrace();
	    }
	    catch(IllegalAccessException e4){
		e4.printStackTrace();
	    }

	    String findUserQuery="SELECT username, passw FROM customerHierarchy WHERE username='"+username+"';";
	    String databaseUname="";
	    String databasePassw="";
	    
	    try{
		if(stmt.execute(findUserQuery)){
		    ResultSet rs1=stmt.getResultSet();
		    while(rs1.next()){
			databaseUname=rs1.getString("username");
			databasePassw=rs1.getString("passw");
		    }//while
		}//if
	    
	    else{
		//out.println("error in execute statement");
	    }
	    }//try
	    catch(Exception e){
		e.printStackTrace();
	    }
	    
	    if(!databaseUname.equals(username)){
		// they are not an admin in the database deny access
		//send to an error ftl
		templateName="notACustomerPage.ftl";
		templatePath=templateDir+"/"+templateName;
		Map<String,String>root=new HashMap<String,String>();
		
		try{
		    template=cfg.getTemplate("notACustomerPage.ftl");
		}//try                                                                                                                           
		catch(IOException e){
		    throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
		}//catch                                                                                                                         
		
		res.setContentType("text/html; charset="+template.getEncoding());
		Writer out=res.getWriter();
		
		//merge the data model and template                                                                                              
		try{
		    template.process(root,out);
		}catch(TemplateException e){
		    throw new ServletException("Error while processing Freemarker template",e);
		}
		out.close();
		 

	    }
		
	    //their username is right. check to see if the password is same
	    else{
		if(!databasePassw.equals(password)){
		    //there password does not match what we have in the db
		    //produce error.ftl
		    templateName="incorrectCustomerPassword.ftl";
		    templatePath=templateDir+"/"+templateName;
		    Map<String,String>root=new HashMap<String,String>();

		    try{
			template=cfg.getTemplate("incorrectCustomerPassword.ftl");
		    }//try                                                                                                                  
		    catch(IOException e){
			throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
		    }//catch
		    res.setContentType("text/html; charset="+template.getEncoding());
		    Writer out=res.getWriter();

		    //merge the data model and template
		    try{
			template.process(root,out);
		    }catch(TemplateException e){
			throw new ServletException("Error while processing Freemarker template",e);
		    }
		    out.close();



		}//if
		else{
		    //they should login successful. display admin home page.
		    //set active user
		    //tell them login successful 
		    activeUser=username;
		    templateName="customerWelcomeBackPage.ftl";
		    templatePath=templateDir+"/"+templateName;
		    Map<String,String>root=new HashMap<String,String>();

		    try{
			template=cfg.getTemplate("customerWelcomeBackPage.ftl");
		    }//try                                                                                                                  
		    catch(IOException e){
			throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
		    }//catch
		    res.setContentType("text/html; charset="+template.getEncoding());
		    Writer out=res.getWriter();
		    root.put("username",username);
		    //merge the data model and template
		    try{
			template.process(root,out);
		    }catch(TemplateException e){
			throw new ServletException("Error while processing Freemarker template",e);
		    }
		    out.close();
		}//else
		
	    }//else

	    //out.close();
	    try{
		if(con!=null){
		    con.close();
		}
		if(stmt!=null){
		    stmt.close();
		}
	    }
	    catch(SQLException e){
		e.printStackTrace();
	    }



	}//customerLoggedInPage

	public void customerSignedUpPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	    //String fname, lname, uname, passw, email, address
	    //check to see if uname already exists in database. if not allow them to be added to database
	    //if it has tell them they already have a profile
	    //show signup successful and load the same ftl from customerLoggedInPage
	    //Class.forName("com.mysql.jdbc.Driver").newInstance();
	    //con=DriverManager.getConnection(dbURL,dbUname,dbPassw);
	    //stmt=con.createStatement();
	    String firstName=req.getParameter("firstName");
	    String lastName=req.getParameter("lastName");
	    String username=req.getParameter("username");
	    String password=req.getParameter("password");
	    String email=req.getParameter("email");
	    
	    Template template;
	    
	    try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con=DriverManager.getConnection(dbURL,dbUname,dbPassw);
		stmt=con.createStatement();
	    }
	    catch(InstantiationException e1){
		e1.printStackTrace();
	    }
	    catch(SQLException e2){
		e2.printStackTrace();
	    }
	    catch(ClassNotFoundException e3){
		e3.printStackTrace();
	    }
	    catch(IllegalAccessException e4){
		e4.printStackTrace();
	    }

	    if((firstName.length()==0)||(lastName.length()==0)|| (email.length()==0)|| (username.length()==0)||(password.length()==0)){
		//they didn't enter a required value.
		templateName="nullParamInSignUpPage.ftl";
		templatePath=templateDir+"/"+templateName;
		Map<String,String>root=new HashMap<String,String>();
		
		try{
		    template=cfg.getTemplate("nullParamInSignUpPage.ftl");
		}//try                                                                                                                           
		catch(IOException e){
		    throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
		}//catch                                                                                                                         
		
		res.setContentType("text/html; charset="+template.getEncoding());
		Writer out=res.getWriter();
		
		//merge the data model and template                                                                                              
		try{
		    template.process(root,out);
		}catch(TemplateException e){
		    throw new ServletException("Error while processing Freemarker template",e);
		}
		out.close();

	    }//if 

	    //else there are no null values they've entered all of them
	    else{
		
		String checkUserName="select username from customerHierarchy where username='"+username+
		    "' union select email from customerHierarchy where email='"+email+"';";
		String insertUserQuery="insert into customerHierarchy (firstname,lastname,username,passw,email) VALUES "+
		    "('"+firstName+"','"+lastName+"','"+username+"','"+password+"','"+email+"');";
	


		//try to run insertUserQuery(if it works run customerSuccessfullySignedUp.ftl
		//else tell them an error occurred. username already exists try another.
	

		int r = 0;
		try {
		    Statement s = con.createStatement();
		    r = s.executeUpdate(insertUserQuery);
		} 
		catch (SQLException e) {
		    e.printStackTrace();
		}
		if(r==0){
		    //error page
		    templateName="userNameExists.ftl";
		    templatePath=templateDir+"/"+templateName;
		    Map<String,String>root=new HashMap<String,String>();
		    
		    try{
			template=cfg.getTemplate("userNameExists.ftl");
		    }//try                                                                                                                           
		    catch(IOException e){
			throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
		    }//catch                                                                                                                         
		    
		    res.setContentType("text/html; charset="+template.getEncoding());
		    Writer out=res.getWriter();
		
		    //merge the data model and template                                                                                              
		    try{
			template.process(root,out);
		    }catch(TemplateException e){
			throw new ServletException("Error while processing Freemarker template",e);
		    }
		    out.close();
		}//if
		else if(r!=0){
		    //success
		    templateName="customerSuccessfullySignedUp.ftl";
		    templatePath=templateDir+"/"+templateName;
		    Map<String,String>root=new HashMap<String,String>();
		    
		    try{
			template=cfg.getTemplate("customerSuccessfullySignedUp.ftl");
		    }//try                                                                                                                           
		    catch(IOException e){
			throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
		    }//catch                                                                                                                         
		    
		    res.setContentType("text/html; charset="+template.getEncoding());
		    Writer out=res.getWriter();
		    root.put("username",username);
		    activeUser=username;
		    //merge the data model and template                                                                                              
		    try{
			template.process(root,out);
		    }catch(TemplateException e){
			throw new ServletException("Error while processing Freemarker template",e);
		    }
		    out.close();
		}//else
		

	    }//else
	    
	    
	    //out.close();
	    try{
		if(con!=null){
		    con.close();
		}
		if(stmt!=null){
		    stmt.close();
		}
	    }
	    catch(SQLException e){
		e.printStackTrace();
	    }

	}//customerSignedUpPage


	public void toCustomerHomePage(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	    //will display that correct ftl file
	    Template template;
	    templateName="toCustomerHomePage.ftl";
	    templatePath=templateDir+"/"+templateName;
	    Map<String,String>root=new HashMap<String,String>();
	    
	    
	    try{
		template=cfg.getTemplate("toCustomerHomePage.ftl");
	    }//try
	    catch(IOException e){
		throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
	    }//catch
	    
	    res.setContentType("text/html; charset="+template.getEncoding());
	    Writer out=res.getWriter();
	    root.put("user",activeUser);
	    //merge the data model and template
	    try{
		template.process(root,out);
	    }catch(TemplateException e){
		throw new ServletException("Error while processing Freemarker template",e);
	    }

	     out.close();

	}//toCustomerHomePage

	public void toAdminHomePage(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	    //will display that correct ftl file
	    Template template;
	    templateName="toAdminHomePage.ftl";
	    templatePath=templateDir+"/"+templateName;
	    Map<String,String>root=new HashMap<String,String>();
	    
	    
	    try{
		template=cfg.getTemplate("toAdminHomePage.ftl");
	    }//try
	    catch(IOException e){
		throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
	    }//catch
	    
	    res.setContentType("text/html; charset="+template.getEncoding());
	    Writer out=res.getWriter();
	    root.put("user",activeUser);
	    //merge the data model and template
	    try{
		template.process(root,out);
	    }catch(TemplateException e){
		throw new ServletException("Error while processing Freemarker template",e);
	    }

	     out.close();

	}//toAdminHomePage

	public void logOff(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	    //will display that correct ftl file
	    Template template;
	    templateName="logOffSuccess.ftl";
	    templatePath=templateDir+"/"+templateName;
	    Map<String,String>root=new HashMap<String,String>();
	    activeUser="";
	    
	    try{
		template=cfg.getTemplate("logOffSuccess.ftl");
	    }//try
	    catch(IOException e){
		throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
	    }//catch
	    
	    res.setContentType("text/html; charset="+template.getEncoding());
	    Writer out=res.getWriter();
	    
	    //merge the data model and template
	    try{
		template.process(root,out);
	    }catch(TemplateException e){
		throw new ServletException("Error while processing Freemarker template",e);
	    }

	     out.close();

	}//logOff

	public void loadCreateRentalLocationPage(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	    //will display that correct ftl file
	    Template template;
	    templateName="createRentalLocationPage.ftl";
	    templatePath=templateDir+"/"+templateName;
	    Map<String,String>root=new HashMap<String,String>();
	    
	    
	    try{
		template=cfg.getTemplate("createRentalLocationPage.ftl");
	    }//try
	    catch(IOException e){
		throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
	    }//catch
	    
	    res.setContentType("text/html; charset="+template.getEncoding());
	    Writer out=res.getWriter();
	    root.put("user",activeUser);
	    //merge the data model and template
	    try{
		template.process(root,out);
	    }catch(TemplateException e){
		throw new ServletException("Error while processing Freemarker template",e);
	    }

	     out.close();

	}//loadCreateRentalLocation


	public void createRentalLocation(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	    String rentalLocationName=req.getParameter("rentalLocationName");
	    String rentalLocationAddress=req.getParameter("rentalLocationAddress");
	    String rentalLocationCapacity=req.getParameter("rentalLocationCapacity");
	    int capacity=Integer.parseInt(rentalLocationCapacity);
	    
	    Template template;
	    
	    try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con=DriverManager.getConnection(dbURL,dbUname,dbPassw);
		stmt=con.createStatement();
	    }
	    catch(InstantiationException e1){
		e1.printStackTrace();
	    }
	    catch(SQLException e2){
		e2.printStackTrace();
	    }
	    catch(ClassNotFoundException e3){
		e3.printStackTrace();
	    }
	    catch(IllegalAccessException e4){
		e4.printStackTrace();
	    }


	    String insertRLQuery="insert into rentalLocation (name, address, capacity) values ('"
		+rentalLocationName+"','"+rentalLocationAddress+"','"+capacity+"');";
	    String selectRLQuery="select name, address, capacity from rentalLocation;";
	    int r = 0;
	    try {
		Statement s = con.createStatement();
		r = s.executeUpdate(insertRLQuery);
	    } 
	    catch (SQLException e) {
		e.printStackTrace();
	    }
	    if(r==0){
		//error page
		templateName="failedToInsertRL.ftl";
		templatePath=templateDir+"/"+templateName;
		Map<String,String>root=new HashMap<String,String>();
		
		try{
		    template=cfg.getTemplate("failedToInsertRL.ftl");
		}//try                                                                                                                           
		catch(IOException e){
		    throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
		}//catch                                                                                                                         
		
		res.setContentType("text/html; charset="+template.getEncoding());
		Writer out=res.getWriter();
		root.put("user",activeUser);
		//merge the data model and template                                                                                              
		try{
		    template.process(root,out);
		}catch(TemplateException e){
		    throw new ServletException("Error while processing Freemarker template",e);
		}
		out.close();
	    }//if
	    else if(r!=0){
		//success

		String html="";
		String n="";
		String a="";
		int c=-1;

		html="<table><tr><th>RL Name</th>"+
		     "<th>RL Address</th>"+
		    "<th>RL Capacity</th></tr>";


		try{
		    if(stmt.execute(selectRLQuery)){
			ResultSet rs1=stmt.getResultSet();
			while(rs1.next()){
			    n=rs1.getString("name");
			    a=rs1.getString("address");
			    c=rs1.getInt("capacity");
			    html= html+"<tr><td>"+n+"</td><td>"+a+"</td>"+
				"<td>"+c+"</td></tr>";
			}//while
		    }//if
		    
		    else{
			//out.println("error in execute statement");
		    }
		}//try
		catch(Exception e){
		    e.printStackTrace();
		}
		
		templateName="successInsertRL.ftl";
		templatePath=templateDir+"/"+templateName;
		Map<String,String>root=new HashMap<String,String>();
		
		try{
		    template=cfg.getTemplate("successInsertRL.ftl");
		}//try                                                                                                                           
		catch(IOException e){
		    throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
		}//catch                                                                                                                         
		
		res.setContentType("text/html; charset="+template.getEncoding());
		Writer out=res.getWriter();
		root.put("user",activeUser);
		root.put("html",html);
		//merge the data model and template                                                                                              
		try{
		    template.process(root,out);
		}catch(TemplateException e){
		    throw new ServletException("Error while processing Freemarker template",e);
		}
		out.close();
	    }//else
	    
	    //out.close();
	    try{
		if(con!=null){
		    con.close();
		}
		if(stmt!=null){
		    stmt.close();
		}
	    }
	    catch(SQLException e){
		e.printStackTrace();
	    }
	    
	    
	}//createRentalLocation

	public void loadCreateVehiclePage(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	    //will display that correct ftl file
	    Template template;
	    templateName="createVehiclePage.ftl";
	    templatePath=templateDir+"/"+templateName;
	    Map<String,String>root=new HashMap<String,String>();
	    
	    
	    try{
		template=cfg.getTemplate("createVehiclePage.ftl");
	    }//try
	    catch(IOException e){
		throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
	    }//catch
	    
	    res.setContentType("text/html; charset="+template.getEncoding());
	    Writer out=res.getWriter();
	    root.put("user",activeUser);
	    //merge the data model and template
	    try{
		template.process(root,out);
	    }catch(TemplateException e){
		throw new ServletException("Error while processing Freemarker template",e);
	    }

	     out.close();

	}//loadCreateVehiclePage

	public void loadCreateVehiclePage(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	    //will display that correct ftl file
	    Template template;
	    templateName="createVehicleTypePage.ftl";
	    templatePath=templateDir+"/"+templateName;
	    Map<String,String>root=new HashMap<String,String>();
	    
	    
	    try{
		template=cfg.getTemplate("createVehicleTypePage.ftl");
	    }//try
	    catch(IOException e){
		throw new ServletException("Can't load Template "+templatePath+": "+e.toString());
	    }//catch
	    
	    res.setContentType("text/html; charset="+template.getEncoding());
	    Writer out=res.getWriter();
	    root.put("user",activeUser);
	    //merge the data model and template
	    try{
		template.process(root,out);
	    }catch(TemplateException e){
		throw new ServletException("Error while processing Freemarker template",e);
	    }

	     out.close();

	}//loadCreateVehicleTypePage



    }//class