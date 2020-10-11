<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<h2>Sign In</h2>

<form name="myForm" action="AuthServlet" onsubmit="return validateForm()" method="POST">

  Name:<br>
  <input type="text" name="username">
  <br>
  Password:<br>
  <input type="password" name="password">
  <br>
	<br>
<input type="submit" value="Submit">
</form> 



<script>
function validateForm() {
	var x = document.forms["myForm"]["username"].value;
	var z= document.forms["myForm"]["password"].value;

 if (x == "") {
        alert("Name must be filled out");
        return false;}
 if (z == "") {
        alert("Password must be filled out");
        return false;
    }
}
</script>



</body>
</html>