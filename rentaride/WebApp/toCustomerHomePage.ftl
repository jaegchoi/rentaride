<!DOCTYPE html>
<html>
<head>
<title>RentARide Home</title>
<link rel="stylesheet" type="text/css" href="CSS/homepageStyle.css">
<link href="images/favicon.jpg" type="image/jpg" rel="shortcut icon"/>
</head>
<body>
<div id="wrapper">
     <div id="info">
     	  <form action="RentARide" method="post">
            Welcome, ${user}<input type="submit" value="Log Off" name="clickedLogOff">
        </form>
            <table>
                <tr>
                    <td><img src="images/logo.png" alt="logo" width=200 height=100></td>
                    <td><h1>RentARide!</h1></td>
                    <td><img src="images/logo.png" alt="logo" width=200 height=100></td>
                </tr>
            </table>
        </div>
	<div id="menubar">
	     <form action="RentARide" method="post">
	     	   <ul class="mymenubar">
			<li class="menu"><input type="submit" value="Home" name="toCustomerHomePage"> </li>
			<li class="menu"><input type="submit" value="Reserve a Vehicle" name="toptenpage"></li>
			<li class="menu"><input type="submit" value="Account Information" name="accountinfopage"></li>
			<li class="menu"><input type="submit" value="Reviews" name="myrecipespage"></li>
			<li class="menu"><input type="submit" value="About Us" name="aboutpage"></li>
			<li class="menu"><input type="submit" value="Misc" name="contactpage"></li>
		   </ul>
	     </form>
	</div>
     <p> Home Page. here is some key stuff about our website.</p>
     <div id="tabl">	     
     </div>
</div>                                                          
</body>
</html>
