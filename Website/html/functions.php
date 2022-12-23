<?php
function check_login($conn)
{
  if (isset($_SESSION['ID'])) {
    $id = $_SESSION['ID'];
    $sql = "SELECT * FROM users WHERE ID = '$id' limit 1";

    $result = mysqli_query($conn, $sql);
    if ($result && mysqli_num_rows($result) > 0) {
      $user_data = mysqli_fetch_assoc($result);
      return $user_data;
    }
  } else {
    redirect();
  }
}

function redirect()
{
  // redirect to login
  header("Location: login.php");
  exit;
}

function get_user_data($conn)
{
  if (isset($_SESSION['ID'])) {
    $id = $_SESSION['ID'];
    $sql = "SELECT * FROM users WHERE ID = '$id' limit 1";

    $result = mysqli_query($conn, $sql);
    $user_data = mysqli_fetch_assoc($result);
    return $user_data;
  }
}

function sort_dates($a, $b)
{
  $date1 = strtotime($a['blogDate']);
  $date2 = strtotime($b['blogDate']);
  return $date1 - $date2;
}

function get_blog_data($conn)
{
  if (isset($_SESSION['ID'])) {
    $id = $_SESSION['ID'];
    $sql = "SELECT * FROM blog_posts";

    $result = mysqli_query($conn, $sql);
    //$blog_data = mysqli_fetch_assoc($result);

    /*foreach ($blog_data as $key => $part) {
      $sort[$key] = strtotime($part['blogDate']);
    }
    array_multisort($sort, SORT_DESC, $blog_data);*/

    //usort($blog_data, 'sort_dates');*/

    //$new_blog_data = array_reverse($blog_data);

    return $result;
  }
}

function get_blog_titles($conn)
{
  $counter = 0;
  $result = get_blog_data($conn);
  $titleList = array();
  foreach ($result as $q) {
    $titleList[$counter] = $q['title'];
    $counter++;
  }
  return $titleList;
}

function get_blog_contents($conn)
{
  $counter = 0;
  $result = get_blog_data($conn);
  $contentsList = array();
  foreach ($result as $q) {
    $contentsList[$counter] = $q['content'];
    $counter++;
  }
  return $contentsList;
}

function get_blog_dates($conn)
{
  $counter = 0;
  $result = get_blog_data($conn);
  $datesList = array();
  foreach ($result as $q) {
    $datesList[$counter] = $q['blogDate'];
    $counter++;
  }
  return $datesList;
}

/*function get_row_count($conn)
{
  $result = get_blog_data($conn);
  $rowcount = mysqli_num_rows($result);
  return $rowcount;
}*/


/*echo $q['ID'] . "<br>";
      echo $q['title'] . "<br>";
      echo $q['content'] . "<br>";
      echo $q['blogDate'] . "<br>";*/
//$dateInfo = mysqli_fetch_assoc($result);

//$date = $dateInfo["ID"];
//printf($date);



function function_alert($message)
{
  // Display the alert box 
  echo "<script>alert('$message');</script>";
}
