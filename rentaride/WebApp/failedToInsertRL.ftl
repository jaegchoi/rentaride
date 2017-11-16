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
                        <li class="menu"><input type="submit" value="Home" name="searchpage"> </li>
                        <li class="menu"><input type="submit" value="Create Rental Location" name="toCreateRentalLocation"></li>
                        <li class="menu"><input type="submit" value="Account Information" name="accountinfopage"></li>
                        <li class="menu"><input type="submit" value="Create Vehicle" name="myrecipespage"></li>
                        <li class="menu"><input type="submit" value="Create Vehicle Type" name="aboutpage"></li>
                        <li class="menu"><input type="submit" value="Misc" name="contactpage"></li>
                   </ul>
             </form>
        </div>
	<h1>Failed to Insert Rental Location. Please try again.</h1>
        <form action="RentARide" method="post">
          Rental Location Name: <input type="text" name="rentalLocationName"><br>
          Rental Location Address: <input type="text" name="rentalLocationAddress"><br>
          Rental Location Capacity: <input type="text" name="rentalLocationCapacity"><br>
          <input type="submit" value="Create Rental Location" name="createRentalLocation">
          </form>
     <div id="tabl">
</div>
</div>
</body>
</html>