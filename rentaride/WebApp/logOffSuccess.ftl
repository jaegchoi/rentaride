<!DOCTYPE html>
<html>
<head>
<title>Rent-A-Ride Login</title>
<!--<link rel="stylesheet" type="text/css" href="CSS/style.css">                                                                             
<link href="images/favicon.jpg" type="image/jpg" rel="shortcut icon"/>-->
</head>
<body>
  <div id="wrapper">
        <div id="header">
            <table>
                <tr>
                    <!--<td><img src="images/logo.png" alt="logo" width=200 height=100></td>-->
                    <td><h1>Rent-A-Ride</h1></td>
                    <!--<td><img src="images/logo.png" alt="logo" width=200 height=100></td>-->
                </tr>
            </table>
        </div>
        <div id="search">
          <h2>You have successfully logged off.</h2>
            <header>Please log in. Don't have an account? click Sign up to get started:</header><br>
            <form action="RentARide" method="POST">
              <input type="submit" value="Login" name="requestLogin">
              <input type="submit" value="Sign Up" name="requestSignUp">
              <input type="submit" value="Admin Login" name="requestAdminLogin">
            </form>
        </div>
        </div>
</body>
</html>
