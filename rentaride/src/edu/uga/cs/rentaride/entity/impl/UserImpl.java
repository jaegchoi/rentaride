

import java.util.Date;

import edu.uga.cs.rentaride.entity.UserStatus;

public class UserImpl {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private Date createDate;
    private String address;
    private UserStatus userStatus;
    
    public UserImpl(){
        
    }
    
    public UserImpl(String firstName, String lastName, String userName, String email, String password, Date createDate, String address, UserStatus userStatus){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password= password;
        this.createDate = createDate;
        this.address = address;
        this.userStatus = userStatus;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getUserName(){
        return userName;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public Date getCreatedDate(){
        return createDate;
    }
    
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public UserStatus getUserStatus(){
        return userStatus;
    }
    
    public void setUserStatus(UserStatus userStatus){
        this.userStatus = userStatus;
    }
    
}
