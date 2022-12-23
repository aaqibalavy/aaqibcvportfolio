<?php
/*if (!isset($_SESSION)) {
  session_start();
}

if (isset($_SESSION['ID'])) {
  unset($_SESSION['ID']);
}*/

session_start();
session_unset();
session_destroy();

header("Location: index.php");
exit;
