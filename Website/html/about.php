<?php

if (!isset($_SESSION)) {
  session_start();
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <title>About Me | Aaqib Alavy</title>
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
          <li><a href="about.php" class="current">About</a></li>
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

  <article id="about-me-article">
    <section id="about" class="text-center pad3">
      <div class="container">
        <h2 class="section-title">About Me</h2>
        <div class="bottom-line"></div>
        <p class="desc">
          Let me tell you a little about myself...
        </p>
        <div class="about-info">
          <img src="../html/images/silhouettev2.jpg" alt="Profile" class="profile">
          <div class="about-text text-left">
            <h4><i>Computer Science undergraduate student studying at Queen Mary University of London</i></h4>
            <p class="pad1">
              Problem solving and decision making, complex algorithms and technological systems.
              These are some of the many facets within the field of computer science that fascinate logical thinkers,
              and in being one myself I’ve found a passion within this industry. But it is in the world of Games Development
              that I aim to contribute to and become an integrated part of, specifically in the more technological areas of the
              field such as the development of AI Games Systems, through which I look to expand my knowledge in by
              completing my degree at Queen Mary University of London.
            </p>
            <h4 class="pad1">Research Interests</h4>
            <div class="research">
              <ul>
                <li>AI Games Systems - This ranges from AI enemies/players to automatic level/map generation</li>
                <li>Psychology in Games - How certain psychological responses can be targeted through game design</li>
                <li>Augmented Reality - The live altering of reality through digital tracking and augmentation</li>
                <li>Object Collision - Collision between different elements on the screen and how they react upon pixels coming into contact</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </section>
  </article>


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