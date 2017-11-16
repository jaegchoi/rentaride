<html>
  <head>
    <title>Customer Login</title>
  </head>
  <body>
    <h1>Sorry. Incorrect Password. That password doesn't match the password we have for that given username</h1>
    <h2> Please Login: </h2>
    <form action="RentARide" method="POST">
      Username: <input type="text" name="username"><br>
      Password: <input type="password" name="password"><br>
      <input type="submit" value="Login" name="customerLoggedIn">
      <input type="reset" value="Reset">
    </form>
  </body>
</html>
