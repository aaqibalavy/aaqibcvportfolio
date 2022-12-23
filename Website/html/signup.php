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
    //save to database
    $sql = "INSERT INTO USERS (uname, password) VALUES ('$username', '$password')";
    mysqli_query($conn, $sql);

    header("Location: login.php");
    exit;
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }
  $conn->close();
}

?>

<!DOCTYPE html>
<html lang="en">

<head>
  <title>SignUp Page</title>
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
          <?php
          if (isset($_SESSION['ID'])) {
            echo '<li><a href="logout.php" id="logout">LOGOUT</a></li>';
          } else {
            echo '<li><a href="contact.php">Contact</a></li>';
          }
          ?>
        </ul>
      </nav>
    </div>
  </header>

  <!--SignUp Box-->
  <section id="signup" class="text-center pad3">
    <h2 class="section-title-white">SignUp Page</h2>
    <div class="bottom-line"></div>
    <div class="container">
      <form class="signup-form" method="POST">
        <div class="username">
          <label for="username"><b>Username</b></label>
          <input type="text" placeholder="Enter Username" name="username" required>
        </div>
        <div class="password">
          <label for="password"><b>Password</b></label>
          <input type="password" placeholder="Enter Password" name="password" required>
        </div>
        <button type="submit" class="signup-btn btn-dark">SignUp</button>
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