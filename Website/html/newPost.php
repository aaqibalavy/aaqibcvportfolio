<?php
if (!isset($_SESSION)) {
  session_start();
}

include_once("functions.php");
include("connections.php");

$user_data = check_login($conn);

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  $title = $_POST["post-title"];
  $content = $_POST["post-content"];
  $date = date('d-m-Y H:i:s');

  if (!empty($title) && !empty($content) && !empty($date)) {
    $sql = "INSERT INTO BLOG_POSTS (title, content, blogDate) VALUES ('$title', '$content', NOW())";
    mysqli_query($conn, $sql);

    header("Location: blogpage.php?info=added");
    exit;
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }
  $conn->close();
}

?>

<script>
  document.querySelector('clear-btn').addEventListener('reset', (event) => {
    if (!confirm('Are you sure you want to reset? This will erase whatever you\'ve written')) {
      event.preventDefault();
    }
  });
</script>


<!DOCTYPE html>
<html lang="en">

<head>
  <title>New Post | Aaqib Alavy</title>
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

  <section id="new-post" class="text-center pad3">
    <div class="pad1 form-container">
      <form class="new-post" method="POST">
        <h2 class="section-title">Create New Post</h2>
        <div class="post-info pad1">
          <!--<label for="post-title"><b>Title</b></label>-->
          <input type="text" placeholder="Title" name="post-title" required>
          <!--<label for="password"><b>Password</b></label>-->
          <textarea name="post-content" class="" placeholder="Enter your text here" cols="30" rows="10" required></textarea>
        </div>
        <div class="post-buttons pad1">
          <button type="submit" class="btn-dark">POST</button>
          <button type="reset" id="clear-btn" class="btn-dark">CLEAR</button>
        </div>
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