<style type = "main1.css">
<!--
body{
margin-left: 0px;
margin-top: 0px;
}
-->
</style>
<table border = "0" width="100%" cellspace="0" cellpadding="0" backgrounf="74728.jpg">
<tr>
<td width="90%" valign="top">
<!-- You can modify the text,colour,size, number of loops and more on the flash header by editing the text file(fence.txt) included in the zip file.-->

<td width="100%">

</tr>
</table>

<Table width="100%">
<tr>
<td>
<?php @$_SESSION['login'];
error_reporting(1);
?>
</td>
<td>
<?php
if(isset($_SESSION['login']))
{
	echo "<div align=\"right\"><string><a href=\"inex.php\">Home</a>|<a href=\"signot.php\">Signout</a></strong></div>";
}
else
{
	echo "&nbsp;";
}
?>
</td>
</tr>
</table>