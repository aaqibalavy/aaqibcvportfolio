<!DOCTYPE html>
<html lang="en">

<head>
  <title>Contact Me | Aaqib Alavy</title>
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

  <!-- Contact Form -->
  <section id="contact" class="text-center pad3">
    <div class="container">
      <h2 class="section-title">Contact Me<br>(PAGE UNDER CONSTRUCTION)</h2>
      <div class="bottom-line"></div>
      <p class="desc">Please reach out using the fields below</p>
      <form name="contact" method="POST">
        <div class="text-fields">
          <input type="text" class="text-input name-input" placeholder="Name" name="name" />
          <input type="text" class="text-input subject-input" placeholder="Subject" name="subject" />
          <input type="email" class="text-input email-input" placeholder="Email Address" name="email" />
          <input type="text" class="text-input phone-input" placeholder="Phone Number" name="phone" />
          <textarea class="text-input message-input" placeholder="Enter Message" name="message"></textarea>
        </div>
        <button type="submit" class="btn-dark">Submit</button>
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

</html>