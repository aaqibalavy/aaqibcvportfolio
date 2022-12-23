<?php

if (!isset($_SESSION)) {
  session_start();
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <title>My Portfolio | Aaqib Alavy</title>
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
          <li><a href="portfolio.php" class="current">Portfolio</a></li>
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

  <!-- Portfolio -->
  <section id="portfolio" class="text-center pad3">
    <div class="container">
      <h2 class="section-title">My Portfolio</h2>
      <div class="bottom-line"></div>
      <p class="desc">
        Check out some of my Computer Science projects
      </p>
      <div class="items">
        <div class="item">
          <div class="item-image">
            <img src="images/items/code.png" alt="Java" />
          </div>
          <div class="item-text">
            <div class="item-text-wrap pad1">
              <p class="item-text-category">Java Programming</p>
              <h2 class="item-text-title">Animal Forest Healing Game</h2>
            </div>
          </div>
        </div>
        <div class="item">
          <div class="item-image">
            <img src="images/items/study.png" alt="Study" />
          </div>
          <div class="item-text">
            <div class="item-text-wrap pad1">
              <p class="item-text-category">Research Study</p>
              <h2 class="item-text-title">Eco-Friendly Portable Devices</h2>
            </div>
          </div>
        </div>
        <div class="item">
          <div class="item-image">
            <img src="images/items/website.png" alt="Website" />
          </div>
          <div class="item-text">
            <div class="item-text-wrap pad1">
              <p class="item-text-category">Web Development</p>
              <h2 class="item-text-title">EdgeLedger Concept Company Website</h2>
            </div>
          </div>
        </div>
      </div>
      <!--Edits-->
      <div class="pad1"></div>
      <div class="bottom-line"></div>
      <p class="desc">
        Here are some of my creative editing projects
      </p>
      <div class="edits">
        <div class="item anime">
          <div class="item-image">
            <img src="images/items/edit.gif" alt="Video Edit" />
          </div>
          <div class="item-text">
            <div class="item-text-wrap pad1">
              <p class="item-text-category">Video Editing: Anime</p>
              <h2 class="item-text-title">Conceptual Anime Manipulation Editing</h2>
            </div>
          </div>
        </div>
        <div class="item cod">
          <div class="item-image">
            <img src="images/items/cod-edit.gif" alt="Call Of Duty Edit" />
          </div>
          <div class="item-text">
            <div class="item-text-wrap pad1">
              <p class="item-text-category">Video Editing: Games</p>
              <h2 class="item-text-title">Synced Game Maniupulation Editing</h2>
            </div>
          </div>
        </div>
        <div class="item game">
          <div class="item-image">
            <img src="images/items/hidden.png" alt="Game Cover" />
          </div>
          <div class="item-text">
            <div class="item-text-wrap pad1">
              <p class="item-text-category">Graphic Design</p>
              <h2 class="item-text-title">Video Game Cover Concept</h2>
            </div>
          </div>
        </div>
      </div>
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