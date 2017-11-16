<html>
  <head>
    <title>Customer Login</title>
  </head>
  <body>
    <h1>Sorry. Incorrect Username. That username doesn't exist in our system. Please try again.</h1>
    <h2> Please Login: </h2>
    <form action="RentARide" method="POST">
      Username: <input type="text" name="username"><br>
      Password: <input type="password" name="password"><br>
      <input type="submit" value="Login" name="customerLoggedIn">
      <input type="reset" value="reset">
    </form>
  </body>
</html>
