<?php
if (!isset($_SESSION)) {
  session_start();
}

include("connections.php");
include_once("functions.php");

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  $username = $_POST['username'];
  $password = $_POST['password'];

  if (!empty($username) && !empty($password) && !is_numeric($username)) {
    //read from database
    $sql = "SELECT * FROM USERS WHERE uname = '$username'";
    $result = mysqli_query($conn, $sql);

    if ($result) {
      if ($result && mysqli_num_rows($result) > 0) {
        $user_data = mysqli_fetch_assoc($result);

        if ($user_data['password'] === $password) {
          $_SESSION['ID'] = $user_data['ID'];
          header("Location: blogpage.php");
          exit;
        } else {
          function_alert("Incorrect Username or Password");
        }
      }
    }

    header("Location: login.php");
    function_alert("Incorrect Username or Password");
    die;
  }

  $conn->close();
}

?>

<!DOCTYPE html>
<html lang="en">

<head>
  <title>Login Page</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://fonts.googleapis.com/css?family=Dosis" rel="stylesheet" />
  <link href="../css/reset.css" rel="stylesheet">
  <link href="../css/main.css" rel="stylesheet">
</head>

<body>
  <!-- Header -->
  <header id="header-inner">
    <div class="container">
      <nav id="main-nav">
        <img src="images/logofinalv4.png" alt="Logo" id="AA-logo" width="50em" height="50em">
        <ul class="navlist">
          <li><a href="index.php">Home</a></li>
          <li><a href="about.php">About</a></li>
          <li><a href="portfolio.php">Portfolio</a></li>
          <li><a href="blogpage.php">Blog</a></li>
          <li><a href="contact.php">Contact</a></li>
        </ul>
      </nav>
    </div>
  </header>

  <!--Login Box-->
  <section id="login" class="text-center pad3">
    <h2 class="section-title-white">Login Page</h2>
    <div class="bottom-line"></div>
    <div class="container">
      <form class="login-form" method="POST">
        <div class="username">
          <label for="username"><b>Username</b></label>
          <input type="text" placeholder="Enter Username" name="username" required>
        </div>
        <div class="password">
          <label for="password"><b>Password</b></label>
          <input type="password" placeholder="Enter Password" name="password" required>
        </div>
        <button type="submit" class="login-btn btn-dark">Login</button>
        <!--<label class="remember-me">
            <input type="checkbox" name="remember"> Remember me
          </label>-->
        <br>
        <a href="signup.php" class="signup-link">Create new account</a>
      </form>
    </div>
  </section>

  <!-- Footer -->
  <footer id="main-footer">
    <div class="footer-content container">
      <p>Copyright &copy; 2022. All Rights Reserved</p>
      <div class="social">
        <a href="https://www.instagram.com/aaqib.a1/?hl=en"><img src="images/icons/instagram.png" alt="Instagram" class="icon"></a>
        <a href="https://www.facebook.com/profile.php?id=100072672469824"><img src="images/icons/facebook.png" alt="Facebook" class="icon"></a>
        <a href="https://www.linkedin.com/in/aaqib-alavy-172b7a227/"><img src="images/icons/linkedinpng.png" alt="LinkedIn" class="icon"></a>
      </div>
    </div>
  </footer>
</body>