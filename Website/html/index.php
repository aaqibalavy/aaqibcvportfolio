<?php
if (!isset($_SESSION)) {
  session_start();
}
$_SESSION;
include("functions.php");
include("connections.php");

$user_data = get_user_data($conn);

?>

<!DOCTYPE html>
<html lang="en">

<head>
  <title>Aaqib Alavy | Queen Mary CompSci</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://fonts.googleapis.com/css?family=Dosis" rel="stylesheet" />
  <link href="../css/reset.css" rel="stylesheet">
  <link href="../css/main.css" rel="stylesheet">
</head>

<body>
  <!-- Header -->
  <header id="header-home">
    <div class="container">
      <nav id="main-nav">
        <img src="images/logofinalv4.png" alt="Logo" id="AA-logo" width="50em" height="50em">
        <ul class="navlist">
          <li><a href="index.php" class="current">Home</a></li>
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
      <div class="header-content">
        <h1>Hi, I'm Aaqib</h1>
        <p class="desc">Welcome to my Portfolio</p>
        <?php
        if (isset($_SESSION['ID'])) {
          echo '<a class="btn-light">You are logged in as ' . $user_data['uname'] . ' </a>';
        } else {
          echo '<a href="login.php" class="btn-main">LOGIN</a>';
        }
        ?>
      </div>
    </div>
  </header>

  <!-- Expertise and Skills -->
  <section id="home-expertise" class="text-center pad2">
    <div class="container">
      <h2 class="section-title">Expertise</h2>
      <div class="bottom-line"></div>
      <p class="desc">
        From programming to project management, I've acquired skills in a range of fields.
      </p>
      <div class="expertise">
        <div>
          <h3>Programming & Web Development</h3>
          <p>
            I am well-versed in Java programming having worked on multiple projects and assignments at degree-level, as well as HTML and CSS, with my first endeavours in web development being 3 years ago for an independant research project.
          </p>
        </div>
        <div>
          <h3>Project Management</h3>
          <p>
            Through all my creative endeavours I've accumulated knowledge and experience in managing as well as planning projects, both individually and with teams.
          </p>
        </div>
        <div>
          <h3>Digital Design</h3>
          <p>
            Art and design have been major parts of my life, both as hobbies and areas of study. I've developed this interest through working digitally to create visual graphics.
          </p>
        </div>
        <div>
          <h3>Video Editing</h3>
          <p>
            My expertise within video editing has expanded over the years, beginning as a hobby and gradually becoming more serious through working on projects in a team, as well as creating commissioned works for customers.
          </p>
        </div>
      </div>
    </div>
  </section>

  <!-- Technical Skills -->
  <section id="home-skills" class="bg-dark pad3">
    <div class="container">
      <h2 class="section-title">Technical Skills</h2>
      <div class="bottom-line"></div>
      <h4>Adobe Photoshop & Illustrator</h4>
      <div class="progress">
        <div style="width:90%"></div>
      </div>
      <h4>Vegas Pro</h4>
      <div class="progress">
        <div style="width:100%"></div>
      </div>
      <h4>Email Marketing</h4>
      <div class="progress">
        <div style="width:90%"></div>
      </div>
      <h4>Google Suite</h4>
      <div class="progress">
        <div style="width:100%"></div>
      </div>
      <h4>HTML & CSS</h4>
      <div class="progress">
        <div style="width:80%"></div>
      </div>
      <h4>Java</h4>
      <div class="progress">
        <div style="width:70%"></div>
      </div>
      <h4>Python</h4>
      <div class="progress">
        <div style="width:60%"></div>
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

</html>