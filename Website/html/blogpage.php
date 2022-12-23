<?php
if (!isset($_SESSION)) {
  session_start();
}

include_once("functions.php");
include("connections.php");

$user_data = check_login($conn);
$blog_data = get_blog_data($conn);

// date("jS \ F Y, h:i") . " UTC"

?>


<!DOCTYPE html>
<html lang="en">

<head>
  <title>Blogpage | Aaqib Alavy</title>
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
          <li><a href="blogpage.php" class="current">Blog</a></li>
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

  <section id="blog-page" class="text-center pad3">
    <h2 class="section-title-white">Blog Page</h2>
    <div class="bottom-line"></div>
    <a href="newPost.php" class="btn-main mar2 new-post-btn">Create New Post</a>
    <div class="container">
      <?php foreach ($blog_data as $q) : ?>
        <p class="blog-date"><?php echo date('jS \ F Y, h:i', strtotime($q['blogDate'])) . " UTC"; ?></p>
        <div class="blog-posts">
          <h3 class="blog-title"><?php echo $q['title']; ?></h2>
            <p class="blog-content"><?php echo $q['content']; ?></p>
            <div class="blog-line"></div>
        </div>
      <?php endforeach; ?>
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