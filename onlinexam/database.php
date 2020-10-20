<?php
error_reporting(1);
$cn=mysql_connect("localhost","root","") or die("Could not Connect My Sql");
mysql_select_db("demonstration_lisenme",$cn)  or die("Could not connect to Database");
?>