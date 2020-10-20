<?php
session_start();
?>
<!DOCTYPE HTML>
<html>
<head>
<title>Online Quiz - Test List</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="quiz.css" rel="stylesheet" type="text/css">
</head>
<body>
<?php
include("header.php");
include("dbconnect.php");
extract($_GET);
$rs1=mysql_query("select * from mst_subject where sub_Id=$subid");
$row1=mysql_fetch_array($rs1);
echo "<h1 align=center><font color=blue> $row1[1]</font></h1>";
$rs=mysql_query("select * from mst_test where sub_id=$subid");
if(mysql_num_rows($rs)<1)
{
	echo "<br><br><h2 class=head1> No Exam for this Subject </h2>";
	exit;
}
echo "<h2 class=head1> Select Topic Name to Give Quiz </h2>";
echo "<table align=center>";
 
while($row=mysql_fetch_row($rs))
{
	echo "<tr><td align=center ><a href=quiz.php?test_ID=$row[0]&Sub_Id=$subid><font size=4>$row[2]</font></a>";
}
echo "</table>";
?>
</body>
</html>