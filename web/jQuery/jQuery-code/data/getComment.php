<?php
 header("Content-Type:application/json");
 require_once("init.php");
 $sql="SELECT * FROM detail_comments";
 $result=mysqli_query($conn,$sql);
 echo json_encode(mysqli_fetch_all($result,1));
?>