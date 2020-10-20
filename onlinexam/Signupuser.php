<!DOCTYPE HTML >
<html>
<head>
<title>User Signup</title>

<link href="quiz1.css" rel="stylesheet" type="text/css">
</head>
 
<body>
<?php
include("header.php");
extract($_POST);
include("dbconnect.php");
$rs=mysql_query("select * from mst_user where loginid='$Login Id'");
if (mysql_num_rows($rs)>0)
{
	echo "<br><br><br><div class=head1>Login Id Already Exists</div>";
	exit;
}
$query="insert into mst_user(user_id,loginid,pass,username,address,city,phone,email) values('$uid','$lid','$pass','$name','$address','$city','$phone','$email')";
$rs=mysql_query($query)or die("Could Not Perform the Query");
echo "<br><br><br><div class=head1>Your Login ID  $lid Created Sucessfully</div>";
echo "<br><div class=head1>Please Login using your Login ID to take Quiz</div>";
echo "<br><div class=head1><a href=index.php>Login</a></div>";
 
 
?>
</body>
</html>